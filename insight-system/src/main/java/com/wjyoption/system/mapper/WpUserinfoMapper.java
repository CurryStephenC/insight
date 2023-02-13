package com.wjyoption.system.mapper;

import java.util.List;

import com.wjyoption.common.core.domain.Ztree;
import com.wjyoption.system.domain.WpUserinfo;
import com.wjyoption.system.vo.resp.ChildListResp;

/**
 * 前端用户Mapper接口
 * 
 * @author hs
 * @date 2021-06-03
 */
public interface WpUserinfoMapper 
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
     * 删除前端用户
     * 
     * @param uid 前端用户ID
     * @return 结果
     */
    public int deleteWpUserinfoById(Integer uid);

    /**
     * 批量删除前端用户
     * 
     * @param uids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWpUserinfoByIds(String[] uids);
    /**
     * 指定日期数量
     * @param uids
     * @return
     */
    public int selectDailyCount(WpUserinfo daily);

	public WpUserinfo selectUserByPhone(String phone);

	/**
	 * @param usermoney 增加改字段
	 * @return
	 */
	public int updateMoney(WpUserinfo user);
	
	
	
	
	
	
	/**
	 * 查询下级信息
	 * @param wpUserinfo
	 * @return
	 */
	public List<ChildListResp> selectChildList(ChildListResp wpUserinfo);

	/**
	 * 设置所有用户为非IB身份
	 */
	public void updateAllNoib();

	public List<Ztree> selectUserinfoTree(Integer oid);

	/**
	 * 设置所有用户无法购买理财
	 */
	public void setAllNoOrderPrice();
}
