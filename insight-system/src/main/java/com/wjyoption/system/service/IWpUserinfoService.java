package com.wjyoption.system.service;

import java.math.BigDecimal;
import java.util.List;

import com.wjyoption.common.core.domain.Ztree;
import com.wjyoption.common.vo.UserLogin;
import com.wjyoption.system.domain.WpUserinfo;
import com.wjyoption.system.domain.WpuserinfoLow;
import com.wjyoption.system.vo.resp.ChildListResp;

/**
 * 前端用户Service接口
 * 
 * @author hs
 * @date 2021-06-03
 */
public interface IWpUserinfoService 
{
    /**
     * 查询前端用户
     * 
     * @param uid 前端用户ID
     * @return 前端用户
     */
    public WpUserinfo selectWpUserinfoById(Integer uid);

    /**
     * 查询前端用户列表
     * 
     * @param wpUserinfo 前端用户
     * @return 前端用户集合
     */
    public List<WpUserinfo> selectWpUserinfoList(WpUserinfo wpUserinfo);

    /**
     * 新增前端用户
     * 
     * @param wpUserinfo 前端用户
     * @return 结果
     */
    public int insertWpUserinfo(WpUserinfo wpUserinfo);

    /**
     * 修改前端用户
     * 
     * @param wpUserinfo 前端用户
     * @return 结果
     */
    public int updateWpUserinfo(WpUserinfo wpUserinfo);

    /**
     * 批量删除前端用户
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWpUserinfoByIds(String ids);

    /**
     * 删除前端用户信息
     * 
     * @param uid 前端用户ID
     * @return 结果
     */
    public int deleteWpUserinfoById(Integer uid);

	public WpUserinfo selectUserByPhone(String phone);
	
	/**
	 * 修改用户金额
	 * @param money 增加改字段的金额
	 * @return
	 */
	public int updateMoney(BigDecimal money,Integer uid);
	/**
	 * 修改用户金额
	 * @param money 增加改字段的金额
	 * @param newtask 新手任务：0未完成，1完成
	 * @return
	 */
	public int updateMoney(BigDecimal money,Integer uid,Integer newtask);
	
	public void updateUserToRedis(UserLogin user);
	
	/**
     * 指定日期数量
     * @param uids
     * @return
     */
    public int selectDailyCount(WpUserinfo daily);
	
	
	
	
	
	/**
	 * 查询下级信息
	 * @param wpUserinfo
	 * @return
	 */
	public List<ChildListResp> selectChildList(ChildListResp wpUserinfo);

	/**
	 * 更新用户IB信息
	 */
	public void updateIbStatus();

	public List<Ztree> selectUserinfoTree(WpUserinfo wpUserinfo);

	/**
	 * 冻结
	 * @param uid
	 * @return
	 */
	public void freeze(Integer uid);

	/**
	 * 修改用户状态
	 * @param uid
	 * @param ustatus
	 * @return
	 */
	public int updateUserStatus(Integer uid, Integer ustatus);

	/**
	 * 查询下级列表
	 * @param oid
	 * @return
	 */
	public List<Ztree> selectUserinfoTree(Integer oid);
	
	public List<WpuserinfoLow> exportUserinfoTree(Integer oid);

	/**
	 * 更新下单金额限制
	 */
	public void updateOrderPrice();
}
