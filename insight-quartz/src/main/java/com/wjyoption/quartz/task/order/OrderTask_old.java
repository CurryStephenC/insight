//package com.wjyoption.quartz.task.order;
//
//import java.math.BigDecimal;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//import java.util.concurrent.TimeUnit;
//
//import org.apache.commons.lang3.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.data.redis.core.BoundValueOperations;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.wjyoption.common.utils.DateUtils;
//import com.wjyoption.common.utils.db.RedisEnum;
//import com.wjyoption.common.utils.http.HttpUtils;
//import com.wjyoption.system.domain.WpProductdata;
//import com.wjyoption.system.domain.WpProductinfo;
//import com.wjyoption.system.service.IWpOrderService;
//import com.wjyoption.system.service.IWpProductdataService;
//import com.wjyoption.system.service.IWpProductinfoService;
//import com.wjyoption.system.vo.report.ProductKlineRedisVo;
//import com.wjyoption.system.vo.report.ProductKlineVo;
//import com.wjyoption.system.vo.report.ProductNowDataVo;
//
//
//@Component("orderTask")
//@EnableScheduling
//public class OrderTask {
//
//	private static Logger logger = LoggerFactory.getLogger("sys_tcp");
//	private static String KLINE_URL = "https://pricecloud.finpoints.com:447/kline";
//	private static long LAST_UPDATE = 0;
//	@Autowired IWpProductinfoService productinfoService;
//	@Autowired IWpProductdataService productdataService;
//	@Autowired IWpOrderService orderService;
//	
//	@Autowired WebsocketClient webSocketClient;
//	@SuppressWarnings("rawtypes")
//	@Autowired RedisTemplate redisTemplate;
//	@Value("${wjyoption.tcpSwitch}")
//	private String tcpSwitch;
//	/**
//	 * 更新K线图
//	 */
//	@Scheduled(fixedDelay=50000)
//	public void updateKline(){
//		if("0".equals(tcpSwitch)){
//			return;
//		}
//		Map<String, ProductNowDataVo> nowMap = webSocketClient.getNowData();
//		long beginTime = System.currentTimeMillis();
//		logger.info("更新k线图");
//		WpProductinfo wpProductinfo = new WpProductinfo();
//		wpProductinfo.setIsdelete(0);
//		List<WpProductinfo> productList = this.productinfoService.selectWpProductinfoList(wpProductinfo);
//		for(WpProductinfo product : productList){
//			if(!productinfoService.canBuy(null, product)){
//				logger.info("产品休市中>" + product.getProcode());
//				continue;
//			}
//			String token = webSocketClient.token();
//			ProductNowDataVo nowData = nowMap.get(product.getPid().toString());
//			BigDecimal nowPrice = null;
//			Long nowTime = null;
//			if(nowData != null){
//				nowPrice = nowData.getBid_price();
//				nowTime = nowData.getTime();
//			}
//			getkdata(product, "1", token,nowPrice,nowTime);
//			getkdata(product, "5", token,nowPrice,nowTime);
//			getkdata(product, "15", token,nowPrice,nowTime);
//			getkdata(product, "30", token,nowPrice,nowTime);
//			getkdata(product, "60", token,nowPrice,nowTime);
//			getkdata(product, "D", token,nowPrice,nowTime);
//		}
//		logger.info("更新k线图结束:spendtime->"+(System.currentTimeMillis()-beginTime));
//	}
//	
////	public static void main(String[] args) {
////		OrderTask order = new OrderTask();
//////		String token = order.updateToken();
////		WpProductinfo vo = new WpProductinfo();
////		vo.setProcode("AUDUSD");
////		order.getkdata(vo, "1", "VGVzdFNob3c=.1625724336.5548324b337731574c7173743f4ad382732e4a960bc21416b61463cb",new BigDecimal(100),DateUtils.getNowSecond());
////	}
////	{"code":0,"data":[{"open":0.74632,"high":0.74649,"low":0.74632,"close":0.74636,"time":1626073560},{"open":0.7466,"high":0.74661,"low":0.74632,"close":0.74632,"time":1626073500}]}
//	@SuppressWarnings("unchecked")
//	private void getkdata(WpProductinfo vo,String interval,String token,BigDecimal price,Long nowSecond){
////		long nowSecond = DateUtils.getNowSecond();
//		if(nowSecond == null){
//			nowSecond = DateUtils.getNowSecond();
//		}
//		String param = "symbol=" + vo.getProcode()+"&period=" + interval + "&from=1550592000&to="+DateUtils.getNowSecond() + "&limit=300";
//		logger.info("kline nowSecond|param->" + nowSecond + "|"+param);
//		Map<String, String> header = new HashMap<String, String>();
//		header.put("X-Auth-Token", token);
//		String res = HttpUtils.sendGet(KLINE_URL, param, header);
//		try {
//			if(StringUtils.isBlank(res)){
//				if(LAST_UPDATE == 0 || System.currentTimeMillis() - LAST_UPDATE > 60000){
//					LAST_UPDATE = System.currentTimeMillis();
//					webSocketClient.updateToken();
//					getkdata(vo, interval, token,price,nowSecond);
//				}
//				return;
//			}
//			JSONObject json = JSON.parseObject(res);
//			if(!json.containsKey("data")){
//				return;
//			}
//			List<ProductKlineVo> array = JSON.parseArray(json.getJSONArray("data").toJSONString(), ProductKlineVo.class);
//			if(array == null || array.size() == 0){
//				return;
//			}
//			String keyPrefix = RedisEnum.PRODUCT_KLINE.getKeyPrefix();
//			String key = keyPrefix + "_" + vo.getPid() + "_" + interval;
//			BoundValueOperations<String,ProductKlineRedisVo> boundValueOps = this.redisTemplate.boundValueOps(key);
//			ProductKlineRedisVo map  = new ProductKlineRedisVo();
//			map.setData(array);
//			map.setProcode(vo.getProcode());
//			map.setPid(vo.getPid());
//			boundValueOps.set(map, 20, TimeUnit.DAYS);
//			if("1".equals(interval)){
//				ProductKlineVo v = array.get(array.size() - 1);
//				WpProductdata wpProductdata = this.productdataService.selectWpProductdataByPid(vo.getPid());
//				wpProductdata.setOpen(v.getOpen());
//				wpProductdata.setClose(v.getClose());
//				wpProductdata.setHigh(v.getHigh());
//				wpProductdata.setLow(v.getLow());
//				wpProductdata.setUpdatetime(nowSecond);
//				wpProductdata.setPrice(price);
//				this.productdataService.updateWpProductdata(wpProductdata);
//			}
//		} catch (Exception e) {
//			logger.error("updateKline data error1...",e);
//			try {
//				if(LAST_UPDATE == 0 || System.currentTimeMillis() - LAST_UPDATE > 60000){
//					LAST_UPDATE = System.currentTimeMillis();
//					webSocketClient.updateToken();
//					getkdata(vo, interval, token,price,nowSecond);
//				}
//			} catch (Exception e1) {
//				logger.error("updateKline data error2...",e1);
//			}
//		}
//	}
//	
//	/**
//	 * 定时
//	 * 更新产品列表
//	 */
//	public void updateWebSocketMap(){
//		webSocketClient.updateProductMap();
//	}
//	
//	/**
//	 * 定时
//	 * 十秒或其他时间更新数据库
//	 */
//	public void updateProductNowData(){
//		Map<String, ProductNowDataVo> map = webSocketClient.getNowData();
//		Set<String> keySet = map.keySet();
//		for(String pid:keySet){
//			ProductNowDataVo nowData = map.get(pid);
//			WpProductdata wpProductdata = new WpProductdata();
//			wpProductdata.setPid(Integer.valueOf(pid));
//			wpProductdata.setPrice(nowData.getBid_price());
//			wpProductdata.setUpdatetime(nowData.getTime());
//			this.productdataService.updateWpProductdataByPid(wpProductdata);
//		}
//	}
//	
//	
//	@Scheduled(fixedDelay=3000)
//	public void overOrder(){
//		if("0".equals(tcpSwitch)){
//			return;
//		}
//		long begin = System.currentTimeMillis();
//		logger.info("结算订单开始");
//		Map<String, ProductNowDataVo> nowData = this.webSocketClient.getNowData();
//		this.orderService.overOrder(nowData);
//		logger.info("结算订单结束 spend->" + (System.currentTimeMillis()-begin));
//		
//	}
//}