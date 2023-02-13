package com.wjyoption.system.mapper;

import java.util.List;

import com.wjyoption.system.vo.report.StatuserReport;

public interface StatUserMapper {

	/**
	 * 查询所有部门列表
	 * @return
	 */
	public List<StatuserReport> selectAllDept();
	
	/**
	 * 查询指定部门用户信息列表
	 * @param deptId
	 * @return
	 */
	public List<StatuserReport> selectTopList(Long deptId);
	
	/**
	 * 查询充值统计
	 * @param param
	 * @return
	 */
	public StatuserReport selectRecharge(StatuserReport param);
	
	/**
	 * 查询提现统计
	 * @param param
	 * @return
	 */
	public StatuserReport selectWithdraw(StatuserReport param);
	
	/**
	 * 注册用户
	 * @param param
	 * @return
	 */
	public Integer selectRegisterNum(StatuserReport param);
	
}
