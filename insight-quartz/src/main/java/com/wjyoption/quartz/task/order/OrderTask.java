package com.wjyoption.quartz.task.order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wjyoption.common.utils.DateUtils;
import com.wjyoption.common.utils.db.RedisEnum;
import com.wjyoption.common.utils.http.HttpUtils;
import com.wjyoption.system.domain.WpProductdata;
import com.wjyoption.system.domain.WpProductinfo;
import com.wjyoption.system.service.IWpOrderService;
import com.wjyoption.system.service.IWpProductdataService;
import com.wjyoption.system.service.IWpProductinfoService;
import com.wjyoption.system.vo.report.ProductKlineRedisVo;
import com.wjyoption.system.vo.report.ProductKlineVo;
import com.wjyoption.system.vo.report.ProductNowDataVo;


//@Component("orderTask")
//@EnableScheduling
public class OrderTask {

	private static Logger logger = LoggerFactory.getLogger("sys_tcp");
	private static String DOMAIN = "https://api.finage.co.uk/agg/forex/";
	private static String API_KEY = "API_KEY492A6TP6CXNV3ZILWIGF2HXQETMPAP11";
	@Autowired IWpProductinfoService productinfoService;
	@Autowired IWpProductdataService productdataService;
	@Autowired IWpOrderService orderService;
	
	@Autowired WebsocketClient webSocketClient;
	@SuppressWarnings("rawtypes")
	@Autowired RedisTemplate redisTemplate;
	@Value("${wjyoption.tcpSwitch}")
	private String tcpSwitch;
	
	/**
	 * 更新K线图
	 */
	@Scheduled(fixedDelay=50000)
	public void updateKline(){
		if("0".equals(tcpSwitch)){
			return;
		}
		Map<String, ProductNowDataVo> nowMap = webSocketClient.getNowData();
		if(nowMap == null || nowMap.size() == 0){
			logger.info("当前数据为空.....");
			return;
		}
		long beginTime = System.currentTimeMillis();
		logger.info("更新k线图");
		WpProductinfo wpProductinfo = new WpProductinfo();
		wpProductinfo.setIsdelete(0);
		List<WpProductinfo> productList = this.productinfoService.selectWpProductinfoList(wpProductinfo);
		for(WpProductinfo product : productList){
			if(!productinfoService.canBuy(null, product)){
				logger.info("产品休市中>" + product.getProcode());
				continue;
			}
			ProductNowDataVo nowData = nowMap.get(product.getPid().toString());
			BigDecimal nowPrice = null;
			Long nowTime = null;
			if(nowData != null){
				nowPrice = nowData.getBid_price();
				nowTime = nowData.getTime();
			}
			getkdata(product, "1", nowPrice,nowTime);
			getkdata(product, "5", nowPrice,nowTime);
			getkdata(product, "15", nowPrice,nowTime);
			getkdata(product, "30", nowPrice,nowTime);
			getkdata(product, "60", nowPrice,nowTime);
			getkdata(product, "D", nowPrice,nowTime);
		}
		logger.info("更新k线图结束:spendtime->"+(System.currentTimeMillis()-beginTime));
	}
	
//	{"code":0,"data":[{"open":0.74632,"high":0.74649,"low":0.74632,"close":0.74636,"time":1626073560},{"open":0.7466,"high":0.74661,"low":0.74632,"close":0.74632,"time":1626073500}]}
	@SuppressWarnings("unchecked")
	private void getkdata(WpProductinfo vo,String interval,BigDecimal price,Long nowSecond){
//		long nowSecond = DateUtils.getNowSecond();
		if(nowSecond == null){
			nowSecond = DateUtils.getNowSecond();
		}
		String end = DateUtils.dateTime(new Date(nowSecond*1000), DateUtils.YYYY_MM_DD);
		String begin = end;
		String url = DOMAIN + vo.getProcode();// + "/1/minute/" + end +"/" + end;
		if("D".equalsIgnoreCase(interval)){
			url += "/1/day/";
			begin = DateUtils.dateTime(new Date((nowSecond-86400*90)*1000), DateUtils.YYYY_MM_DD);
		}else if("60".equalsIgnoreCase(interval)){
			url += "/1/hour/";
			begin = DateUtils.dateTime(new Date((nowSecond-86400*30)*1000), DateUtils.YYYY_MM_DD);
		}else{
			url += "/"+interval+"/minute/";
		}
		url += begin +"/" + end;
		try {
//			{"symbol":"AUDUSD","totalResults":20,"results":[{"o":0.73482,"h":0.73679,"l":0.73392,"c":0.73649,"v":2462,"t":1631404800000}...]}
			String res = HttpUtils.sendGet(url, "apikey="+API_KEY+"&limit=1000&sort=desc");
			JSONObject json = JSON.parseObject(res);
			if(!json.containsKey("results") || json.getJSONArray("results") == null){
				return;
			}
			JSONArray arr = json.getJSONArray("results");
			List<ProductKlineVo> array = new ArrayList<>();//.parseArray(json.getJSONArray("data").toJSONString(), ProductKlineVo.class);
			for(int i = 0;i < arr.size();i++){
				JSONObject obj = arr.getJSONObject(i);
				ProductKlineVo pdvo = new ProductKlineVo();
				pdvo.setClose(obj.getBigDecimal("c"));
				pdvo.setHigh(obj.getString("h"));
				pdvo.setLow(obj.getString("l"));
				pdvo.setOpen(obj.getBigDecimal("o"));
				pdvo.setTime(obj.getLong("t")/1000);
				array.add(pdvo);
			}
			if(array == null || array.size() == 0){
				return;
			}
			String keyPrefix = RedisEnum.PRODUCT_KLINE.getKeyPrefix();
			String key = keyPrefix + "_" + vo.getPid() + "_" + interval;
			BoundValueOperations<String,ProductKlineRedisVo> boundValueOps = this.redisTemplate.boundValueOps(key);
			ProductKlineRedisVo map  = new ProductKlineRedisVo();
			map.setData(array);
			map.setProcode(vo.getProcode());
			map.setPid(vo.getPid());
			boundValueOps.set(map, 20, TimeUnit.DAYS);
			if("1".equals(interval)){
				ProductKlineVo v = array.get(array.size() - 1);
				WpProductdata wpProductdata = this.productdataService.selectWpProductdataByPid(vo.getPid());
				wpProductdata.setOpen(v.getOpen());
				wpProductdata.setClose(v.getClose());
				wpProductdata.setHigh(v.getHigh());
				wpProductdata.setLow(v.getLow());
				wpProductdata.setUpdatetime(v.getTime());
				wpProductdata.setPrice(price);
				this.productdataService.updateWpProductdata(wpProductdata);
			}
		} catch (Exception e) {
			logger.error("updateKline data error1...",e);
		}
	}
	
	/**
	 * 定时
	 * 更新产品列表
	 */
	public void updateWebSocketMap(){
		webSocketClient.updateProductMap();
	}
	
	/**
	 * 定时
	 * 十秒或其他时间更新数据库
	 */
	public void updateProductNowData(){
		Map<String, ProductNowDataVo> map = webSocketClient.getNowData();
		Set<String> keySet = map.keySet();
		for(String pid:keySet){
			ProductNowDataVo nowData = map.get(pid);
			WpProductdata wpProductdata = new WpProductdata();
			wpProductdata.setPid(Integer.valueOf(pid));
			wpProductdata.setPrice(nowData.getBid_price());
			wpProductdata.setUpdatetime(nowData.getTime());
			this.productdataService.updateWpProductdataByPid(wpProductdata);
		}
	}
	
	
	@Scheduled(fixedDelay=3000)
	public void overOrder(){
		if("0".equals(tcpSwitch)){
			return;
		}
		long begin = System.currentTimeMillis();
		logger.info("结算订单开始");
		Map<String, ProductNowDataVo> nowData = this.webSocketClient.getNowData();
		if(nowData == null || nowData.size() == 0){
			logger.info("没有当前数据，无需结算");
		}else{
			this.orderService.overOrder(nowData);
		}
		logger.info("结算订单结束 spend->" + (System.currentTimeMillis()-begin));
		
	}
	
	
	
	
}
