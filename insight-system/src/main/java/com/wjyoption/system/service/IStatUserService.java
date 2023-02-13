package com.wjyoption.system.service;

import java.util.List;

import com.wjyoption.system.vo.report.StatuserReport;

public interface IStatUserService {

	
	/**
	 * 查询部门统计
	 * @param param
	 * @return
	 */
	public List<StatuserReport> selectDeptStat(StatuserReport param);
	
	/**
	 * 查询部门统计详情
	 * @param param
	 * @return
	 */
	public List<StatuserReport> selectDeptDetail(StatuserReport param);
	
	
}
