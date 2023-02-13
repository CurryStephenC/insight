package com.wjyoption.system.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wjyoption.system.mapper.WpCheckinRewardUserMapper;
import com.wjyoption.system.domain.WpCheckinRewardUser;
import com.wjyoption.system.domain.WpUserinfo;
import com.wjyoption.system.service.IWpCashFlowService;
import com.wjyoption.system.service.IWpCheckinRewardUserService;
import com.wjyoption.system.service.IWpOnboardService;
import com.wjyoption.system.service.IWpPriceLogService;
import com.wjyoption.system.service.IWpUserinfoService;
import com.wjyoption.common.core.domain.Response;
import com.wjyoption.common.core.text.Convert;
import com.wjyoption.common.enums.ErrorConstants;
import com.wjyoption.common.utils.ThreadLocals;
import com.wjyoption.common.vo.UserLogin;

/**
 * 用户累计签到奖励Service业务层处理
 * 
 * @author wjyoption
 * @date 2021-06-12
 */
@Service
public class WpCheckinRewardUserServiceImpl implements IWpCheckinRewardUserService 
{
    @Autowired
    private WpCheckinRewardUserMapper wpCheckinRewardUserMapper;
    @Autowired IWpUserinfoService userinfoService;
    @Autowired IWpPriceLogService priceLogService;
	@Autowired IWpCashFlowService cashFlowService;
	@Autowired IWpOnboardService onboardService;

    /**
     * 查询用户累计签到奖励
     * 
     * @param id 用户累计签到奖励ID
     * @return 用户累计签到奖励
     */
    @Override
    public WpCheckinRewardUser selectWpCheckinRewardUserById(Integer id)
    {
        return wpCheckinRewardUserMapper.selectWpCheckinRewardUserById(id);
    }

    /**
     * 查询用户累计签到奖励列表
     * 
     * @param wpCheckinRewardUser 用户累计签到奖励
     * @return 用户累计签到奖励
     */
    @Override
    public List<WpCheckinRewardUser> selectWpCheckinRewardUserList(WpCheckinRewardUser wpCheckinRewardUser)
    {
        return wpCheckinRewardUserMapper.selectWpCheckinRewardUserList(wpCheckinRewardUser);
    }

    /**
     * 新增用户累计签到奖励
     * 
     * @param wpCheckinRewardUser 用户累计签到奖励
     * @return 结果
     */
    @Override
    public int insertWpCheckinRewardUser(WpCheckinRewardUser wpCheckinRewardUser)
    {
        return wpCheckinRewardUserMapper.insertWpCheckinRewardUser(wpCheckinRewardUser);
    }

    /**
     * 修改用户累计签到奖励
     * 
     * @param wpCheckinRewardUser 用户累计签到奖励
     * @return 结果
     */
    @Override
    public int updateWpCheckinRewardUser(WpCheckinRewardUser wpCheckinRewardUser)
    {
        return wpCheckinRewardUserMapper.updateWpCheckinRewardUser(wpCheckinRewardUser);
    }

    /**
     * 删除用户累计签到奖励对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWpCheckinRewardUserByIds(String ids)
    {
        return wpCheckinRewardUserMapper.deleteWpCheckinRewardUserByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除用户累计签到奖励信息
     * 
     * @param id 用户累计签到奖励ID
     * @return 结果
     */
    public int deleteWpCheckinRewardUserById(Integer id)
    {
        return wpCheckinRewardUserMapper.deleteWpCheckinRewardUserById(id);
    }

	@Override
	@Transactional
	public void signinTotalReward(Integer id, Response<Object> response) {
		UserLogin user = ThreadLocals.getUser();
		WpCheckinRewardUser vo = this.wpCheckinRewardUserMapper.selectWpCheckinRewardUserById(id);
		if(vo == null || vo.getUid().intValue() != user.getUid().intValue() || vo.getStatus() != 1){
			ErrorConstants.setResponse(response, ErrorConstants.FAIL);
			return;
		}
		WpCheckinRewardUser update = new WpCheckinRewardUser();
		update.setId(vo.getId());
		update.setStatus(2);
		this.wpCheckinRewardUserMapper.updateWpCheckinRewardUser(update);
		
		this.userinfoService.updateMoney(new BigDecimal(vo.getMoney()), user.getUid());
		WpUserinfo userinfo = this.userinfoService.selectWpUserinfoById(user.getUid());
		BigDecimal nowMoney = userinfo.getUsermoney();
		this.priceLogService.insertWpPriceLog(user.getUid(), vo.getMoney().toString(), 1, "Sign in", "Cumulative sign-in reward", id, nowMoney);
		this.cashFlowService.insertWpCashFlow(user.getUid(), 15, vo.getMoney().toString(), "累计签到奖励", nowMoney);
		
		//清除缓存
		this.onboardService.removeSignInMsgRedis(user.getUid());
	}
}
