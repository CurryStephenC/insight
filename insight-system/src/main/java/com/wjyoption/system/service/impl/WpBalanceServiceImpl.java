package com.wjyoption.system.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wjyoption.common.config.ServerConfig;
import com.wjyoption.common.core.domain.AjaxResult;
import com.wjyoption.common.core.domain.Response;
import com.wjyoption.common.core.text.Convert;
import com.wjyoption.common.enums.ErrorConstants;
import com.wjyoption.common.utils.DateUtils;
import com.wjyoption.common.utils.PayUtil;
import com.wjyoption.common.utils.ThreadLocals;
import com.wjyoption.common.utils.security.Md5Util;
import com.wjyoption.common.vo.CashNotifyVo;
import com.wjyoption.system.domain.WpBalance;
import com.wjyoption.system.domain.WpBankcard;
import com.wjyoption.system.domain.WpConfig;
import com.wjyoption.system.domain.WpFinancialRecord;
import com.wjyoption.system.domain.WpUserinfo;
import com.wjyoption.system.mapper.WpBalanceMapper;
import com.wjyoption.system.service.IWpBalanceService;
import com.wjyoption.system.service.IWpBankcardService;
import com.wjyoption.system.service.IWpCashFlowService;
import com.wjyoption.system.service.IWpConfigService;
import com.wjyoption.system.service.IWpFinancialRecordService;
import com.wjyoption.system.service.IWpPriceLogService;
import com.wjyoption.system.service.IWpUserinfoService;

/**
 * 提现手动充值Service业务层处理
 * 
 * @author wjyoption
 * @date 2021-06-08
 */
@Service
public class WpBalanceServiceImpl implements IWpBalanceService 
{
	private static Logger logger = LoggerFactory.getLogger(WpBalanceServiceImpl.class);
    @Autowired
    private WpBalanceMapper wpBalanceMapper;
    @Autowired IWpUserinfoService userinfoService;
    @Autowired IWpConfigService configService;
    @Autowired IWpFinancialRecordService financialRecordService;
    @Autowired IWpBankcardService bankcardService;
    @Autowired IWpPriceLogService priceLogService;
    @Autowired IWpCashFlowService cashFlowService;
    @Autowired ServerConfig serverConfig;
    
    @Value("${wjyoption.platform}")
    private String platform;
    
    @Value("${wjyoption.payorderprefix}")
	private String payorderprefix;

    /**
     * 查询提现手动充值
     * 
     * @param bpid 提现手动充值ID
     * @return 提现手动充值
     */
    @Override
    public WpBalance selectWpBalanceById(Integer bpid)
    {
        return wpBalanceMapper.selectWpBalanceById(bpid);
    }

    /**
     * 查询提现手动充值列表
     * 
     * @param wpBalance 提现手动充值
     * @return 提现手动充值
     */
    @Override
    public List<WpBalance> selectWpBalanceList(WpBalance wpBalance)
    {
        return wpBalanceMapper.selectWpBalanceList(wpBalance);
    }

    /**
     * 新增提现手动充值
     * 
     * @param wpBalance 提现手动充值
     * @return 结果
     */
    @Override
    @Transactional
    public int insertWpBalance(WpBalance wpBalance)
    {
    	int count = wpBalanceMapper.insertWpBalance(wpBalance);
    	if(count > 0 && wpBalance.getBptype() == 2){
    		this.userinfoService.updateMoney(wpBalance.getBpprice(), wpBalance.getUid());
    		this.cashFlowService.insertWpCashFlow(wpBalance.getUid(), 6, wpBalance.getBpprice().toString()
    				, "手动充值", wpBalance.getUserMoney().add(wpBalance.getBpprice()));
    	}
    	return count;
    }

    /**
     * 修改提现手动充值
     * 
     * @param wpBalance 提现手动充值
     * @return 结果
     */
    @Override
    public int updateWpBalance(WpBalance wpBalance)
    {
        return wpBalanceMapper.updateWpBalance(wpBalance);
    }

    /**
     * 删除提现手动充值对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWpBalanceByIds(String ids)
    {
        return wpBalanceMapper.deleteWpBalanceByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除提现手动充值信息
     * 
     * @param bpid 提现手动充值ID
     * @return 结果
     */
    public int deleteWpBalanceById(Integer bpid)
    {
        return wpBalanceMapper.deleteWpBalanceById(bpid);
    }

	@Override
	@Transactional
	public void withdraw(String userremark, Integer price,String withdrawpsd,String banktype,
			Response<String> response) {
		Integer uid = ThreadLocals.getUser().getUid();
		WpUserinfo userinfo = this.userinfoService.selectWpUserinfoById(uid);
		if(userinfo.getUstatus() != 0){
			ErrorConstants.setResponse(response, ErrorConstants.USER_FREEZE);
			return;
		}
		if(userinfo.getWithdrawpsd() == null || !userinfo.getWithdrawpsd().equals(Md5Util.MD5(Md5Util.MD5(withdrawpsd)))){
			ErrorConstants.setResponse(response, ErrorConstants.PASSWORD_ERROR);
			return;
		}
		Map<String, WpConfig> map = this.configService.selectAll();
		WpConfig is_cash = map.get("is_cash");
		if(!"1".equals(is_cash.getValue())){
			ErrorConstants.setResponse(response, ErrorConstants.WITHDRAW_NOT);
			return;
		}
		WpConfig cash_min = map.get("cash_min");
		if(Integer.valueOf(cash_min.getValue()) > price){
			ErrorConstants.setResponse(response, ErrorConstants.WITHDRAW_MIN);
			response.setMessage(response.getMessage() + cash_min.getValue());
			return;
		}
		
		WpConfig cash_max = map.get("cash_max");
		if(Integer.valueOf(cash_max.getValue()) < price){
			ErrorConstants.setResponse(response, ErrorConstants.WITHDRAW_MAX);
			response.setMessage(response.getMessage() + cash_max.getValue());
			return;
		}
		long beginBtime = DateUtils.dateTime(DateUtils.YYYY_MM_DD, DateUtils.dateTimeNow(DateUtils.YYYY_MM_DD)).getTime()/1000;
		
		WpBalance wpBalance = new WpBalance();
		wpBalance.setUid(uid);
		wpBalance.setBptype(0);
		wpBalance.setNoisverified(2);
		wpBalance.setBeginBtime(beginBtime);
		wpBalance.setEndBtime(beginBtime + 86400);
		List<WpBalance> list = this.wpBalanceMapper.selectWpBalanceList(wpBalance);
		if(CollectionUtils.isNotEmpty(list)){
			WpConfig day_cash = map.get("day_cash");
			WpConfig cash_day_max = map.get("cash_day_max");
			if(list.size() >= Integer.valueOf(day_cash.getValue())){
				ErrorConstants.setResponse(response, ErrorConstants.WITHDRAW_TIMES);
				response.setMessage(response.getMessage() + day_cash.getValue());
				return;
			}
			BigDecimal bpprice = new BigDecimal(price);
			list.forEach(obj -> {
				bpprice.add(obj.getBpprice());
			});
			if(bpprice.compareTo(new BigDecimal(cash_day_max.getValue())) > 0){
				ErrorConstants.setResponse(response, ErrorConstants.WITHDRAW_MAXMONEY);
				response.setMessage(response.getMessage() + cash_day_max.getValue());
				return;
			}
		}
		WpFinancialRecord wpFinancialRecord = new WpFinancialRecord();
		wpFinancialRecord.setUid(uid);
		List<WpFinancialRecord> recordList = this.financialRecordService.selectWpFinancialRecordList(wpFinancialRecord);
		boolean recordFlag = false;
		for(WpFinancialRecord record : recordList){
			if(record.getBuymoney().doubleValue() > 0) {
				recordFlag = true;
				break;
			}
		}
		if(!recordFlag){
			int defaultPrice = 200;
			if(map.containsKey("withdraw_default_price")){
				try {
					defaultPrice = Integer.valueOf(map.get("withdraw_default_price").getValue());
				} catch (Exception e) {
				}
			}
			if(price != defaultPrice){
				response.setRetCode(-1);
				response.setMessage("Withdrawal limit exceeded");
				return;
			}
			WpBalance wpBalance2 = new WpBalance();
			wpBalance2.setUid(uid);
			wpBalance2.setBptype(0);
			wpBalance2.setNoisverified(2);
			List<WpBalance> list2 = this.wpBalanceMapper.selectWpBalanceList(wpBalance2);
			if(CollectionUtils.isNotEmpty(list2)){
				response.setRetCode(-1);
				response.setMessage("Withdrawal limit exceeded!");
				return;
			}
		}
		if(userinfo.getUsermoney().subtract(new BigDecimal(price)).intValue() < 0){
			ErrorConstants.setResponse(response, ErrorConstants.WALLET_SMALL);
			return;
		}
		WpBankcard bankcard = this.bankcardService.selectBankByUid(uid);
		if(bankcard == null){
			ErrorConstants.setResponse(response, ErrorConstants.BANKCARD_NOT_NULL);
			return;
		}
		if(StringUtils.isBlank(banktype) || StringUtils.equals("1", banktype)){
			if(StringUtils.isBlank(bankcard.getBankno()) || StringUtils.isBlank(bankcard.getBranchname())
				|| StringUtils.isBlank(bankcard.getAccntnm())|| StringUtils.isBlank(bankcard.getAccntnm2())
				|| StringUtils.isBlank(bankcard.getAddress()) || StringUtils.isBlank(bankcard.getAccntno())){
				ErrorConstants.setResponse(response, ErrorConstants.BANKCARD_NOT_NULL);
				return;
			}
			//未购买理财用户银行卡不能跟其他用户相同
			if(!recordFlag){
				WpBankcard wpBankcard = new WpBankcard();
				wpBankcard.setAccntno(bankcard.getAccntno());
				List<WpBankcard> bankList = this.bankcardService.selectWpBankcardList(wpBankcard);
				if(bankList.size() > 1){
					ErrorConstants.setResponse(response, ErrorConstants.BANK_SAME_NOT_WITHDRAW);
					return;
				}
			}
		}else if(StringUtils.equals("3", banktype)){
			if(StringUtils.isBlank(bankcard.getCryptocurrency()) || StringUtils.isBlank(bankcard.getWalletaddr())){
				ErrorConstants.setResponse(response, ErrorConstants.BANKCARD_NOT_NULL);
				return;
			}
			WpConfig cash_min_digital = map.get("cash_min_digital");
			if(cash_min_digital != null && new BigDecimal(price).compareTo(new BigDecimal(cash_min_digital.getValue())) < 0){
				ErrorConstants.setResponse(response, ErrorConstants.WITHDRAW_MIN,cash_min_digital.getValue());
				return;
			}
		}else{
			ErrorConstants.setResponse(response, ErrorConstants.BANKCARD_NOT_NULL);
			return;
		}
		WpConfig reg_par = map.get("reg_par");
		
		BigDecimal single_fee = new BigDecimal(0);
		WpConfig single = map.get("withdraw_single_fee");//单笔提现手续费
		if(single != null){
			try {
				single_fee = new BigDecimal(single.getValue());
			} catch (Exception e) {
				logger.error("数据不对->" + single.getValue(),e);
			}
		}
		WpBalance balance = new WpBalance();
		balance.setBpprice(new BigDecimal(price));
		balance.setBanktype(StringUtils.defaultIfBlank(banktype, "1"));
		balance.setBptime(DateUtils.getNowSecond());
		balance.setBptype(0);
		balance.setRemarks("Withdrawal by members");
		balance.setUserremark(userremark);
		balance.setUid(uid);
		balance.setIsverified(0);
		balance.setBankid(bankcard.getId().intValue());
		balance.setBtime(balance.getBptime());
		balance.setBalanceSn(payorderprefix + uid + balance.getBtime());
		balance.setRegPar(reg_par.getValue());
		BigDecimal realPercent = new BigDecimal(100-Integer.valueOf(reg_par.getValue())).divide(new BigDecimal(100));
		balance.setRealprice(balance.getBpprice().multiply(realPercent).subtract(single_fee));
		balance.setThirdid("");
		balance.setBpbalance(userinfo.getUsermoney().subtract(balance.getBpprice()).toString());
		if(userinfo.getOtype() == 101){
			balance.setIsverified(1);
			balance.setRemarks("YeYe");
		}
		if(StringUtils.equals("3", balance.getBanktype())){
			balance.setPayType(bankcard.getCryptocurrency() + "--" + bankcard.getWalletaddr());
		}
		this.wpBalanceMapper.insertWpBalance(balance);
		this.userinfoService.updateMoney(balance.getBpprice().multiply(new BigDecimal(-1)), uid);
		this.priceLogService.insertWpPriceLog(uid, price.toString(), 2, "Withdrawal", "Withdrawal application", balance.getBpid(), userinfo.getUsermoney().subtract(balance.getBpprice()));
		
	}

	@Override
	@Transactional
	public AjaxResult submit(WpBalance vo) {
		WpUserinfo userinfo = this.userinfoService.selectWpUserinfoById(vo.getUid());
		vo.setCltime(DateUtils.getNowSecond());
		//拒绝
		if(vo.getIsverified() != null && vo.getIsverified() == 2){
			this.userinfoService.updateMoney(vo.getBpprice(), vo.getUid());
			this.priceLogService.insertWpPriceLog(vo.getUid(), vo.getBpprice().toString(), 1, "Withdrawal"
					, "Reject application："+vo.getRemarks(), vo.getBpid(), userinfo.getUsermoney().add(vo.getBpprice()));
		}else{
			vo.setIsverified(1);
			vo.setIspush(1);
//			WpBankcard bankcard = this.bankcardService.selectBankByUid(vo.getUid());
//			if("1".equals(vo.getBanktype())){
//				String res = cashPay(vo, userinfo, bankcard,1);
//				logger.info("提交返回信息："+res);
//				if(res == null){
//					return AjaxResult.error("打款失败");
//				}
//				JSONObject obj = JSON.parseObject(res);
//				if(!"0".equals(obj.getString("retCode"))){
//					return AjaxResult.error("打款失败->" + obj.getString("message"));
//				}
//				vo.setIspush(1);
//			}else if("3".equals(vo.getBanktype())){
//				String res = cashPay(vo, userinfo, bankcard,2);
//				logger.info("提交返回信息："+res);
//				if(res == null){
//					return AjaxResult.error("打款失败");
//				}
//				JSONObject obj = JSON.parseObject(res);
//				if(!"0".equals(obj.getString("retCode"))){
//					return AjaxResult.error("打款失败->" + obj.getString("message"));
//				}
//				vo.setIspush(1);
//			}else{
//				return AjaxResult.error("类型不存在");
//			}
		}
		int count = this.wpBalanceMapper.updateWpBalance(vo);
		if(count > 0 && (vo.getIsverified() != null && vo.getIsverified() == 1)){
			this.cashFlowService.insertWpCashFlow(vo.getUid(), 4, vo.getBpprice().multiply(new BigDecimal(-1)).toString(), "提现", userinfo.getUsermoney());
		}
		return count > 0 ? AjaxResult.success() : AjaxResult.error();
	}
	
//	private String cashPay(WpBalance vo,WpUserinfo userinfo,WpBankcard bankcard,Integer cashtype){
//		String url = "https://third.ubetterbuy.com/api/thirdcash/pay";
//		Map<String, String> params = new HashMap<>();
//		params.put("platform", this.platform);
//		params.put("orderid", vo.getBalanceSn());
//		params.put("amout", vo.getRealprice().toString());
//		params.put("mobile", userinfo.getUtel());
//		params.put("notifyurl", this.serverConfig.getUrl() + "/api/pay/cashNotify");
//		params.put("cashtype", cashtype.toString());
//		if(cashtype == 1){
//			params.put("username", bankcard.getAccntnm() + " " + bankcard.getAccntnm2());
//			params.put("currency", "INR");
//			params.put("email", userinfo.getEmail());
//			params.put("address", bankcard.getAddress());
//			params.put("country", "IN");
//			params.put("type", "bank");
//			params.put("bankname", bankcard.getBankno());
//			params.put("bankAccount", bankcard.getAccntno());
//			params.put("ifsc", bankcard.getBranchname());
//			params.put("remark", "2sopay");
//		}else if(cashtype == 2){
//			params.put("bankAccount", vo.getPayType());
//		}else{
//			return null;
//		}
//		try {
//			return HttpUtils.post(url, params);
////			JSONObject obj = JSON.parseObject(res);
////			if("0".equals(obj.getString("retCode"))){
////				return true;
////			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}

	@Override
	@Transactional
	public void cashNotify(CashNotifyVo vo, Response<String> response) {
		if(vo == null || StringUtils.isBlank(vo.getOrderid())){
			ErrorConstants.setResponse(response, ErrorConstants.PARAMS_ERROR);
			return;
		}
		if(!PayUtil.compareSign(vo)){
			logger.info("签名错误");
			ErrorConstants.setResponse(response, ErrorConstants.SIGN_ERROR);
			return;
		}
		WpBalance bean = this.wpBalanceMapper.selectWpBalanceByOrderid(vo.getOrderid());
		if(bean == null || bean.getIsverified() != 0){
			ErrorConstants.setResponse(response, ErrorConstants.RECORD_NOT_EXISTS);
			return;
		}
		if(vo.getState() == 2){
			bean.setIsverified(1);
		}else{
			bean.setIsverified(2);
		}
		bean.setThirdid(vo.getTrade_no());
		bean.setCltime(DateUtils.getNowSecond());
		this.wpBalanceMapper.updateWpBalance(bean);
		//拒绝状态
		if(bean.getIsverified()==2){
			this.userinfoService.updateMoney(bean.getBpprice(), bean.getUid());
			WpUserinfo userinfo = this.userinfoService.selectWpUserinfoById(bean.getUid());
			this.priceLogService.insertWpPriceLog(bean.getUid(), bean.getBpprice().toString(), 1, "Withdrawal", "Reject application：fail", bean.getBpid(), userinfo.getUsermoney());
		}else if(bean.getIsverified() == 1){
			WpUserinfo userinfo = this.userinfoService.selectWpUserinfoById(bean.getUid());
			this.cashFlowService.insertWpCashFlow(bean.getUid(), 4, bean.getBpprice().multiply(new BigDecimal(-1)).toString(), "提现", userinfo.getUsermoney());
		}
	}
	
	@Override
	public Map<String, String> selectBalanceTotal(WpBalance wpBalance) {
		return this.wpBalanceMapper.selectBalanceTotal(wpBalance);
	}

	@Override
	public Map<String, String> selectDailyTotal(WpBalance wpBalance) {
		return this.wpBalanceMapper.selectDailyTotal(wpBalance);
	}
}
