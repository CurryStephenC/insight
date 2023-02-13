package com.wjyoption.system.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wjyoption.common.core.domain.Response;
import com.wjyoption.common.core.text.Convert;
import com.wjyoption.common.utils.DateUtils;
import com.wjyoption.common.utils.ThreadLocals;
import com.wjyoption.common.utils.db.RedisEnum;
import com.wjyoption.common.vo.UserLogin;
import com.wjyoption.system.domain.WpCashFlow;
import com.wjyoption.system.domain.WpCheckinReward;
import com.wjyoption.system.domain.WpCheckinRewardDaily;
import com.wjyoption.system.domain.WpCheckinRewardUser;
import com.wjyoption.system.domain.WpFinancialRecord;
import com.wjyoption.system.domain.WpOnboard;
import com.wjyoption.system.domain.WpUserinfo;
import com.wjyoption.system.mapper.WpOnboardMapper;
import com.wjyoption.system.service.IWpCashFlowService;
import com.wjyoption.system.service.IWpCheckinRewardDailyService;
import com.wjyoption.system.service.IWpCheckinRewardService;
import com.wjyoption.system.service.IWpCheckinRewardUserService;
import com.wjyoption.system.service.IWpFinancialRecordService;
import com.wjyoption.system.service.IWpOnboardService;
import com.wjyoption.system.service.IWpPriceLogService;
import com.wjyoption.system.service.IWpUserinfoService;
import com.wjyoption.system.vo.resp.SignInMsgResp;

/**
 * 用户签到Service业务层处理
 * 
 * @author wjyoption
 * @date 2021-06-11
 */
@Service
public class WpOnboardServiceImpl implements IWpOnboardService 
{
    @Autowired
    private WpOnboardMapper wpOnboardMapper;
    @Autowired IWpUserinfoService userinfoService;
    @Autowired IWpPriceLogService priceLogService;
    @Autowired IWpCashFlowService cashFlowService;
    @Autowired IWpFinancialRecordService recordService;
    @Autowired IWpCheckinRewardDailyService checkinRewardDailyService;
    @Autowired IWpCheckinRewardService checkinRewardService;
    @Autowired IWpCheckinRewardUserService checkinRewardUserService;

    @SuppressWarnings("rawtypes")
	@Autowired RedisTemplate redisTemplate;
    
    /**
     * 查询用户签到
     * 
     * @param id 用户签到ID
     * @return 用户签到
     */
    @Override
    public WpOnboard selectWpOnboardById(Integer id)
    {
        return wpOnboardMapper.selectWpOnboardById(id);
    }

    /**
     * 查询用户签到列表
     * 
     * @param wpOnboard 用户签到
     * @return 用户签到
     */
    @Override
    public List<WpOnboard> selectWpOnboardList(WpOnboard wpOnboard)
    {
        return wpOnboardMapper.selectWpOnboardList(wpOnboard);
    }

    /**
     * 新增用户签到
     * 
     * @param wpOnboard 用户签到
     * @return 结果
     */
    @Override
    public int insertWpOnboard(WpOnboard wpOnboard)
    {
        return wpOnboardMapper.insertWpOnboard(wpOnboard);
    }

    /**
     * 修改用户签到
     * 
     * @param wpOnboard 用户签到
     * @return 结果
     */
    @Override
    public int updateWpOnboard(WpOnboard wpOnboard)
    {
        return wpOnboardMapper.updateWpOnboard(wpOnboard);
    }

    /**
     * 删除用户签到对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWpOnboardByIds(String ids)
    {
        return wpOnboardMapper.deleteWpOnboardByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除用户签到信息
     * 
     * @param id 用户签到ID
     * @return 结果
     */
    public int deleteWpOnboardById(Integer id)
    {
        return wpOnboardMapper.deleteWpOnboardById(id);
    }

	@Override
	@Transactional
	public void onboard(Response<Object> response) {
		UserLogin user = ThreadLocals.getUser();
		WpOnboard wpOnboard = new WpOnboard();
		wpOnboard.setUid(user.getUid());
		wpOnboard.setDaily(Integer.valueOf(DateUtils.dateTimeNow(DateUtils.YYYYMMDD)));
		List<WpOnboard> list = this.wpOnboardMapper.selectWpOnboardList(wpOnboard);
		if(CollectionUtils.isNotEmpty(list)){
			return;
		}
		wpOnboard.setCreatetime(System.currentTimeMillis()/1000);
		int money = this.getCheckinDaily(user.getUid());
		this.wpOnboardMapper.insertWpOnboard(wpOnboard);
		if(money > 0){
			WpUserinfo userinfo = this.userinfoService.selectWpUserinfoById(user.getUid());
			this.userinfoService.updateMoney(new BigDecimal(money), user.getUid());
			BigDecimal usermoney = userinfo.getUsermoney().add(new BigDecimal(money));
			this.priceLogService.insertWpPriceLog(user.getUid(), money+"", 1, "Sign in", "Sign-in reward", wpOnboard.getId(), usermoney);
			this.cashFlowService.insertWpCashFlow(user.getUid(), 14, money+"", "签到奖励", usermoney);
		}
		WpCheckinRewardUser bean = this.onboradTotal(user.getUid());
		if(bean != null){
			this.checkinRewardUserService.insertWpCheckinRewardUser(bean);
		}
		this.removeSignInMsgRedis(user.getUid());
	}
	
	private int getCheckinDaily(Integer uid){
		WpFinancialRecord wpFinancialRecord = new WpFinancialRecord();
		wpFinancialRecord.setStatus(0);
		wpFinancialRecord.setUid(uid);
		List<WpFinancialRecord> list = this.recordService.selectWpFinancialRecordList(wpFinancialRecord);
		BigDecimal totalMoney = new BigDecimal(0);
		for(WpFinancialRecord record : list){
			totalMoney = totalMoney.add(record.getBuymoney());
		}
		
		List<WpCheckinRewardDaily> dailyList = this.checkinRewardDailyService.selectWpCheckinRewardDailyList(new WpCheckinRewardDaily());
		dailyList.sort(Comparator.comparing(WpCheckinRewardDaily::getMinmoney).reversed());
		for(WpCheckinRewardDaily daily : dailyList){
			if(totalMoney.intValue() >= daily.getMinmoney()){
				Integer money = daily.getMoney();
				if(daily.getTimes() != null && daily.getTimes() != 0 && daily.getTimes() > 0){
					long beginSecond = DateUtils.getNowSecond()-86400*daily.getTimes();
					WpOnboard wpOnboard = new WpOnboard();
					wpOnboard.setUid(uid);
					wpOnboard.setBeginDaily(Integer.parseInt(DateUtils.parseDateToStr(DateUtils.YYYYMMDD, new Date(beginSecond * 1000))));
					List<WpOnboard> onboardList = this.wpOnboardMapper.selectWpOnboardList(wpOnboard);
					if(onboardList.size() >= daily.getTimes() - 1){
						money = daily.getMoney2();
					}
				}
				return money;
			}
		}
		return 0;
	}
	
	private WpCheckinRewardUser onboradTotal(Integer uid){
		List<WpCheckinReward> list = this.checkinRewardService.selectWpCheckinRewardList(new WpCheckinReward());
		list.sort(Comparator.comparing(WpCheckinReward::getId).reversed());
		WpOnboard wpOnboard = new WpOnboard();
		wpOnboard.setUid(uid);
		List<WpOnboard> list2 = this.wpOnboardMapper.selectWpOnboardList(wpOnboard);
		int onboradCount = list2.size();
		WpCheckinRewardUser bean = null;
		for(WpCheckinReward v : list){
			if(onboradCount >= v.getTimes()){
				WpCheckinRewardUser wpCheckinRewardUser = new WpCheckinRewardUser();
				wpCheckinRewardUser.setUid(uid);
				wpCheckinRewardUser.setRefid(v.getId());
				List<WpCheckinRewardUser> list3 = this.checkinRewardUserService.selectWpCheckinRewardUserList(wpCheckinRewardUser);
				if(CollectionUtils.isEmpty(list3)){
					bean = new WpCheckinRewardUser();
					bean.setUid(uid);
					bean.setRefid(v.getId());
					bean.setMoney(v.getMoney());
					bean.setCreatetime(DateUtils.getNowSecond());
				}
				break;
			}
		}
		return bean;
	}

	@SuppressWarnings("unchecked")
	@Override
	public SignInMsgResp queryMySignIn() {
		String dateTimeNow = DateUtils.dateTimeNow(DateUtils.YYYYMMDD);
		UserLogin user = ThreadLocals.getUser();
		String key = onboardRedisKey(user.getUid(),dateTimeNow); 
		BoundValueOperations<String,SignInMsgResp> boundValueOps = this.redisTemplate.boundValueOps(key);
		SignInMsgResp resp = boundValueOps.get();
		if(resp != null){
			return resp;
		}
		resp = new SignInMsgResp();
		WpOnboard wpOnboard = new WpOnboard();
		wpOnboard.setUid(user.getUid());
		wpOnboard.setDaily(Integer.parseInt(dateTimeNow));
		List<WpOnboard> list = this.wpOnboardMapper.selectWpOnboardList(wpOnboard);
		resp.setOnboardStatus(CollectionUtils.isEmpty(list) ? 0 : 1);
		List<Integer> types = new ArrayList<Integer>();
		types.add(14);
		types.add(15);
		WpCashFlow flow = new WpCashFlow();
		flow.setUid(user.getUid());
		flow.setTypes(types);
		String totalMoney = this.cashFlowService.selectTotalMoney(flow);
		resp.setReward(StringUtils.isBlank(totalMoney) ? 0 : new BigDecimal(totalMoney).intValue());
		
		wpOnboard = new WpOnboard();
		wpOnboard.setUid(user.getUid());
		List<WpOnboard> list2 = this.wpOnboardMapper.selectWpOnboardList(wpOnboard);
		resp.setSignincount(list2.size());
		boundValueOps.set(resp,1,TimeUnit.HOURS);
		return resp;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void removeSignInMsgRedis(Integer uid){
		String dateTimeNow = DateUtils.dateTimeNow(DateUtils.YYYYMMDD);
		String key = onboardRedisKey(uid, dateTimeNow);
		this.redisTemplate.delete(key);
	}
	
	private String onboardRedisKey(Integer uid,String dateTimeNow){
		return RedisEnum.SIGNIN_MSG_RECORD.getKeyPrefix() + dateTimeNow + "_" + uid; 
	}
}
