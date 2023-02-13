package com.wjyoption.quartz.task;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wjyoption.common.utils.DateUtils;
import com.wjyoption.system.domain.CodepayOrder;
import com.wjyoption.system.domain.WpBalance;
import com.wjyoption.system.domain.WpFinancialRecord;
import com.wjyoption.system.domain.WpFinancialType;
import com.wjyoption.system.domain.WpRedEnvelope;
import com.wjyoption.system.domain.WpStatRecord;
import com.wjyoption.system.domain.WpTopStat;
import com.wjyoption.system.domain.WpUserinfo;
import com.wjyoption.system.domain.WpYieldRate;
import com.wjyoption.system.service.ICodepayOrderService;
import com.wjyoption.system.service.IWpBalanceService;
import com.wjyoption.system.service.IWpCashFlowService;
import com.wjyoption.system.service.IWpFinancialRecordService;
import com.wjyoption.system.service.IWpFinancialTypeService;
import com.wjyoption.system.service.IWpPriceLogService;
import com.wjyoption.system.service.IWpRedEnvelopeService;
import com.wjyoption.system.service.IWpStatRecordService;
import com.wjyoption.system.service.IWpTopStatService;
import com.wjyoption.system.service.IWpUserinfoService;
import com.wjyoption.system.service.IWpYieldRateService;


@Component("financialTask")
public class FinancialTask {

	private static Logger logger = LoggerFactory.getLogger(FinancialTask.class);
	
	@Autowired IWpYieldRateService yieldRateService;
	@Autowired IWpFinancialTypeService typeService;
	@Autowired IWpFinancialRecordService recordService;
	@Autowired IWpUserinfoService userinfoService;
	@Autowired IWpCashFlowService cashFlowService;
	@Autowired IWpPriceLogService priceLogService;
	@Autowired IWpStatRecordService statService;
	@Autowired IWpRedEnvelopeService redEnvelopeService;
	@Autowired ICodepayOrderService payOrderService;
	@Autowired IWpBalanceService balanceService;
	@Autowired IWpTopStatService topStatService;
	
	public static void main(String[] args) {
		long now = 1664853714000l;
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(now);
		int week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		int daily1 = Integer.valueOf(DateUtils.parseDateToStr(DateUtils.YYYYMMDD, new Date(now+86400000*(7-week+1))));
		int daily2 = Integer.valueOf(DateUtils.parseDateToStr(DateUtils.YYYYMMDD, new Date(now+86400000*(7-week+2))));
		int daily3 = Integer.valueOf(DateUtils.parseDateToStr(DateUtils.YYYYMMDD, new Date(now+86400000*(7-week+3))));
		int daily4 = Integer.valueOf(DateUtils.parseDateToStr(DateUtils.YYYYMMDD, new Date(now+86400000*(7-week+4))));
		int daily5 = Integer.valueOf(DateUtils.parseDateToStr(DateUtils.YYYYMMDD, new Date(now+86400000*(7-week+5))));
		
		System.out.println(daily1);
		System.out.println(daily2);
		System.out.println(daily3);
		System.out.println(daily4);
		System.out.println(daily5);
		
//		WpFinancialType bean = new WpFinancialType();
//		bean.setRete(0.016);
//		bean.setFreezedate(4);
//		BigDecimal fiveZero = new BigDecimal(100000);
//		BigDecimal addOrSubstract = BigDecimal.valueOf(0.0005);
//		for(int i = 0;i < 10;i++){
//			Random random = new Random();
//			BigDecimal rete = BigDecimal.valueOf(bean.getRete()+0.01).divide(new BigDecimal(bean.getFreezedate()*5).divide(new BigDecimal(7),8,BigDecimal.ROUND_HALF_UP),5,BigDecimal.ROUND_HALF_UP);
//			int max = 0;
//			int min = 0;
//			try {
//				if(bean.getFreezedate().intValue() < 6){
//					rete = BigDecimal.valueOf(bean.getRete()).divide(new BigDecimal(bean.getFreezedate()),5,BigDecimal.ROUND_HALF_UP);
//				}
//				max = rete.add(addOrSubstract).multiply(fiveZero).intValue();
//				min = rete.subtract(addOrSubstract).multiply(fiveZero).intValue();
//				if(min<0){
//					min = 0;
//				}
//				System.out.println(rete);
//			} catch (Exception e) {
//				logger.error("addYieldRate 计算收益率错误...",e);
//			}
//			BigDecimal r1 = new BigDecimal(random.nextInt(max-min+1) + min).divide(fiveZero);
////			if(random.nextInt(2) == 1){
////				r1 = r1.add(rete);
////			}else{
////				r1 = r1.subtract(rete);
////			}
//			BigDecimal r2 = rete.multiply(new BigDecimal(2)).subtract(r1);
//			
//			BigDecimal r3 = new BigDecimal(random.nextInt(max-min+1) + min).divide(fiveZero);
////			if(random.nextInt(2) == 1){
////				r3 = rete.add(r3);
////			}else{
////				r3 = rete.subtract(r3);
////			}
//			BigDecimal r4 = rete.multiply(new BigDecimal(2)).subtract(r3);
//			BigDecimal r5 = new BigDecimal(random.nextInt(max-min+1) + min).divide(fiveZero);
//			System.out.println(r1+"|" + r2 + "|" + r3 + "|" + r4 + "|" + r5 + "-------" + (r1.add(r2).add(r3).add(r4).add(r5)));
//		}
	}
	/**
	 * 添加收益率
	 */
	public void addYieldRate(Long now){
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(now);
		int week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
//		long now = System.currentTimeMillis();
		int daily1 = Integer.valueOf(DateUtils.parseDateToStr(DateUtils.YYYYMMDD, new Date(now+86400000*(7-week+1))));
		int daily2 = Integer.valueOf(DateUtils.parseDateToStr(DateUtils.YYYYMMDD, new Date(now+86400000*(7-week+2))));
		int daily3 = Integer.valueOf(DateUtils.parseDateToStr(DateUtils.YYYYMMDD, new Date(now+86400000*(7-week+3))));
		int daily4 = Integer.valueOf(DateUtils.parseDateToStr(DateUtils.YYYYMMDD, new Date(now+86400000*(7-week+4))));
		int daily5 = Integer.valueOf(DateUtils.parseDateToStr(DateUtils.YYYYMMDD, new Date(now+86400000*(7-week+5))));
		List<WpFinancialType> list = this.typeService.selectWpFinancialTypeList(null);
		BigDecimal fiveZero = new BigDecimal(100000);
		BigDecimal addOrSubstract = BigDecimal.valueOf(0.0005);
		Random random = new Random();
		for(WpFinancialType bean : list){
			BigDecimal rete = BigDecimal.valueOf(bean.getRete()+0.01).divide(new BigDecimal(bean.getFreezedate()*5).divide(new BigDecimal(7),8,BigDecimal.ROUND_HALF_UP),5,BigDecimal.ROUND_HALF_UP);
			int max = 0;
			int min = 0;
			try {
				if(bean.getFreezedate().intValue() < 6){
					rete = BigDecimal.valueOf(bean.getRete()).divide(new BigDecimal(bean.getFreezedate()),5,BigDecimal.ROUND_HALF_UP);
				}
				max = rete.add(addOrSubstract).multiply(fiveZero).intValue();
				min = rete.subtract(addOrSubstract).multiply(fiveZero).intValue();
				if(min<0){
					min = 0;
				}
			} catch (Exception e) {
				logger.error("addYieldRate 计算收益率错误...",e);
			}
			BigDecimal r1 = new BigDecimal(random.nextInt(max-min+1) + min).divide(fiveZero);
//			if(random.nextInt(2) == 1){
//				r1 = r1.add(rete);
//			}else{
//				r1 = r1.subtract(rete);
//			}
			BigDecimal r2 = rete.multiply(new BigDecimal(2)).subtract(r1);
			
			BigDecimal r3 = new BigDecimal(random.nextInt(max-min+1) + min).divide(fiveZero);
//			if(random.nextInt(2) == 1){
//				r3 = rete.add(r3);
//			}else{
//				r3 = rete.subtract(r3);
//			}
			BigDecimal r4 = rete.multiply(new BigDecimal(2)).subtract(r3);
			BigDecimal r5 = new BigDecimal(random.nextInt(max-min+1) + min).divide(fiveZero);
//			if(random.nextInt(2) == 1){
//				r5 = rete.add(r5.multiply(new BigDecimal(-1)));
//			}else{
//				r5 = rete.add(r5);
//			}
			int s1 = insertYieldRate(bean.getId(), r1, daily1);
			int s2 = insertYieldRate(bean.getId(), r2, daily2);
			int s3 = insertYieldRate(bean.getId(), r3, daily3);
			int s4 = insertYieldRate(bean.getId(), r4, daily4);
			int s5 = insertYieldRate(bean.getId(), r5, daily5);
			logger.info("成功记录数量->" + bean.getName() + "|" + (s1+s2+s3+s4+s5));
		}
	}
	public void addYieldRate(){
		Calendar calendar = Calendar.getInstance();
		int week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		long now = System.currentTimeMillis();
		int daily1 = Integer.valueOf(DateUtils.parseDateToStr(DateUtils.YYYYMMDD, new Date(now+86400000*(7-week+1))));
		int daily2 = Integer.valueOf(DateUtils.parseDateToStr(DateUtils.YYYYMMDD, new Date(now+86400000*(7-week+2))));
		int daily3 = Integer.valueOf(DateUtils.parseDateToStr(DateUtils.YYYYMMDD, new Date(now+86400000*(7-week+3))));
		int daily4 = Integer.valueOf(DateUtils.parseDateToStr(DateUtils.YYYYMMDD, new Date(now+86400000*(7-week+4))));
		int daily5 = Integer.valueOf(DateUtils.parseDateToStr(DateUtils.YYYYMMDD, new Date(now+86400000*(7-week+5))));
		List<WpFinancialType> list = this.typeService.selectWpFinancialTypeList(null);
		BigDecimal fiveZero = new BigDecimal(100000);
		BigDecimal addOrSubstract = BigDecimal.valueOf(0.0005);
		Random random = new Random();
		for(WpFinancialType bean : list){
			BigDecimal rete = BigDecimal.valueOf(bean.getRete()+0.01).divide(new BigDecimal(bean.getFreezedate()*5).divide(new BigDecimal(7),8,BigDecimal.ROUND_HALF_UP),5,BigDecimal.ROUND_HALF_UP);
			int max = 0;
			int min = 0;
			try {
				if(bean.getFreezedate().intValue() < 6){
					rete = BigDecimal.valueOf(bean.getRete()).divide(new BigDecimal(bean.getFreezedate()),5,BigDecimal.ROUND_HALF_UP);
				}
				max = rete.add(addOrSubstract).multiply(fiveZero).intValue();
				min = rete.subtract(addOrSubstract).multiply(fiveZero).intValue();
				if(min<0){
					min = 0;
				}
			} catch (Exception e) {
				logger.error("addYieldRate 计算收益率错误...",e);
			}
			BigDecimal r1 = new BigDecimal(random.nextInt(max-min+1) + min).divide(fiveZero);
//			if(random.nextInt(2) == 1){
//				r1 = r1.add(rete);
//			}else{
//				r1 = r1.subtract(rete);
//			}
			BigDecimal r2 = rete.multiply(new BigDecimal(2)).subtract(r1);
			
			BigDecimal r3 = new BigDecimal(random.nextInt(max-min+1) + min).divide(fiveZero);
//			if(random.nextInt(2) == 1){
//				r3 = rete.add(r3);
//			}else{
//				r3 = rete.subtract(r3);
//			}
			BigDecimal r4 = rete.multiply(new BigDecimal(2)).subtract(r3);
			BigDecimal r5 = new BigDecimal(random.nextInt(max-min+1) + min).divide(fiveZero);
//			if(random.nextInt(2) == 1){
//				r5 = rete.add(r5.multiply(new BigDecimal(-1)));
//			}else{
//				r5 = rete.add(r5);
//			}
			int s1 = insertYieldRate(bean.getId(), r1, daily1);
			int s2 = insertYieldRate(bean.getId(), r2, daily2);
			int s3 = insertYieldRate(bean.getId(), r3, daily3);
			int s4 = insertYieldRate(bean.getId(), r4, daily4);
			int s5 = insertYieldRate(bean.getId(), r5, daily5);
			logger.info("成功记录数量->" + bean.getName() + "|" + (s1+s2+s3+s4+s5));
		}
	}
	private int insertYieldRate(Integer typeid,BigDecimal rate,Integer daily){
		WpYieldRate wpYieldRate = new WpYieldRate();
		wpYieldRate.setTypeid(typeid);
		wpYieldRate.setDaily(daily);
		List<WpYieldRate> list = this.yieldRateService.selectWpYieldRateList(wpYieldRate);
		if(CollectionUtils.isNotEmpty(list)){
			return 0;
		}
		wpYieldRate.setRate(rate);
		this.yieldRateService.insertWpYieldRate(wpYieldRate);
		return 1;
	}
	
	/**
	 * 手动赎回的产品
	 * @return
	 */
	public int overself(){
		WpFinancialRecord wpFinancialRecord = new WpFinancialRecord();
		wpFinancialRecord.setOverself(2);
		wpFinancialRecord.setStatus(0);
		List<WpFinancialRecord> list = this.recordService.selectWpFinancialRecordList(wpFinancialRecord);
		if(CollectionUtils.isEmpty(list)) return 0;
		for(WpFinancialRecord record : list){
			record.setStatus(1);
			record.setOverself(3);
			this.recordService.financialOver(record);
		}
		return list.size();
	}
	
	/**
	 * 更新收益
	 */
	public String updateProfit(){
		logger.info("更新收益begin");
		int retCount = this.recordService.updateProfit();
		logger.info("更新收益end:记录数-》"+retCount);
		return "记录条数："+retCount;
	}
	/**
	 * 更新指定日期收益
	 * yyyyMMdd
	 */
	public String updateProfit(Integer date){
		logger.info("更新收益2begin");
		int retCount = this.recordService.updateProfit(date);
		logger.info("更新收益2end:记录数-》"+retCount);
		return "记录条数："+retCount;
	}
	
	/**
     * 理财资金大于1000，推荐3个有效客户(投资大于500)则拥有ib身份
     */
	public void updateib(){
		long beginTime = System.currentTimeMillis();
		logger.info("更新IB身份开始");
		this.userinfoService.updateIbStatus();
		logger.info("更新IB身份结束:spendtime->"+(System.currentTimeMillis()-beginTime));
	}
	
	/**
	 * 更新下单金额限制
	 */
	public void updateOrderPrice(){
		long beginTime = System.currentTimeMillis();
		logger.info("更新下单金额限制开始");
		this.userinfoService.updateOrderPrice();
		logger.info("更新下单金额限制结束:spendtime->"+(System.currentTimeMillis()-beginTime));
	}
	
	/**
	 * 处理理财信息
	 */
	public void updatefinancial(){
		long beginTime = System.currentTimeMillis();
		logger.info("处理理财信息开始");
		int count = this.recordService.updatefinancial();
		logger.info("处理理财信息结束:"+count + "--"+(System.currentTimeMillis()-beginTime));
	}
	
	public void statdaily(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        int endtime = Integer.valueOf(DateUtils.parseDateToStr(DateUtils.YYYYMMDD, calendar.getTime()));
        
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        int begintime = Integer.valueOf(DateUtils.parseDateToStr(DateUtils.YYYYMMDD, calendar.getTime()));
        WpStatRecord bean = this.statService.selectWpStatRecordByDaily(begintime);
        if(bean != null){
        	logger.info("已经统计过");
        	return;
        }
        bean = new WpStatRecord();
        bean.setDaily(begintime);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("beginTime", begintime);
        params.put("endTime", endtime);
        //注册用户数
        WpUserinfo wpUserinfo = new WpUserinfo();
        wpUserinfo.setParams(params);
        List<WpUserinfo> list = this.userinfoService.selectWpUserinfoList(wpUserinfo);
        bean.setRegistnum(CollectionUtils.size(list));
        
        //红包信息
        WpRedEnvelope wpRedEnvelope = new WpRedEnvelope();
        wpRedEnvelope.setType(1);
        wpRedEnvelope.setParams(params);
		List<WpRedEnvelope> redList = this.redEnvelopeService.selectWpRedEnvelopeList(wpRedEnvelope);
		List<Integer> redNumList = new ArrayList<>();
		BigDecimal totalMoney = new BigDecimal(0);
		for(WpRedEnvelope red : redList){
			if(!redNumList.contains(red.getUid())){
				redNumList.add(red.getUid());
			}
			totalMoney = totalMoney.add(red.getMoney());
		}
		bean.setGiveusernum(redNumList.size());
		bean.setGiveprice(totalMoney);
		
		//新充值人数/充值金额
		CodepayOrder codepayOrder = new CodepayOrder();
		codepayOrder.setParams(params);
		codepayOrder.setStatus(1);
		List<CodepayOrder> orderList = this.payOrderService.selectCodepayOrderList(codepayOrder);
		List<Integer> numList = new ArrayList<>();
		totalMoney = new BigDecimal(0);
		for(CodepayOrder order : orderList){
			if(!numList.contains(order.getPayId())){
				numList.add(order.getPayId());
			}
			totalMoney = totalMoney.add(order.getMoney());
		}
		bean.setNewrechargenum(numList.size());
		bean.setNewrechargeprice(totalMoney);
		
		//总充值人数和总充值金额
		codepayOrder = new CodepayOrder();
		codepayOrder.setStatus(1);
		orderList = this.payOrderService.selectCodepayOrderList(codepayOrder);
		numList = new ArrayList<>();
		totalMoney = new BigDecimal(0);
		for(CodepayOrder order : orderList){
			if(!numList.contains(order.getPayId())){
				numList.add(order.getPayId());
			}
			totalMoney = totalMoney.add(order.getMoney());
		}
		bean.setTotalrechargenum(numList.size());
		bean.setTotalrechargeprice(totalMoney);
		
		//出金和提现人数
		WpBalance wpBalance = new WpBalance();
		wpBalance.setBptype(0);
		wpBalance.setIsverified(1);
		wpBalance.setParams(params);
		List<WpBalance> withdrawList = this.balanceService.selectWpBalanceList(wpBalance);
		numList = new ArrayList<>();
		totalMoney = new BigDecimal(0);
		for(WpBalance withdraw : withdrawList){
			if(!numList.contains(withdraw.getUid())){
				numList.add(withdraw.getUid());
			}
			totalMoney = totalMoney.add(withdraw.getRealprice());
		}
		bean.setCashnum(numList.size());
		bean.setCashprice(totalMoney);
		this.statService.insertWpStatRecord(bean);
	}
	
	public void topStatdaily_two(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		long endTimestamp = calendar.getTimeInMillis() / 1000;
//		int endtime = Integer.valueOf(DateUtils.parseDateToStr(DateUtils.YYYYMMDD, calendar.getTime()));
		
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		long beginTimestamp = calendar.getTimeInMillis() / 1000;
		int begintime = Integer.valueOf(DateUtils.parseDateToStr(DateUtils.YYYYMMDD, calendar.getTime()));
		
		List<WpTopStat> topList = new ArrayList<>();
		
		WpUserinfo wpUserinfo = new WpUserinfo();
		wpUserinfo.setOtype(101);
		List<WpUserinfo> list = this.userinfoService.selectWpUserinfoList(wpUserinfo);
		logger.info("topStatdaily>用户数量："+list);
		for(WpUserinfo user : list){
			//如果已存在则不需要插入
			WpTopStat wpTopStat = new WpTopStat();
			wpTopStat.setUid(user.getUid());
			wpTopStat.setDaily(begintime);
			List<WpTopStat> topStatList = this.topStatService.selectWpTopStatList(wpTopStat);
			if(CollectionUtils.isNotEmpty(topStatList)){
				logger.info("topStatdaily>记录已存在，无需插入："+user.getUid() + "|" + begintime);
				continue;
			}
			WpTopStat stat = new WpTopStat();
			stat.setUid(user.getUid());
			stat.setUtel(user.getUtel());
			stat.setDaily(begintime);
			topList.add(stat);
			
			
			List<Integer> level1 = new ArrayList<>();
			List<Integer> level2 = new ArrayList<>();
			List<Integer> level3 = new ArrayList<>();
			List<Integer> allUid = new ArrayList<>();
			
			Map<Integer, WpUserinfo> map = new HashMap<>();
			
			//获取底下全部用户
			WpUserinfo up = new WpUserinfo();
			up.setTopid(user.getUid());
			List<WpUserinfo> allUser = this.userinfoService.selectWpUserinfoList(up);
			//用户为空则无需处理
			if(CollectionUtils.isEmpty(allUser)){
				continue;
			}
			stat.setRegistnum(allUser.size());
			int ibnum = 0;
			//一级用户
			for(int i = 0;i < allUser.size();i++){
				WpUserinfo u = allUser.get(i);
				allUid.add(u.getUid());
				if(u.getIbtime() != null && u.getIbtime() >= beginTimestamp && u.getIbtime() <= endTimestamp){
					ibnum++;
				}
				map.put(u.getUid(), u);
				if(u.getOid().intValue() == user.getUid().intValue()){
					level1.add(u.getUid());
					allUser.remove(i);
					i--;
				}
			}
			stat.setIbnum(ibnum);
			//二级用户
			for(int i = 0;i < allUser.size();i++){
				WpUserinfo u = allUser.get(i);
				if(level1.contains(u.getOid())){
					level2.add(u.getUid());
					allUser.remove(i);
					i--;
				}
			}
			//三级用户
			for(int i = 0;i < allUser.size();i++){
				WpUserinfo u = allUser.get(i);
//				if(level2.contains(u.getOid())){
					level3.add(u.getUid());
//				}
			}
			
			stat.setLevel1num(level1.size());
			stat.setLevel2num(level2.size());
			stat.setLevel3num(level3.size());
			
			//充值
			CodepayOrder codepayOrder = new CodepayOrder();
			codepayOrder.setStatus(1);
			codepayOrder.setPayIds(allUid);
			List<CodepayOrder> orderList = this.payOrderService.selectCodepayOrderList(codepayOrder);
			List<Integer> orderUids = new ArrayList<>();
			BigDecimal level1Money = new BigDecimal(0);
			BigDecimal level2Money = new BigDecimal(0);
			BigDecimal level3Money = new BigDecimal(0);
			BigDecimal allMoney = new BigDecimal(0);
			for (int i = 0; i < orderList.size(); i++) {
				CodepayOrder order = orderList.get(i);
				if(!orderUids.contains(order.getPayId())){
					orderUids.add(order.getPayId());
				}
				allMoney = allMoney.add(order.getMoney());
				if(level1.contains(order.getPayId())){
					level1Money = level1Money.add(order.getMoney());
				}else if(level2.contains(order.getPayId())){
					level2Money = level2Money.add(order.getMoney());
				}else if(level3.contains(order.getPayId())){
					level3Money = level3Money.add(order.getMoney());
				}
			}
			stat.setTotalrechargenum(orderUids.size());
			stat.setTotalrechargeprice(allMoney);
			
			stat.setLevel1rechargeprice(level1Money);
			stat.setLevel2rechargeprice(level2Money);
			stat.setLevel3rechargeprice(level3Money);
			
			//提现
			WpBalance wpBalance = new WpBalance();
			wpBalance.setBptype(0);
			wpBalance.setIsverified(1);
			wpBalance.setUids(allUid);
			List<WpBalance> withdrawList = this.balanceService.selectWpBalanceList(wpBalance);
			List<Integer> cashUids = new ArrayList<>();
			BigDecimal cash1Money = new BigDecimal(0);
			BigDecimal cash2Money = new BigDecimal(0);
			BigDecimal cash3Money = new BigDecimal(0);
			BigDecimal allCashMoney = new BigDecimal(0);
			for (int i = 0; i < withdrawList.size(); i++) {
				WpBalance order = withdrawList.get(i);
				if(!cashUids.contains(order.getUid())){
					cashUids.add(order.getUid());
				}
				allCashMoney = allCashMoney.add(order.getRealprice());
				if(level1.contains(order.getUid())){
					cash1Money = cash1Money.add(order.getRealprice());
				}else if(level2.contains(order.getUid())){
					cash2Money = cash2Money.add(order.getRealprice());
				}else if(level3.contains(order.getUid())){
					cash3Money = cash3Money.add(order.getRealprice());
				}
			}
			stat.setCashnum(cashUids.size());
			stat.setCashprice(allCashMoney);
			
			stat.setLevel1cashprice(cash1Money);
			stat.setLevel2cashprice(cash2Money);
			stat.setLevel3cashprice(cash3Money);
		}
		logger.info("topStatdaily>插入记录数：" + topList.size());
		if(topList.size() > 0)
			this.topStatService.batchAdd(topList);
	}
	public void topStatdaily(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        long endTimestamp = calendar.getTimeInMillis() / 1000;
//        int endtime = Integer.valueOf(DateUtils.parseDateToStr(DateUtils.YYYYMMDD, calendar.getTime()));
        
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        long beginTimestamp = calendar.getTimeInMillis() / 1000;
        int begintime = Integer.valueOf(DateUtils.parseDateToStr(DateUtils.YYYYMMDD, calendar.getTime()));

        List<WpTopStat> topList = new ArrayList<>();
        
        WpUserinfo wpUserinfo = new WpUserinfo();
        wpUserinfo.setOtype(101);
        List<WpUserinfo> list = this.userinfoService.selectWpUserinfoList(wpUserinfo);
        for(WpUserinfo user : list){
        	//如果已存在则不需要插入
        	WpTopStat wpTopStat = new WpTopStat();
        	wpTopStat.setUid(user.getUid());
        	wpTopStat.setDaily(begintime);
        	List<WpTopStat> topStatList = this.topStatService.selectWpTopStatList(wpTopStat);
        	if(CollectionUtils.isNotEmpty(topStatList)){
        		continue;
        	}
        	WpTopStat stat = new WpTopStat();
        	stat.setUid(user.getUid());
        	stat.setUtel(user.getUtel());
        	stat.setDaily(begintime);
        	topList.add(stat);
        	
        	
        	List<Integer> level1 = new ArrayList<>();
        	List<Integer> level2 = new ArrayList<>();
        	List<Integer> level3 = new ArrayList<>();
        	List<Integer> allUid = new ArrayList<>();
        	
        	Map<Integer, WpUserinfo> map = new HashMap<>();
        	
        	//获取地下全部用户
        	WpUserinfo up = new WpUserinfo();
        	up.setTopid(user.getUid());
        	up.setOtype(0);
        	List<WpUserinfo> allUser = this.userinfoService.selectWpUserinfoList(up);
        	//用户为空则无需处理
        	if(CollectionUtils.isEmpty(allUser)){
        		continue;
        	}
        	stat.setRegistnum(allUser.size());
        	int ibnum = 0;
        	//一级用户
        	for(int i = 0;i < allUser.size();i++){
        		WpUserinfo u = allUser.get(i);
        		allUid.add(u.getUid());
        		if(u.getIbtime() != null && u.getIbtime() >= beginTimestamp && u.getIbtime() <= endTimestamp){
        			ibnum++;
        		}
        		map.put(u.getUid(), u);
        		if(u.getOid().intValue() == user.getUid().intValue()){
        			level1.add(u.getUid());
        			allUser.remove(i);
        			i--;
        		}
        	}
        	stat.setIbnum(ibnum);
        	//二级用户
        	for(int i = 0;i < allUser.size();i++){
        		WpUserinfo u = allUser.get(i);
        		if(level1.contains(u.getOid())){
        			level2.add(u.getUid());
        			allUser.remove(i);
        			i--;
        		}
        	}
        	//三级用户
        	for(int i = 0;i < allUser.size();i++){
        		WpUserinfo u = allUser.get(i);
//        		if(level2.contains(u.getOid())){
        			level3.add(u.getUid());
//        		}
        	}
        	
        	stat.setLevel1num(level1.size());
        	stat.setLevel2num(level2.size());
        	stat.setLevel3num(level3.size());
        	
        	//充值
        	CodepayOrder codepayOrder = new CodepayOrder();
        	codepayOrder.setStatus(1);
        	codepayOrder.setPayIds(allUid);
        	List<CodepayOrder> orderList = this.payOrderService.selectCodepayOrderList(codepayOrder);
        	List<Integer> orderUids = new ArrayList<>();
        	BigDecimal level1Money = new BigDecimal(0);
        	BigDecimal level2Money = new BigDecimal(0);
        	BigDecimal level3Money = new BigDecimal(0);
        	BigDecimal allMoney = new BigDecimal(0);
        	for (int i = 0; i < orderList.size(); i++) {
        		CodepayOrder order = orderList.get(i);
        		if(!orderUids.contains(order.getPayId())){
        			orderUids.add(order.getPayId());
        		}
        		allMoney = allMoney.add(order.getMoney());
        		if(level1.contains(order.getPayId())){
        			level1Money = level1Money.add(order.getMoney());
        		}else if(level2.contains(order.getPayId())){
        			level2Money = level2Money.add(order.getMoney());
        		}else if(level3.contains(order.getPayId())){
        			level3Money = level3Money.add(order.getMoney());
        		}
			}
        	stat.setTotalrechargenum(orderUids.size());
        	stat.setTotalrechargeprice(allMoney);
        	
        	stat.setLevel1rechargeprice(level1Money);
        	stat.setLevel2rechargeprice(level2Money);
        	stat.setLevel3rechargeprice(level3Money);
        	
        	//提现
        	WpBalance wpBalance = new WpBalance();
    		wpBalance.setBptype(0);
    		wpBalance.setIsverified(1);
    		wpBalance.setUids(allUid);
    		List<WpBalance> withdrawList = this.balanceService.selectWpBalanceList(wpBalance);
    		List<Integer> cashUids = new ArrayList<>();
        	BigDecimal cash1Money = new BigDecimal(0);
        	BigDecimal cash2Money = new BigDecimal(0);
        	BigDecimal cash3Money = new BigDecimal(0);
        	BigDecimal allCashMoney = new BigDecimal(0);
        	for (int i = 0; i < withdrawList.size(); i++) {
        		WpBalance order = withdrawList.get(i);
        		if(!cashUids.contains(order.getUid())){
        			cashUids.add(order.getUid());
        		}
        		allCashMoney = allCashMoney.add(order.getRealprice());
        		if(level1.contains(order.getUid())){
        			cash1Money = cash1Money.add(order.getRealprice());
        		}else if(level2.contains(order.getUid())){
        			cash2Money = cash2Money.add(order.getRealprice());
        		}else if(level3.contains(order.getUid())){
        			cash3Money = cash3Money.add(order.getRealprice());
        		}
			}
        	stat.setCashnum(cashUids.size());
        	stat.setCashprice(allCashMoney);
        	
        	stat.setLevel1cashprice(cash1Money);
        	stat.setLevel2cashprice(cash2Money);
        	stat.setLevel3cashprice(cash3Money);
        }
        if(topList.size() > 0)
        this.topStatService.batchAdd(topList);
	}
	
}
