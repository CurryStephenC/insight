package com.wjyoption.system.service;

import com.wjyoption.common.core.domain.Response;
import com.wjyoption.system.domain.WpOnboard;
import com.wjyoption.system.vo.resp.SignInMsgResp;

import java.util.List;

/**
 * 用户签到Service接口
 * 
 * @author wjyoption
 * @date 2021-06-11
 */
public interface IWpOnboardService 
{
    /**
     * 查询用户签到
     * 
     * @param id 用户签到ID
     * @return 用户签到
     */
    public WpOnboard selectWpOnboardById(Integer id);

    /**
     * 查询用户签到列表
     * 
     * @param wpOnboard 用户签到
     * @return 用户签到集合
     */
    public List<WpOnboard> selectWpOnboardList(WpOnboard wpOnboard);

    /**
     * 新增用户签到
     * 
     * @param wpOnboard 用户签到
     * @return 结果
     */
    public int insertWpOnboard(WpOnboard wpOnboard);

    /**
     * 修改用户签到
     * 
     * @param wpOnboard 用户签到
     * @return 结果
     */
    public int updateWpOnboard(WpOnboard wpOnboard);

    /**
     * 批量删除用户签到
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWpOnboardByIds(String ids);

    /**
     * 删除用户签到信息
     * 
     * @param id 用户签到ID
     * @return 结果
     */
    public int deleteWpOnboardById(Integer id);

    /**
     * 签到
     * @param response
     */
	public void onboard(Response<Object> response);

	
	public SignInMsgResp queryMySignIn();
	
	/**
	 * 清除当天签到信息redis缓存
	 * @param uid
	 */
	public void removeSignInMsgRedis(Integer uid);
}
