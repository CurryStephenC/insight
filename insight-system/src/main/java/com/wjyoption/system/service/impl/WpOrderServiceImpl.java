package com.wjyoption.system.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wjyoption.common.core.domain.Response;
import com.wjyoption.common.core.text.Convert;
import com.wjyoption.common.enums.ErrorConstants;
import com.wjyoption.common.utils.DateUtils;
import com.wjyoption.common.utils.RandUtil;
import com.wjyoption.common.utils.db.RedisEnum;
import com.wjyoption.system.domain.WpConfig;
import com.wjyoption.system.domain.WpFinancialRecord;
import com.wjyoption.system.domain.WpOrder;
import com.wjyoption.system.domain.WpProductinfo;
import com.wjyoption.system.domain.WpRisk;
import com.wjyoption.system.domain.WpUserinfo;
import com.wjyoption.system.mapper.WpOrderMapper;
import com.wjyoption.system.service.IWpCashFlowService;
import com.wjyoption.system.service.IWpConfigService;
import com.wjyoption.system.service.IWpFinancialRecordService;
import com.wjyoption.system.service.IWpOrderService;
import com.wjyoption.system.service.IWpPriceLogService;
import com.wjyoption.system.service.IWpProductdataService;
import com.wjyoption.system.service.IWpProductinfoService;
import com.wjyoption.system.service.IWpRiskService;
import com.wjyoption.system.service.IWpUserinfoService;
import com.wjyoption.system.vo.RiskChanceVo;
import com.wjyoption.system.vo.report.ProductNowDataVo;
import com.wjyoption.system.vo.resp.FinancialRecordResp;
import com.wjyoption.system.vo.resp.OrderResp;

/**
 * 订单模块Service业务层处理
 * 
 * @author wjyoption
 * @date 2021-07-08
 */
@Service
public class WpOrderServiceImpl implements IWpOrderService 
{
	private static Logger logger = LoggerFactory.getLogger(WpOrderServiceImpl.class);
    @Autowired
    private WpOrderMapper wpOrderMapper;
    @Autowired IWpConfigService wpConfigService;
    @Autowired IWpUserinfoService userinfoService;
    @Autowired IWpProductinfoService productinfoService;
    @Autowired IWpProductdataService productdataService;
    @Autowired IWpPriceLogService priceLogService;
    @Autowired IWpCashFlowService cashFlowService;
    @Autowired IWpRiskService riskService;
    @Autowired IWpFinancialRecordService financialRecordService;
    
    @SuppressWarnings("rawtypes")
	@Autowired RedisTemplate redisTemplate;
    

    /**
     * 查询订单模块
     * 
     * @param oid 订单模块ID
     * @return 订单模块
     */
    @Override
    public WpOrder selectWpOrderById(Integer oid)
    {
        return wpOrderMapper.selectWpOrderById(oid);
    }

    /**
     * 查询订单模块列表
     * 
     * @param wpOrder 订单模块
     * @return 订单模块
     */
    @Override
    public List<WpOrder> selectWpOrderList(WpOrder wpOrder)
    {
        return wpOrderMapper.selectWpOrderList(wpOrder);
    }
    
    public Map<String, Object> selectWpOrderListTotal(WpOrder wpOrder){
    	return wpOrderMapper.selectWpOrderListTotal(wpOrder);
    }

    /**
     * 新增订单模块
     * 
     * @param wpOrder 订单模块
     * @return 结果
     */
    @Override
    public int insertWpOrder(WpOrder wpOrder)
    {
        return wpOrderMapper.insertWpOrder(wpOrder);
    }

    /**
     * 修改订单模块
     * 
     * @param wpOrder 订单模块
     * @return 结果
     */
    @Override
    public int updateWpOrder(WpOrder wpOrder)
    {
        return wpOrderMapper.updateWpOrder(wpOrder);
    }

    /**
     * 删除订单模块对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWpOrderByIds(String ids)
    {
        return wpOrderMapper.deleteWpOrderByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除订单模块信息
     * 
     * @param oid 订单模块ID
     * @return 结果
     */
    public int deleteWpOrderById(Integer oid)
    {
        return wpOrderMapper.deleteWpOrderById(oid);
    }

	@Override
	public List<OrderResp> selectOrderList(WpOrder order) {
		return this.wpOrderMapper.selectOrderList(order);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public void placeOrder(OrderResp order, Response<String> response) {
		Integer uid = order.getUid();
		if(uid == null){
			ErrorConstants.setResponse(response, ErrorConstants.NO_LOGIN);
			return;
		}
		if(!productinfoService.canBuy(order.getPid(), null)){
			ErrorConstants.setResponse(response, ErrorConstants.PRODUCT_CLOSED);
			return;
		}
		Map<String, WpConfig> map = this.wpConfigService.selectAll();
		WpConfig orderMinPrice = map.get("order_min_price");
		WpConfig maxCount = map.get("max_order_count");
		WpConfig orderMaxPrice = map.get("order_max_price");
		WpConfig orderMaxPriceUserinfo = map.get("order_max_price_userinfo_switch");//下单金额限制开关
		WpConfig orderMaxDailycount = map.get("order_max_dailycount");//每日下单限制数量
		WpConfig totalPricePercent = map.get("order_totalprice_percent");//每日下单总额限制比例(余额) 10%
		WpConfig totalPricePercent_financial = map.get("order_totalprice_percent_financial");//每日下单总额限制比例(余额) 10%
		int maxDailyCount = orderMaxDailycount != null ? Integer.valueOf(orderMaxDailycount.getValue()) : 10;
		String key = RedisEnum.PRODUCT_DAYLI_LIMIT.getKeyPrefix() + ":" + DateUtils.dateTimeNow(DateUtils.YYYYMMDD) + ":" +uid;
		BoundValueOperations<String, Integer> boundValueOps = this.redisTemplate.boundValueOps(key);
		Integer count = boundValueOps.get();
		if(count == null) count = 0;
		if(count >= maxDailyCount){
			ErrorConstants.setResponse(response, ErrorConstants.ORDER_DAILY_LIMIT);
			return;
		}
		
		WpOrder wpOrder = new WpOrder();
		wpOrder.setOstaus(0);
		wpOrder.setUid(uid);
		List<WpOrder> list = this.wpOrderMapper.selectWpOrderList(wpOrder);
		try {
			if(maxCount != null && list.size() >= Integer.valueOf(maxCount.getValue())){
				ErrorConstants.setResponse(response, ErrorConstants.ORDER_MAX_COUNT,maxCount.getValue());
				return;
			}
			
		} catch (Exception e) {
			logger.error("placeorder maxcount error...",e);
		}
		//最小下单金额
		if(orderMinPrice != null){
			try {
				if(order.getFee().compareTo(new BigDecimal(orderMinPrice.getValue())) < 0){
					ErrorConstants.setResponse(response, ErrorConstants.MONEY_TOO_SMALL);
					return;
				}
			} catch (Exception e) {
				logger.error("placeorder minprice error...",e);
			}
		}
		WpUserinfo userinfo = this.userinfoService.selectWpUserinfoById(uid);
		if(userinfo == null || userinfo.getUstatus() != 0){
			ErrorConstants.setResponse(response, ErrorConstants.USER_FREEZE);
			return;
		}
		//下单金额(最大)限制
		if(orderMaxPriceUserinfo != null && "1".equals(orderMaxPriceUserinfo.getValue())){
			if(order.getFee().compareTo(userinfo.getOrdermaxprice()) > 0){
				ErrorConstants.setResponse(response, ErrorConstants.BLANK_ERROR,"Your maximum order amount is:"+userinfo.getOrdermaxprice().toString()+".Your maximum order amount cannot be greater than 1% of your purchase financial management amount");
				return;
			}
		}else if(orderMaxPrice != null){
			try {
				BigDecimal feeTotal = new BigDecimal(0);
				for(WpOrder bean : list){
					feeTotal = feeTotal.add(bean.getFee());
				}
				if(feeTotal.compareTo(new BigDecimal(orderMaxPrice.getValue())) > 0){
					ErrorConstants.setResponse(response, ErrorConstants.ORDER_MAX_PRICE,maxCount.getValue());
					return;
				}
			} catch (Exception e) {
				logger.error("placeorder maxprice error...",e);
			}
		}
		//每日投资总额不能超过理财的x%+余额的y%
		if((totalPricePercent != null && totalPricePercent.getStatus() == 1) 
				|| (totalPricePercent_financial != null && totalPricePercent_financial.getStatus() == 1)
				){
			BigDecimal totalPrice = new BigDecimal(0);
			
			if(totalPricePercent != null && totalPricePercent.getStatus() == 1){
				totalPrice = userinfo.getUsermoney().multiply(new BigDecimal(totalPricePercent.getValue()));
			}
			if(totalPricePercent_financial != null && totalPricePercent_financial.getStatus() == 1){
				WpFinancialRecord wpFinancialRecord = new WpFinancialRecord();
				wpFinancialRecord.setUid(uid);
				wpFinancialRecord.setStatus(0);
				List<FinancialRecordResp> recordList = this.financialRecordService.selectFinancialRecordList(wpFinancialRecord);
				BigDecimal financialPrice = new BigDecimal(0);
				for(FinancialRecordResp record : recordList){
					financialPrice = financialPrice.add(record.getBuymoney());
				}
				totalPrice = totalPrice.add(financialPrice.multiply(new BigDecimal(totalPricePercent_financial.getValue())));
			}
			
			Map<String, Object> params = new HashMap<>();
			params.put("beginTime", DateUtils.dateTimeNow(DateUtils.YYYYMMDD));
			WpOrder todayParam = new WpOrder();
			todayParam.setUid(uid);
			todayParam.setParams(params);
			List<WpOrder> todayList = this.wpOrderMapper.selectWpOrderList(todayParam);
			BigDecimal todayPrice = order.getFee();
			for(WpOrder o : todayList){
				todayPrice = todayPrice.add(o.getFee());
			}
			logger.info("totalMoney:::" + totalPrice.toString());
			logger.info("todayPrice:::" + todayPrice.toString());
			logger.info("totalMoney - todayPrice:::" + totalPrice.compareTo(todayPrice));
			if(totalPrice.compareTo(todayPrice) < 0){
				ErrorConstants.setResponse(response, ErrorConstants.ORDER_MAXPRICE_DAY);
				return;
			}
		}
		WpOrder add = new WpOrder();
		add.setUid(uid);
		add.setPid(order.getPid());
		add.setOstyle(order.getOstyle());
		add.setBuyprice(order.getBuyprice());
		add.setEid(new BigDecimal(2));
		add.setOstaus(0);
		WpProductinfo product = this.productinfoService.selectWpProductinfoById(order.getPid());
		String endprofit = order.getEndprofit();
		
		String[] proscale = product.getProscale().split(",");//75,77,80,85
		String[] protime = product.getProtime().split(",");//0.5,1,2,3
		long buytime = DateUtils.getNowSecond();
		add.setBuytime(buytime);
		for(int i =0;i < protime.length;i++){
			if(new BigDecimal(protime[i]).multiply(new BigDecimal(60)).compareTo(new BigDecimal(endprofit)) == 0){
				add.setEndloss(Integer.valueOf(proscale[i]));
				BigDecimal time = new BigDecimal(endprofit);
				add.setEndprofit(time.toString());
				add.setSelltime(buytime + time.longValue());
				break;
			}
		}
		//数据不对，无法提交
		if(add.getSelltime() == null){
			ErrorConstants.setResponse(response, ErrorConstants.FAIL);
			return;
		}
//		WpConfig webPoundage = map.get("web_poundage");
		BigDecimal sxFee = new BigDecimal(0);
//		if(webPoundage != null && StringUtils.isNotBlank(webPoundage.getValue())){
//			sxFee = order.getFee().multiply(new BigDecimal(webPoundage.getValue())).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP);
//		}
		if(userinfo.getUsermoney().compareTo(order.getFee().add(sxFee)) < 0){
			ErrorConstants.setResponse(response, ErrorConstants.WALLET_SMALL);
			return;
		}
		BigDecimal allFee = order.getFee().add(sxFee);
		add.setFee(order.getFee());
		add.setOrderno(uid + "" +System.currentTimeMillis() + RandUtil.getRand(2));
		add.setPtitle(product.getPtitle());
		add.setCommission(userinfo.getUsermoney().subtract(allFee));
		add.setIsshow(1);
		add.setSxFee(sxFee);
		this.wpOrderMapper.insertWpOrder(add);
		this.userinfoService.updateMoney(allFee.multiply(new BigDecimal(-1)), uid);
		this.priceLogService.insertWpPriceLog(uid, allFee.toString(), 2, "Place order", "Your order has been placed successfully", add.getOid(), add.getCommission());
		boundValueOps.set(++count, 24, TimeUnit.HOURS);
	}
	
	public static void main(String[] args) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		System.out.println(calendar.getTimeInMillis());
	}

	@Override
	public void overOrder(Map<String, ProductNowDataVo> nowData) {
		long nowTime = DateUtils.getNowSecond();
		WpOrder wpOrder = new WpOrder();
		wpOrder.setSelltime(nowTime);
		wpOrder.setOstaus(0);
		List<WpOrder> list = this.wpOrderMapper.selectWpOrderList(wpOrder);
		if(list.size() == 0){
			logger.info("没有需要处理订单。。");
			return;
		}
		List<WpRisk> riskList = this.riskService.selectWpRiskList(new WpRisk());
		WpRisk risk = riskList.get(0);
		String[] toWin = risk.getToWin().split("\\|");
		String[] toLoss = risk.getToLoss().split("\\|");
		String[] chances = risk.getChance().split("\\|");//10-20:40|20-100:30|100-500:30|500-1000:10|1000-1000000:0
		List<Integer> winList = new ArrayList<>();
		List<Integer> lossList = new ArrayList<>();
		List<RiskChanceVo> chanceList = new ArrayList<>();
		for(String win : toWin) {
			if(StringUtils.isNotBlank(win)){
				try {
					winList.add(Integer.valueOf(win));
				} catch (Exception e) {
				}
			}
		}
		for(String loss : toLoss) {
			if(StringUtils.isNotBlank(loss)){
				try {
					lossList.add(Integer.valueOf(loss));
				} catch (Exception e) {
				}
			}
		}
		for(String c : chances){
			if(StringUtils.isBlank(c)) continue;
			String[] cs = c.split(":");
			if(cs.length < 2) continue;
			String[] price = cs[0].split("-");
			if(price.length < 2) continue;
			RiskChanceVo vo = new RiskChanceVo();
			try {
				vo.setMinPrice(new BigDecimal(price[0]));
				vo.setMaxPrice(new BigDecimal(price[1]));
				vo.setWinChance(Integer.valueOf(cs[1]));
				chanceList.add(vo);
			} catch (Exception e) {
			}
		}
		WpProductinfo wpProductinfo = new WpProductinfo();
		wpProductinfo.setIsdelete(0);
		List<WpProductinfo> allProductList = this.productinfoService.selectWpProductinfoList(wpProductinfo);
		Map<String, WpProductinfo> productMap = new HashMap<>();
		allProductList.forEach(obj -> {
			productMap.put(obj.getPid().toString(), obj);
		});
		list.forEach(obj -> {
			ProductNowDataVo now = nowData.get(obj.getPid().toString());
			WpProductinfo productResp = productMap.get(obj.getPid().toString());
			over(obj,winList,lossList,chanceList,now,productResp);
		});
		logger.info("处理订单数-》"+list.size());
	}
	
	@Transactional
	private void over(WpOrder order,List<Integer> winList,List<Integer> lossList,List<RiskChanceVo> chanceList,ProductNowDataVo now,WpProductinfo product){
		if(now == null || product == null) return;
		if(now.getBid_price().compareTo(new BigDecimal(0)) <= 0) return;
		BigDecimal zero = new BigDecimal(0);
//		boolean needKong = true;
		int win = 0;//1、盈利，2、亏损
		BigDecimal sellPrice = now.getBid_price();
		BigDecimal buyprice = order.getBuyprice();
		BigDecimal fee = order.getFee();
		BigDecimal point_low = rand(product.getPointLow(), product.getPointTop());
		if(order.getKongType() != 0){//指定方向
			if(order.getKongType() == 1){//盈利
				sellPrice = win(order, sellPrice, buyprice, point_low);
				win = 1;
			}else{//亏损
				sellPrice = loss(order, sellPrice, buyprice, point_low);
				win = 2;
			}
		}else{
			if(winList.contains(order.getUid())){
				sellPrice = win(order, sellPrice, buyprice, point_low);
				win = 1;
			}else if(lossList.contains(order.getUid())){
				sellPrice = loss(order, sellPrice, buyprice, point_low);
				win = 2;
			}else if(chanceList.size() > 0){
				for(RiskChanceVo chance : chanceList){
					if(fee.compareTo(chance.getMinPrice()) >= 0 && fee.compareTo(chance.getMaxPrice()) < 0){
						int _rand = new Random().nextInt(100)+1;
//						if(chance.getWinChance() == 0){
//						}else 
						if(_rand <= chance.getWinChance()){
							sellPrice = win(order, sellPrice, buyprice, point_low);
							win = 1;
						}else{
							sellPrice = loss(order, sellPrice, buyprice, point_low);
							win = 2;
						}
						break;
					}
				}
			}
		}
		order.setOstaus(1);
		order.setSellprice(sellPrice);
		BigDecimal yingli = fee.multiply(new BigDecimal(order.getEndloss())).divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP);
		if(win == 1){//盈利
			order.setIsWin(1);
		}else if(win == 2){//亏
			order.setIsWin(2);
			yingli = fee.multiply(new BigDecimal(-1));
		}else{
			BigDecimal order_cha = sellPrice.subtract(buyprice);
			if(order.getOstyle() == 0){//涨
				if(order_cha.compareTo(zero) > 0){//赢
					order.setIsWin(1);
				}else if(order_cha.compareTo(zero) < 0){//亏
					order.setIsWin(2);
					yingli = fee.multiply(new BigDecimal(-1));
				}else{
					yingli = new BigDecimal(0);
					order.setIsWin(3);
				}
			}else if(order.getOstyle() == 1){//跌
				if(order_cha.compareTo(zero) < 0){//赢
					order.setIsWin(1);
				}else if(order_cha.compareTo(zero) > 0){//亏
					order.setIsWin(2);
					yingli = fee.multiply(new BigDecimal(-1));
				}else{
					yingli = new BigDecimal(0);
					order.setIsWin(3);
				}
			}else{
				logger.info("未知状态》" + order.getOstyle());
				return;
			}
		}
		BigDecimal updateMoney = new BigDecimal(0);
		WpUserinfo userinfo = this.userinfoService.selectWpUserinfoById(order.getUid());
		BigDecimal usermoney = userinfo.getUsermoney();
		if(order.getIsWin() == 1){
			updateMoney = yingli.add(fee);
			usermoney = usermoney.add(updateMoney);
			this.userinfoService.updateMoney(updateMoney, order.getUid());
		}else if(order.getIsWin() == 3){
			updateMoney = fee;
			usermoney = usermoney.add(updateMoney);
			this.userinfoService.updateMoney(updateMoney, order.getUid());
		}
		order.setPloss(yingli);
		this.wpOrderMapper.updateWpOrder(order);
		this.priceLogService.insertWpPriceLog(order.getUid(), updateMoney.toString(), 1, "Settlement", "Profit will be calculated on settlement date.", order.getOid(), usermoney);
		if(order.getIsWin() == 1){
			this.cashFlowService.insertWpCashFlow(order.getUid(), 2, yingli.toString(), "投资盈利", usermoney);
		}else if(order.getIsWin() == 2){
			this.cashFlowService.insertWpCashFlow(order.getUid(), 3, yingli.toString(), "投资亏损", usermoney);
//		}else if(order.getIsWin() == 3){
//			this.cashFlowService.insertWpCashFlow(order.getUid(), 3, yingli.toString(), "投资无效(手续费)", usermoney);
		}
	}

	//亏损
	private BigDecimal loss(WpOrder order, BigDecimal sellPrice, BigDecimal buyprice,
			BigDecimal point_low) {
		if(order.getOstyle() == 0 && sellPrice.compareTo(buyprice) >= 0){//涨
			sellPrice = buyprice.subtract(point_low);
		}else if(order.getOstyle() == 1 && sellPrice.compareTo(buyprice) <= 0){//跌
			sellPrice = buyprice.add(point_low);
		}
		return sellPrice;
	}

	//盈利
	private BigDecimal win(WpOrder order, BigDecimal sellPrice, BigDecimal buyprice,
			BigDecimal point_low) {
		if(order.getOstyle() == 0 && sellPrice.compareTo(buyprice) <= 0){//涨
			sellPrice = buyprice.add(point_low);
		}else if(order.getOstyle() == 1 && sellPrice.compareTo(buyprice) >= 0){//跌
			sellPrice = buyprice.subtract(point_low);
		}
		return sellPrice;
	}
	
	//获取随机数
	private static BigDecimal rand(String pointLow,String pointTop){
		try {
			int lowLen = pointLow.split("\\.").length > 1 ? pointLow.split("\\.")[1].length() : 0;
			int topLen = pointTop.split("\\.").length > 1 ? pointTop.split("\\.")[1].length() : 0;
			BigDecimal pow = lowLen > topLen ? new BigDecimal(10).pow(lowLen) : new BigDecimal(10).pow(topLen);
			BigDecimal low = new BigDecimal(pointLow).multiply(pow);
			BigDecimal top = new BigDecimal(pointTop).multiply(pow);
			if(low.compareTo(top) > 0){
				BigDecimal center = low;
				low = top;
				top = center;
			}
			int nextInt = new Random().nextInt(top.subtract(low).add(new BigDecimal(1)).intValue()) + low.intValue();
			return new BigDecimal(nextInt).divide(pow);
		} catch (Exception e) {
		}
		return new BigDecimal(pointLow);
	}
}
