package com.wjyoption.system.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.wjyoption.common.core.domain.AjaxResult;
import com.wjyoption.common.core.domain.Response;
import com.wjyoption.common.core.text.Convert;
import com.wjyoption.common.enums.ErrorConstants;
import com.wjyoption.common.utils.DateUtils;
import com.wjyoption.common.utils.ThreadLocals;
import com.wjyoption.common.utils.db.RedisEnum;
import com.wjyoption.common.vo.UserLogin;
import com.wjyoption.system.domain.CodepayOrder;
import com.wjyoption.system.domain.WpFinancialBuy;
import com.wjyoption.system.domain.WpFinancialDetail;
import com.wjyoption.system.domain.WpFinancialRecord;
import com.wjyoption.system.domain.WpFinancialType;
import com.wjyoption.system.domain.WpProductinfo;
import com.wjyoption.system.domain.WpRedEnvelope;
import com.wjyoption.system.domain.WpUserNovicetaskRate;
import com.wjyoption.system.domain.WpUserinfo;
import com.wjyoption.system.domain.WpYieldRate;
import com.wjyoption.system.mapper.WpFinancialRecordMapper;
import com.wjyoption.system.mapper.WpRedEnvelopeMapper;
import com.wjyoption.system.service.ICodepayOrderService;
import com.wjyoption.system.service.IWpCashFlowService;
import com.wjyoption.system.service.IWpFinancialBuyService;
import com.wjyoption.system.service.IWpFinancialDetailService;
import com.wjyoption.system.service.IWpFinancialRecordService;
import com.wjyoption.system.service.IWpFinancialTypeService;
import com.wjyoption.system.service.IWpPriceLogService;
import com.wjyoption.system.service.IWpProductinfoService;
import com.wjyoption.system.service.IWpUserNovicetaskRateService;
import com.wjyoption.system.service.IWpUserinfoService;
import com.wjyoption.system.service.IWpYieldRateService;
import com.wjyoption.system.vo.resp.FinancialRecordResp;

/**
 * 理财记录Service业务层处理
 * 
 * @author wjyoption
 * @date 2021-06-05
 */
@Service
public class WpFinancialRecordServiceImpl implements IWpFinancialRecordService 
{
	private static Logger logger = LoggerFactory.getLogger(WpFinancialRecordServiceImpl.class);
    @Autowired
    private WpFinancialRecordMapper wpFinancialRecordMapper;
    
    @Autowired WpRedEnvelopeMapper redMapper;
    @Autowired IWpCashFlowService cashFlowService;
    @Autowired IWpPriceLogService priceLogService;
    @Autowired IWpFinancialTypeService typeService;
    @Autowired IWpUserinfoService userinfoService;
    @Autowired IWpProductinfoService productinfoService;
	@Autowired IWpFinancialDetailService detailService;
	@Autowired IWpFinancialBuyService buyService;
	@Autowired IWpYieldRateService yieldRateService;
	@Autowired IWpUserNovicetaskRateService novicetaskRateService;
	@Autowired ICodepayOrderService codepayOrderService;
    
    @SuppressWarnings("rawtypes")
	@Autowired RedisTemplate redisTemplate;

    @Value("${wjyoption.firstRewardSwitch}")
	private String firstRewardSwitch;
    
    /**
     * 查询理财记录
     * 
     * @param id 理财记录ID
     * @return 理财记录
     */
    @Override
    public WpFinancialRecord selectWpFinancialRecordById(Integer id)
    {
        return wpFinancialRecordMapper.selectWpFinancialRecordById(id);
    }

    /**
     * 查询理财记录列表
     * 
     * @param wpFinancialRecord 理财记录
     * @return 理财记录
     */
    @Override
    public List<WpFinancialRecord> selectWpFinancialRecordList(WpFinancialRecord wpFinancialRecord)
    {
        return wpFinancialRecordMapper.selectWpFinancialRecordList(wpFinancialRecord);
    }

    /**
     * 新增理财记录
     * 
     * @param wpFinancialRecord 理财记录
     * @return 结果
     */
    @Override
    public int insertWpFinancialRecord(WpFinancialRecord wpFinancialRecord)
    {
        return wpFinancialRecordMapper.insertWpFinancialRecord(wpFinancialRecord);
    }

    /**
     * 修改理财记录
     * 
     * @param wpFinancialRecord 理财记录
     * @return 结果
     */
    @Override
    public int updateWpFinancialRecord(WpFinancialRecord wpFinancialRecord)
    {
        return wpFinancialRecordMapper.updateWpFinancialRecord(wpFinancialRecord);
    }

    /**
     * 删除理财记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWpFinancialRecordByIds(String ids)
    {
        return wpFinancialRecordMapper.deleteWpFinancialRecordByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除理财记录信息
     * 
     * @param id 理财记录ID
     * @return 结果
     */
    public int deleteWpFinancialRecordById(Integer id)
    {
        return wpFinancialRecordMapper.deleteWpFinancialRecordById(id);
    }

	@Override
	@Transactional
	public void buy(Integer typeid, Integer money,Integer comenewtask, Response<Object> response) {
		UserLogin user = ThreadLocals.getUser();
		Integer uid = user.getUid();
		WpRedEnvelope wpRedEnvelope = new WpRedEnvelope();
		wpRedEnvelope.setUid(uid);
		List<WpRedEnvelope> redList = this.redMapper.selectWpRedEnvelopeList(wpRedEnvelope);
		WpFinancialType typeVo = this.typeService.selectWpFinancialTypeById(typeid);
		if(typeVo == null || typeVo.getStatus() == 2){
			ErrorConstants.setResponse(response, ErrorConstants.TEMPLATE_NOT_EXISTS);
			return;
		}
		if(typeVo.getMinbuymoney() > money){
			ErrorConstants.setResponse(response, ErrorConstants.MONEY_TOO_SMALL);
			return;
		}
//		if(typeVo.getMaxbuymoney() != null && typeVo.getMaxbuymoney() < money){
//			ErrorConstants.setResponse(response, ErrorConstants.MONEY_TOO_LARGE);
//			return;
//		}
		double canusedRedMoney = money * typeVo.getRedpercent();
		double redMoney = 0;
		for(WpRedEnvelope red : redList){
			if(red.getLavemoney().add(new BigDecimal(redMoney)).doubleValue() > canusedRedMoney){
				red.setLavemoney(red.getLavemoney().subtract(new BigDecimal(canusedRedMoney - redMoney)));
				this.redMapper.updateWpRedEnvelope(red);
				redMoney = canusedRedMoney;
				break;
			}
			redMoney += red.getLavemoney().doubleValue();
			red.setLavemoney(new BigDecimal(0));
			this.redMapper.updateWpRedEnvelope(red);
		}
		Calendar calendar = Calendar.getInstance();
		int hour = calendar.get(Calendar.HOUR);
		long addTime = 0;
		if(hour >= 22){
			addTime = 86400;
		}
		long beginTime = DateUtils.dateTime("yyyyMMdd", DateUtils.dateTime()).getTime() / 1000 + addTime;
		BigDecimal buy = new BigDecimal(money).subtract(new BigDecimal(redMoney));
		WpUserinfo userinfo = this.userinfoService.selectWpUserinfoById(uid);
		if(userinfo.getUsermoney().compareTo(buy.multiply(typeVo.getRebate())) < 0){
			ErrorConstants.setResponse(response, ErrorConstants.WALLET_SMALL);
			return;
		}
		WpFinancialRecord record = new WpFinancialRecord();
		record.setUid(uid);
		record.setBuymoney(buy);
		record.setCreditmoney(new BigDecimal(redMoney));
		record.setProfit(new BigDecimal(0));
		record.setTotalmoney(new BigDecimal(money));
		record.setBegintime(beginTime);
		record.setEndtime(beginTime + typeVo.getFreezedate() * 86400);
		record.setReturntime(beginTime + (typeVo.getFreezedate()-3) * 86400);
		record.setFreezedate(typeVo.getFreezedate());
		record.setTypeid(typeid);
		record.setCreatetime(calendar.getTime().getTime() / 1000);
		record.setComenewtask(comenewtask);
		record.setRebate(typeVo.getRebate());
		this.wpFinancialRecordMapper.insertWpFinancialRecord(record);
		if(buy.compareTo(new BigDecimal(0)) > 0){
			BigDecimal buyMoney = buy.multiply(record.getRebate());
			BigDecimal nowMoney = userinfo.getUsermoney().subtract(buyMoney);
			this.userinfoService.updateMoney(buyMoney.multiply(new BigDecimal(-1)), uid);
			this.cashFlowService.insertWpCashFlow(uid, 7, buyMoney.multiply(new BigDecimal(-1)).toString(), "购买理财", nowMoney);
			this.priceLogService.insertWpPriceLog(uid, buyMoney.toString(), 2, "Financial", "Your order has been placed successfully", record.getId(), nowMoney);
			firstBuy(userinfo, buyMoney);
			newtask(record);
		}
	}
	private void firstBuy(WpUserinfo userinfo,BigDecimal buyMoney){
		if("0".equals(firstRewardSwitch)) return;
		WpFinancialRecord wpFinancialRecord = new WpFinancialRecord();
		wpFinancialRecord.setUid(userinfo.getUid());
		wpFinancialRecord.setIsgive(0);
		List<FinancialRecordResp> list = this.wpFinancialRecordMapper.selectFinancialRecordList(wpFinancialRecord);
		if(list.size() != 1){
			return;
		}
		WpUserinfo user_p1 = this.userinfoService.selectWpUserinfoById(userinfo.getOid());
		Integer id = list.get(0).getId();
		BigDecimal rete_p1 = BigDecimal.valueOf(0.1);//一级用户返佣利率
		BigDecimal rete_p2 = BigDecimal.valueOf(0.06);//二级用户返佣利率
		BigDecimal rete_p3 = BigDecimal.valueOf(0.04);//三级用户返佣利率
		if(user_p1 == null){
			return;
		}else{
			BigDecimal profit_p1 = null;
			if(user_p1.getIbstatus() != null && user_p1.getIbstatus() == 2){
				profit_p1 = buyMoney.multiply(BigDecimal.valueOf(0.11)).setScale(2, BigDecimal.ROUND_DOWN);
			}else{
				profit_p1 = buyMoney.multiply(rete_p1).setScale(2, BigDecimal.ROUND_DOWN);
			}
			this.userinfoService.updateMoney(profit_p1, user_p1.getUid());
			BigDecimal usermoney = user_p1.getUsermoney().add(profit_p1);
			this.priceLogService.insertWpPriceLog(user_p1.getUid(), profit_p1.toString(), 1, "Financial", "First purchase reward 1", id, usermoney);
			this.cashFlowService.insertWpCashFlow(user_p1.getUid(), 17, profit_p1.toString(), "一级首购收益", usermoney);
		}
		WpUserinfo user_p2 = this.userinfoService.selectWpUserinfoById(user_p1.getOid());
		if(user_p2 == null){
			return;
		}else{
			BigDecimal profit_p2 = null;
			if(user_p2.getIbstatus() != null && user_p2.getIbstatus() == 2){
				profit_p2 = buyMoney.multiply(BigDecimal.valueOf(0.07)).setScale(2, BigDecimal.ROUND_DOWN);
			}else{
				profit_p2 = buyMoney.multiply(rete_p2).setScale(2, BigDecimal.ROUND_DOWN);
			}
			BigDecimal usermoney = user_p2.getUsermoney().add(profit_p2);
			this.userinfoService.updateMoney(profit_p2, user_p2.getUid());
			this.priceLogService.insertWpPriceLog(user_p2.getUid(), profit_p2.toString(), 1, "Financial", "First purchase reward 2", id, usermoney);
			this.cashFlowService.insertWpCashFlow(user_p2.getUid(), 18, profit_p2.toString(), "二级首购收益", usermoney);
		}
		
		WpUserinfo user_p3 = this.userinfoService.selectWpUserinfoById(user_p2.getOid());
		if(user_p3 == null){
			return;
		}
		BigDecimal profit_p3 = null;
		if(user_p3.getIbstatus() != null && user_p3.getIbstatus() == 2){
			profit_p3 = buyMoney.multiply(BigDecimal.valueOf(0.05)).setScale(2, BigDecimal.ROUND_DOWN);
		}else{
			profit_p3 = buyMoney.multiply(rete_p3).setScale(2, BigDecimal.ROUND_DOWN);
		}
		BigDecimal usermoney = user_p3.getUsermoney().add(profit_p3);
		this.userinfoService.updateMoney(profit_p3, user_p3.getUid());
		this.priceLogService.insertWpPriceLog(user_p3.getUid(), profit_p3.toString(), 1, "Financial", "First purchase reward 3", id, usermoney);
		this.cashFlowService.insertWpCashFlow(user_p3.getUid(), 19, profit_p3.toString(), "三级首购收益", usermoney);
	}
	
	private void newtask(WpFinancialRecord record){
		if(record.getComenewtask() == null || record.getComenewtask() != 1) return;
		
		Integer uid = record.getUid();
		CodepayOrder codepayOrder = new CodepayOrder();
		codepayOrder.setPayId(uid);
		codepayOrder.setComenewtask(1);
		List<CodepayOrder> list = this.codepayOrderService.selectCodepayOrderList(codepayOrder);
		if(list == null || list.size() == 0) return;
		int taskid = 4;
		WpUserNovicetaskRate bean = this.novicetaskRateService.selectWpUserNovicetaskRateByTaskidUid(uid, taskid);
		long now = DateUtils.getNowSecond();
		if(bean == null){
			bean = new WpUserNovicetaskRate();
			bean.setTaskid(taskid);
			bean.setUid(uid);
			bean.setCreatetime(now);
		}else if(bean.getStatus() == 3){
			return;
		}
		bean.setStatus(3);
		bean.setFinishtime(now);
		if(bean.getId() == 0){
			this.novicetaskRateService.insertWpUserNovicetaskRate(bean);
		}else{
			this.novicetaskRateService.updateWpUserNovicetaskRate(bean);
		}
	}

	@Override
	public List<FinancialRecordResp> selectFinancialRecordList(
			WpFinancialRecord wpFinancialRecord) {
		return this.wpFinancialRecordMapper.selectFinancialRecordList(wpFinancialRecord);
	}

	@Override
	public void redemption(Integer id, Response<Object> response) {
		WpFinancialRecord record = this.wpFinancialRecordMapper.selectWpFinancialRecordById(id);
		if(record == null){
			ErrorConstants.setResponse(response, ErrorConstants.RECORD_NOT_EXISTS);
			return;
		}
		if(record.getUid().intValue() != ThreadLocals.getUser().getUid().intValue()){
			ErrorConstants.setResponse(response, ErrorConstants.ERROR);
			return;
		}
		if(record.getNextstep() != 0 || record.getStatus() == 1){
			return;
		}
		if(record.getReturntime() > (System.currentTimeMillis() / 1000)){
			ErrorConstants.setResponse(response, ErrorConstants.NOT_OPERA);
			return;
		}
		record.setNextstep(1);
		this.wpFinancialRecordMapper.updateWpFinancialRecord(record);
	}

	@Override
	public void overBySelf(Integer id, Response<Object> response) {
		WpFinancialRecord record = this.wpFinancialRecordMapper.selectWpFinancialRecordById(id);
		if(record == null){
			ErrorConstants.setResponse(response, ErrorConstants.RECORD_NOT_EXISTS);
			return;
		}
		if(record.getUid().intValue() != ThreadLocals.getUser().getUid().intValue()){
			ErrorConstants.setResponse(response, ErrorConstants.ERROR);
			return;
		}
		if(record.getOverself() != 1 || record.getStatus() != 0){
			return;
		}
//		WpFinancialType typeVo = this.typeService.selectWpFinancialTypeById(record.getTypeid());
//		if(typeVo.getCanover() != 2){
//			ErrorConstants.setResponse(response, ErrorConstants.NO_POWER);
//			return;
//		}
		record.setOverself(2);
		record.setNextstep(1);
		this.wpFinancialRecordMapper.updateWpFinancialRecord(record);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public void freebuy(Integer typeid,Response<Object> response) {
		WpFinancialType typeVo = this.typeService.selectWpFinancialTypeById(typeid);
		if(typeVo == null || typeVo.getStatus() == 2 || typeVo.getIsnormal() != 2){
			ErrorConstants.setResponse(response, ErrorConstants.TEMPLATE_NOT_EXISTS);
			return;
		}
		BigDecimal money = new BigDecimal(typeVo.getMinbuymoney());
		UserLogin userLogin = ThreadLocals.getUser();
		Integer uid = userLogin.getUid();
		WpFinancialRecord wpFinancialRecord = new WpFinancialRecord();
		wpFinancialRecord.setUid(uid);
		wpFinancialRecord.setIsgive(1);
		List<WpFinancialRecord> list = this.wpFinancialRecordMapper.selectWpFinancialRecordList(wpFinancialRecord);
		if(!CollectionUtils.isEmpty(list)){
			ErrorConstants.setResponse(response, ErrorConstants.HAVE_PARTICIPATED);
			return;
		}
		WpRedEnvelope wpRedEnvelope = new WpRedEnvelope();
		wpRedEnvelope.setUid(uid);
		List<WpRedEnvelope> redList = this.redMapper.selectWpRedEnvelopeList(wpRedEnvelope);
		BigDecimal lavemoney = new BigDecimal(0);
		for(WpRedEnvelope obj : redList){
			lavemoney = lavemoney.add(obj.getLavemoney());
		}
		if(lavemoney.compareTo(money) < 0){
			logger.info("金额：" + lavemoney.toString() + "|-----|" + money.toString());
			ErrorConstants.setResponse(response, ErrorConstants.NO_MUCH_MONEY);
			return;
		}
		BigDecimal redMoney = new BigDecimal(0);
		for(WpRedEnvelope red : redList){
			if((red.getLavemoney().add(redMoney)).compareTo(money) >= 0){
				red.setLavemoney(red.getLavemoney().subtract(money.subtract(redMoney)));
				this.redMapper.updateWpRedEnvelope(red);
				redMoney = money;
				break;
			}
			redMoney.add(red.getLavemoney());
			red.setLavemoney(new BigDecimal(0));
			this.redMapper.updateWpRedEnvelope(red);
		}
		Calendar calendar = Calendar.getInstance();
		int hour = calendar.get(Calendar.HOUR);
		int week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		week = week == 0 ? 7 : week;
		int addDate = typeVo.getFreezedate();
		if(typeid == 1){
			addDate = 4;
		}
		if(addDate < 6){
			if(week < 7-addDate){
				
			}else if(week == 7-addDate){
				addDate += 2;
			}else if(week > 7-addDate){
				if(week == 7){
					addDate++;
				}else{
					addDate+=2;
				}
			}
		}
//		if(week == 0){
//			addDate = 5;
//		}else if(week > 2){
//			addDate = 6;
//		}
		long addTime = 0;
		if(hour >= 22){
			addTime = 86400;
		}
		long beginTime = DateUtils.dateTime("yyyyMMdd", DateUtils.dateTime()).getTime() / 1000 + addTime;
		
		WpFinancialRecord record = new WpFinancialRecord();
		record.setUid(uid);
		record.setBuymoney(new BigDecimal(0));
		record.setCreditmoney(money);
		record.setProfit(new BigDecimal(0));
		record.setIsgive(1);
		record.setTotalmoney(money);
		record.setBegintime(beginTime);
		
		record.setEndtime(beginTime + 86400 * addDate);
		record.setReturntime(record.getEndtime() + 86400);
		record.setFreezedate(typeVo.getFreezedate());
		record.setTypeid(typeid);
		record.setCreatetime(calendar.getTime().getTime() / 1000);
		record.setNextstep(1);
		this.wpFinancialRecordMapper.insertWpFinancialRecord(record);
		
		if(record.getId() > 0){
			String key = RedisEnum.USER_LOGIN.getKeyPrefix() + userLogin.getToken();
			ValueOperations<String, UserLogin> opsForValue = redisTemplate.opsForValue();
			userLogin.setExperience(1);
			if(opsForValue.get(key) != null){
				opsForValue.set(key, userLogin, 0);
			}else{
				opsForValue.set(key, userLogin, 14, TimeUnit.DAYS);
			}
		}
	}
	public static void main(String[] args) {
//		long nowtime = 1633251170000l-86400000*7;
//		Calendar calendar = Calendar.getInstance();
//		calendar.setTimeInMillis(nowtime);
//		System.out.println(calendar.getTime());
//		int hour = calendar.get(Calendar.HOUR);
//		int week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
//		week = week == 0 ? 7 : week;
//		System.out.println(week);
//		int addDate = 4;
//		if(addDate < 6){
//			if(week < 7-addDate){
//				
//			}else if(week == 7-addDate){
//				addDate += 2;
//			}else if(week > 7-addDate){
//				if(week == 7){
//					addDate++;
//				}else{
//					addDate+=2;
//				}
//			}
//		}
//		long addTime = 0;
//		if(hour >= 22){
//			addTime = 86400;
//		}
//		long beginTime = nowtime/1000 + addTime;
//		System.out.println(beginTime);
//		System.out.println(addDate);
//		System.out.println(beginTime + 86400 * addDate);
		Date date2 = DateUtils.dateTime("yyyyMMddHHmmss",20221016 + "031010");
		long yesDate = date2.getTime()/1000 - 86400;
		System.out.println(yesDate);
	}

	@Override
	@Transactional
	public AjaxResult over(Integer id) {
		String buyTime = DateUtils.isBuyTime();
		if(buyTime != null){
			return AjaxResult.error(buyTime);
		}
		if(id == null){
			return AjaxResult.error("参数不能为空");
		}
		WpFinancialRecord bean = this.wpFinancialRecordMapper.selectWpFinancialRecordById(id);
		if(bean == null || bean.getStatus() == 1){
			return AjaxResult.error("状态不对,喝瓶红牛再来吧");
		}
		WpUserinfo userinfo = this.userinfoService.selectWpUserinfoById(bean.getUid());
		bean.setStatus(1);
		bean.setManualtime(DateUtils.getNowSecond());
		this.wpFinancialRecordMapper.updateWpFinancialRecord(bean);
		if(bean.getBuymoney().doubleValue() > 0){
			BigDecimal buymoney = bean.getBuymoney().multiply(bean.getRebate());//计算折扣
			this.userinfoService.updateMoney(buymoney, bean.getUid());
			this.priceLogService.insertWpPriceLog(bean.getUid(), bean.getBuymoney().toString(), 1, "Financial", "Principal Redemption", bean.getId(), userinfo.getUsermoney().add(buymoney));
			this.cashFlowService.insertWpCashFlow(bean.getUid(), 8, buymoney.toString(), "后台结束->赎回本金", userinfo.getUsermoney().add(buymoney));
		}
		return AjaxResult.success();
	}

	@Override
	@Transactional
	public void financialOver(WpFinancialRecord record) {
		WpUserinfo userinfo = this.userinfoService.selectWpUserinfoById(record.getUid());
		this.wpFinancialRecordMapper.updateWpFinancialRecord(record);
		if(record.getNextstep() == 0){//到期继续购买
			this.buy(record);
		}else{
			if(record.getBuymoney().intValue() > 0){
				WpFinancialType typeVo = this.typeService.selectWpFinancialTypeById(record.getTypeid());
				BigDecimal addMoney = record.getBuymoney().multiply(record.getRebate());
				if(record.getOverself() != 1 && typeVo.getFeerate() != null && typeVo.getFeerate() > 0 && typeVo.getFeerate() < 100){
					BigDecimal feeMoney = addMoney.multiply(new BigDecimal(typeVo.getFeerate())).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP);
					addMoney = addMoney.subtract(feeMoney);
					this.cashFlowService.insertWpCashFlow(record.getUid(), 21, feeMoney.multiply(new BigDecimal(-1)).toString(), "提前赎回扣款", userinfo.getUsermoney());
					this.priceLogService.insertWpPriceLog(record.getUid(), feeMoney.toString(), 2, "Liquidated damages", "Early redemption of liquidated damages", record.getId(), userinfo.getUsermoney());
				}
				BigDecimal nowMoney = userinfo.getUsermoney().add(addMoney);
				this.userinfoService.updateMoney(record.getBuymoney(), record.getUid());
				this.cashFlowService.insertWpCashFlow(record.getUid(), 8, addMoney.toString(), "赎回理财本金", nowMoney);
				this.priceLogService.insertWpPriceLog(record.getUid(), addMoney.toString(), 1, "Financial", "Principal Redemption", record.getId(), nowMoney);
			}
		}
	}
	
	private void buy(WpFinancialRecord record){
		WpFinancialRecord insert = new WpFinancialRecord();
		insert.setTypeid(record.getTypeid());
		insert.setUid(record.getUid());
		insert.setBuymoney(record.getBuymoney());
		insert.setCreditmoney(record.getCreditmoney());
		insert.setProfit(new BigDecimal(0));
		insert.setTotalmoney(record.getBuymoney().add(record.getCreditmoney()));
		insert.setFreezedate(record.getFreezedate());
		insert.setBegintime(record.getEndtime());
		insert.setEndtime(insert.getBegintime() + (record.getFreezedate()+1)*86400);
		insert.setReturntime(insert.getEndtime() - 86400 * 3);
		insert.setCreatetime(DateUtils.getNowSecond());
		insert.setPid(record.getId());
		insert.setMonth(record.getMonth() + 1);
		insert.setRebate(record.getRebate());
		this.insertWpFinancialRecord(insert);
		
	}

	@Override
	public List<WpFinancialRecord> selectIbRecordList(WpFinancialRecord wpFinancialRecord) {
		return this.wpFinancialRecordMapper.selectIbRecordList(wpFinancialRecord);
	}

	@Override
	public int updatefinancial() {
		Long endtimeBegin = DateUtils.getNowSecond();
		WpFinancialRecord wpFinancialRecord = new WpFinancialRecord();
//		wpFinancialRecord.setEndtimeBegin(endtimeBegin);
		wpFinancialRecord.setEndtimeEnd(endtimeBegin);//(endtimeBegin-86400);
		wpFinancialRecord.setStatus(0);
		List<WpFinancialRecord> list = this.wpFinancialRecordMapper.selectWpFinancialRecordList(wpFinancialRecord);
		if(CollectionUtils.isEmpty(list)){
			logger.info("没有数据有收益产生");
			return 0;
		}
		for(WpFinancialRecord record : list){
			record.setStatus(1);
			this.financialOver(record);
		}
		return list.size();
	}

	@Override
	@Transactional
	public int updateProfit() {
		long yesDate = DateUtils.getNowSecond() - 86400;
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(yesDate * 1000);
		int date = Integer.valueOf(DateUtils.parseDateToStr(DateUtils.YYYYMMDD, calendar.getTime()));
		WpProductinfo wpProductinfo = new WpProductinfo();
		wpProductinfo.setIsdelete(0);
		List<WpProductinfo> products = this.productinfoService.selectWpProductinfoList(wpProductinfo);
		if(CollectionUtils.isEmpty(products)){
			logger.info("没有产品可购买");
			return 0;
		}
		List<WpFinancialType> typeList = this.typeService.selectWpFinancialTypeList(null);
		WpYieldRate wpYieldRate = null;
		int retCount = 0;
		for(WpFinancialType type : typeList){
			wpYieldRate = new WpYieldRate();
			wpYieldRate.setDaily(date);
			wpYieldRate.setTypeid(type.getId());
			List<WpYieldRate> yieldRateList = this.yieldRateService.selectWpYieldRateList(wpYieldRate);
			if(CollectionUtils.isEmpty(yieldRateList)){
				continue;
			}
			WpYieldRate yieldRate = yieldRateList.get(0);
			
			WpFinancialRecord wpFinancialRecord = new WpFinancialRecord();
			wpFinancialRecord.setStatus(0);
			wpFinancialRecord.setTypeid(type.getId());
			
			List<WpFinancialRecord> recordList = this.wpFinancialRecordMapper.selectWpFinancialRecordList(wpFinancialRecord);
			List<BigDecimal> ratedetail = this.rands(yieldRate.getRate());
			for(WpFinancialRecord v : recordList){
				if(v.getBegintime() > yesDate) continue;
				BigDecimal buyTotalMoney = v.getBuymoney().add(v.getCreditmoney());
				BigDecimal profit = yieldRate.getRate().multiply(buyTotalMoney).setScale(2, BigDecimal.ROUND_HALF_UP);
				WpFinancialDetail detail = new WpFinancialDetail();
				detail.setRefid(v.getId());
				detail.setUid(v.getUid());
				detail.setDaily(date);
				List<WpFinancialDetail> detailList = this.detailService.selectWpFinancialDetailList(detail);
				if(CollectionUtils.isNotEmpty(detailList)){
					continue;
				}
				retCount++;
				detail.setRate(yieldRate.getRate());
				detail.setProfit(profit);
				this.detailService.insertWpFinancialDetail(detail);
				Integer detailid = detail.getId();
				v.setProfit(v.getProfit().add(profit));
				this.wpFinancialRecordMapper.updateWpFinancialRecord(v);
				WpUserinfo userinfo = this.userinfoService.selectWpUserinfoById(v.getUid());
				BigDecimal usermoney = userinfo.getUsermoney().add(profit);
				this.userinfoService.updateMoney(profit, v.getUid());
				this.priceLogService.insertWpPriceLog(v.getUid(), profit.toString(), 1, "Financial", "Financial income received", v.getId(), usermoney);
				this.cashFlowService.insertWpCashFlow(v.getUid(), 9, profit.toString(), "理财收益", usermoney);
				
				for(BigDecimal rv : ratedetail){
					if(rv.multiply(buyTotalMoney).doubleValue() == 0) continue;
					WpProductinfo pro = products.get(new Random().nextInt(products.size()));
					WpFinancialBuy wpFinancialBuy = new WpFinancialBuy();
					wpFinancialBuy.setUid(v.getUid());
					wpFinancialBuy.setDetailid(detailid);
					wpFinancialBuy.setPid(pro.getPid());
					wpFinancialBuy.setPtitle(pro.getPtitle());
					wpFinancialBuy.setDirect(new Random().nextInt(2)+1);
					wpFinancialBuy.setProfit(rv.multiply(buyTotalMoney));
					wpFinancialBuy.setRate(rv);
					wpFinancialBuy.setDaily(date);
					this.buyService.insertWpFinancialBuy(wpFinancialBuy);
				}
				//体验产品父级没有收益
				if(type.getIsnormal() == 2){
					continue;
				}
				if(v.getFreezedate() > 15){
					updatePrentProfit(v, userinfo,profit);
				}
			}
			
		}
		return retCount;
	}
	@Override
	@Transactional
	public int updateProfit(int date) {
//		long yesDate = DateUtils.getNowSecond() - 86400;
//		Calendar calendar = Calendar.getInstance();
//		calendar.setTimeInMillis(yesDate * 1000);
//		int date = Integer.valueOf(DateUtils.parseDateToStr(DateUtils.YYYYMMDD, calendar.getTime()));
		Date date2 = DateUtils.dateTime("yyyyMMddHHmmss",date + "031010");
		long yesDate = date2.getTime()/1000 - 86400;
		WpProductinfo wpProductinfo = new WpProductinfo();
		wpProductinfo.setIsdelete(0);
		List<WpProductinfo> products = this.productinfoService.selectWpProductinfoList(wpProductinfo);
		if(CollectionUtils.isEmpty(products)){
			logger.info("没有产品可购买");
			return 0;
		}
		List<WpFinancialType> typeList = this.typeService.selectWpFinancialTypeList(null);
		WpYieldRate wpYieldRate = null;
		int retCount = 0;
		for(WpFinancialType type : typeList){
			wpYieldRate = new WpYieldRate();
			wpYieldRate.setDaily(date);
			wpYieldRate.setTypeid(type.getId());
			List<WpYieldRate> yieldRateList = this.yieldRateService.selectWpYieldRateList(wpYieldRate);
			if(CollectionUtils.isEmpty(yieldRateList)){
				continue;
			}
			WpYieldRate yieldRate = yieldRateList.get(0);
			
			WpFinancialRecord wpFinancialRecord = new WpFinancialRecord();
			wpFinancialRecord.setStatus(0);
			wpFinancialRecord.setTypeid(type.getId());
			
			List<WpFinancialRecord> recordList = this.wpFinancialRecordMapper.selectWpFinancialRecordList(wpFinancialRecord);
			List<BigDecimal> ratedetail = this.rands(yieldRate.getRate());
			for(WpFinancialRecord v : recordList){
				if(v.getBegintime() > yesDate) continue;
				BigDecimal buyTotalMoney = v.getBuymoney().add(v.getCreditmoney());
				BigDecimal profit = yieldRate.getRate().multiply(buyTotalMoney).setScale(2, BigDecimal.ROUND_HALF_UP);
				WpFinancialDetail detail = new WpFinancialDetail();
				detail.setRefid(v.getId());
				detail.setUid(v.getUid());
				detail.setDaily(date);
				List<WpFinancialDetail> detailList = this.detailService.selectWpFinancialDetailList(detail);
				if(CollectionUtils.isNotEmpty(detailList)){
					continue;
				}
				retCount++;
				detail.setRate(yieldRate.getRate());
				detail.setProfit(profit);
				this.detailService.insertWpFinancialDetail(detail);
				Integer detailid = detail.getId();
				v.setProfit(v.getProfit().add(profit));
				this.wpFinancialRecordMapper.updateWpFinancialRecord(v);
				WpUserinfo userinfo = this.userinfoService.selectWpUserinfoById(v.getUid());
				BigDecimal usermoney = userinfo.getUsermoney().add(profit);
				this.userinfoService.updateMoney(profit, v.getUid());
				this.priceLogService.insertWpPriceLog(v.getUid(), profit.toString(), 1, "Financial", "Financial income received", v.getId(), usermoney);
				this.cashFlowService.insertWpCashFlow(v.getUid(), 9, profit.toString(), "理财收益", usermoney);
				
				for(BigDecimal rv : ratedetail){
					if(rv.multiply(buyTotalMoney).doubleValue() == 0) continue;
					WpProductinfo pro = products.get(new Random().nextInt(products.size()));
					WpFinancialBuy wpFinancialBuy = new WpFinancialBuy();
					wpFinancialBuy.setUid(v.getUid());
					wpFinancialBuy.setDetailid(detailid);
					wpFinancialBuy.setPid(pro.getPid());
					wpFinancialBuy.setPtitle(pro.getPtitle());
					wpFinancialBuy.setDirect(new Random().nextInt(2)+1);
					wpFinancialBuy.setProfit(rv.multiply(buyTotalMoney));
					wpFinancialBuy.setRate(rv);
					wpFinancialBuy.setDaily(date);
					this.buyService.insertWpFinancialBuy(wpFinancialBuy);
				}
				//体验产品父级没有收益
				if(type.getIsnormal() == 2){
					continue;
				}
				if(v.getFreezedate() > 15){
					updatePrentProfit(v, userinfo,profit);
				}
			}
			
		}
		return retCount;
	}
	
	/**
	 * 更新父类奖金
	 * @param record
	 * @param userinfo
	 */
	public void updatePrentProfit(WpFinancialRecord record,WpUserinfo userinfo,BigDecimal profit){
		WpUserinfo user_p1 = this.userinfoService.selectWpUserinfoById(userinfo.getOid());
		if(user_p1 == null){
			return;
		}
		BigDecimal rete_p1 = BigDecimal.valueOf(0.3);//一级用户返佣利率
		BigDecimal rete_p2 = BigDecimal.valueOf(0.1);//二级用户返佣利率
		BigDecimal rete_p3 = BigDecimal.valueOf(0.05);
		if(user_p1.getIbstatus() >= 1){
			BigDecimal profit_p1 = null;
			if(user_p1.getIbstatus() == 1){
				profit_p1 = profit.multiply(rete_p1).setScale(2, BigDecimal.ROUND_DOWN);
			}else{
				profit_p1 = profit.multiply(BigDecimal.valueOf(0.35)).setScale(2, BigDecimal.ROUND_DOWN);
			}
			this.userinfoService.updateMoney(profit_p1, user_p1.getUid());
			BigDecimal usermoney = user_p1.getUsermoney().add(profit_p1);
			this.priceLogService.insertWpPriceLog(user_p1.getUid(), profit_p1.toString(), 1, "Financial", "Share income 1", record.getId(), usermoney);
			this.cashFlowService.insertWpCashFlow(user_p1.getUid(), 10, profit_p1.toString(), "一级IB用户收益", usermoney);
		}
		WpUserinfo user_p2 = this.userinfoService.selectWpUserinfoById(user_p1.getOid());
		if(user_p2 == null){
			return;
		}
		if(user_p2.getIbstatus() >= 1){
			BigDecimal profit_p2 = null;
			if(user_p2.getIbstatus() == 1){
				profit_p2 = profit.multiply(rete_p2);
			}else{
				profit_p2 = profit.multiply(BigDecimal.valueOf(0.15));
			}
			BigDecimal usermoney = user_p2.getUsermoney().add(profit_p2);
			this.userinfoService.updateMoney(profit_p2, user_p2.getUid());
			this.priceLogService.insertWpPriceLog(user_p2.getUid(), profit_p2.toString(), 1, "Financial", "Share income 2", record.getId(), usermoney);
			this.cashFlowService.insertWpCashFlow(user_p2.getUid(), 11, profit_p2.toString(), "二级IB用户收益", usermoney);
		}
		
		WpUserinfo user_p3 = this.userinfoService.selectWpUserinfoById(user_p2.getOid());
		if(user_p3 == null || user_p3.getIbstatus() == 0){
			return;
		}
		BigDecimal profit_p3 = null;
		if(user_p3.getIbstatus() == 1){
			profit_p3 = profit.multiply(rete_p3);
		}else{
			profit_p3 = profit.multiply(BigDecimal.valueOf(0.1));
		}
		BigDecimal usermoney = user_p3.getUsermoney().add(profit_p3);
		this.userinfoService.updateMoney(profit_p3, user_p3.getUid());
		this.priceLogService.insertWpPriceLog(user_p3.getUid(), profit_p3.toString(), 1, "Financial", "Share income 3", record.getId(), usermoney);
		this.cashFlowService.insertWpCashFlow(user_p3.getUid(), 16, profit_p3.toString(), "三级IB用户收益", usermoney);
	}
	
	private List<BigDecimal> rands(BigDecimal profitrate){
		List<BigDecimal> list = new ArrayList<BigDecimal>();
		BigDecimal rate = new BigDecimal(100000);
		BigDecimal profit = profitrate.multiply(rate).setScale(0, BigDecimal.ROUND_HALF_UP);
		Random random = new Random();
		int count = random.nextInt(5) + 4;//(0-4)+4=4到8条
		int down = 0;
		BigDecimal profited = new BigDecimal(0);
		for(int i = 0;i < count-1;i++){
			int randKey = 0;
			if(i == 0){
				randKey = profit.divide(new BigDecimal(2),0,BigDecimal.ROUND_HALF_UP).subtract(profited).intValue();
			}else{
				randKey = profit.subtract(profited).intValue();
			}
			int m = 0;
			if(randKey > 0){
				m = random.nextInt(randKey) + 1;
			}else{
				break;
			}
			if(m  > profit.divide(new BigDecimal(2),0,BigDecimal.ROUND_HALF_UP).intValue()){
				m = m/2;
			}
			if(down < 2 && random.nextInt(10) < 2){
				m *= -1;
				down++;
			}
			list.add(new BigDecimal(m).divide(rate));
			profited = profited.add(new BigDecimal(m));
		}
		BigDecimal last = profit.subtract(profited).divide(rate);
		if(last.doubleValue() > 0){
			list.add(profit.subtract(profited).divide(rate));
		}
		return list;
	}

	@Override
	@Transactional
	public AjaxResult createdata(WpFinancialRecord record) {
		if(StringUtils.isBlank(record.getUtel()) 
				|| null == record.getBuymoney()
				|| null == record.getProfit()
				|| null == record.getTypeid()
				|| null == record.getParams().get("endtime")//结束时间
				|| null == record.getParams().get("time")//条数
				){
			return AjaxResult.error("数据不完整");
		}
		WpUserinfo userinfo = this.userinfoService.selectUserByPhone(record.getUtel());
		if(userinfo == null){
			return AjaxResult.error("用户不存在");
		}
		WpFinancialType typeVo = this.typeService.selectWpFinancialTypeById(record.getTypeid());
		if(typeVo == null){
			return AjaxResult.error("产品不存在");
		}
		if(record.getBuymoney().compareTo(new BigDecimal(typeVo.getMinbuymoney())) < 0){
			return AjaxResult.error("金额过小");
		}
		long endTime = 0;
		int len = 1;
		try {
			endTime = DateUtils.parseDate(record.getParams().get("endtime").toString(), DateUtils.YYYYMMDD).getTime()/1000;
			len = Integer.valueOf(record.getParams().get("time").toString());
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return AjaxResult.error("异常" + e.getMessage());
		}
		Random random = new Random();
		BigDecimal creditmoney = new BigDecimal(0);
		BigDecimal totalMoney = record.getProfit().add(record.getBuymoney());
		long substrat = 86400*typeVo.getFreezedate();
		List<WpFinancialRecord> list = new ArrayList<>();
		for(int i = 0;i < len ;i++){
			WpFinancialRecord bean = new WpFinancialRecord();
			bean.setUid(userinfo.getUid());
			bean.setRete(BigDecimal.valueOf(typeVo.getRete()));
			bean.setBuymoney(record.getBuymoney());
			bean.setTypeid(record.getTypeid());
			bean.setCreditmoney(creditmoney);
			bean.setProfit(record.getProfit());
			bean.setRebate(typeVo.getRebate());//折扣
			int nextInt = random.nextInt(5);
			if(nextInt <= 2){//增加
				BigDecimal percent = new BigDecimal(random.nextInt(10)+1+100).divide(new BigDecimal(100));
				bean.setProfit(bean.getProfit().multiply(percent));
			}else if(nextInt == 3){//减少
				BigDecimal percent = new BigDecimal(100 -random.nextInt(7)+1).divide(new BigDecimal(100));
				bean.setProfit(bean.getProfit().multiply(percent));
			}
			bean.setTotalmoney(totalMoney);
			bean.setEndtime(endTime);
			bean.setBegintime(bean.getEndtime()-substrat);
			bean.setReturntime(bean.getEndtime()-86400*3);
			bean.setFreezedate(bean.getFreezedate());
			bean.setCreatetime(bean.getBegintime() +random.nextInt(68400) + 3600);
			bean.setStatus(1);
			bean.setType(2);
			if(i == 0){
				bean.setNextstep(1);
			}else{
				bean.setNextstep(0);
			}
			bean.setMonth(len + 1 - i);
			endTime = bean.getBegintime();
			list.add(bean);
		}
		Collections.reverse(list);
		Integer pid = null;
		for(WpFinancialRecord bean : list){
			if(bean.getMonth() > 1)
			bean.setPid(pid);
			save(bean);
			pid = bean.getId();
		}
		return AjaxResult.success();
	}
	private void save(WpFinancialRecord bean){
		Random random = new Random();
		
		this.insertWpFinancialRecord(bean);
		List<Integer> allDaily = new ArrayList<>();
		Calendar calendar = Calendar.getInstance();
		for(long begin = bean.getBegintime();begin < bean.getEndtime();){
			calendar.setTimeInMillis(begin * 1000);
			int week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
			if(week >= 1 && week <= 5){
				allDaily.add(Integer.valueOf(DateUtils.dateTime(calendar.getTime(), DateUtils.YYYYMMDD)));
			}
			begin+=86400;
		}
		BigDecimal totalRecordMoney = new BigDecimal(0);
		BigDecimal perMoney = bean.getProfit().divide(new BigDecimal(allDaily.size()), 0, BigDecimal.ROUND_HALF_UP);
		BigDecimal addOrno = perMoney.multiply(new BigDecimal(0.4)).setScale(0, BigDecimal.ROUND_HALF_UP);
		for(int i = 0; i < allDaily.size();i++){
			BigDecimal nowMoney = perMoney.multiply(new BigDecimal(i+1)).subtract(totalRecordMoney);
			BigDecimal begin = nowMoney.subtract(addOrno);
			BigDecimal end = nowMoney.add(addOrno);
			BigDecimal money = begin.add(new BigDecimal(random.nextInt(end.intValue() - begin.intValue())));
			if(i+1 == allDaily.size()){
				money = bean.getProfit().subtract(totalRecordMoney);
			}else{
				money = money.add(new BigDecimal(random.nextInt(100)).divide(new BigDecimal(100)));
			}
			totalRecordMoney = totalRecordMoney.add(money);
			
			WpFinancialDetail wpFinancialDetail = new WpFinancialDetail();
			wpFinancialDetail.setUid(bean.getUid());
			wpFinancialDetail.setRefid(bean.getId());
			wpFinancialDetail.setProfit(money);
			wpFinancialDetail.setRate(money.divide(bean.getBuymoney(), 5, BigDecimal.ROUND_HALF_UP));
			wpFinancialDetail.setDaily(allDaily.get(i));
			this.detailService.insertWpFinancialDetail(wpFinancialDetail);
		}
		
	}
}
