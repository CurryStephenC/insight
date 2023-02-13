package com.wjyoption.system.vo.report;

import java.math.BigDecimal;
import java.util.List;

/**
 * 用户统计
 * @author 
 *
 */
public class StatuserReport {

	/** 部门ID */
    private Long deptId;
    private String deptName;//部门名称
    private String uids;//部门uid集合
    
    /***用户ID***/
    private Integer uid;
    private String utel;
    
    /** 注册人数 */
    private Integer registnum;
    /** 充值人数 */
    private Integer rechargenum;
    /** 充值金额 */
    private BigDecimal rechargemoney;
    /** 提现人数 */
    private Integer withdrawnum;
    /** 提现金额 */
    private BigDecimal withdrawmoney;
    
    /** 差额 */
    private BigDecimal subtract;
    
    //查询条件：开始时间
    private Integer begintime;
    //查询条件：结束时间
    private Integer endtime;
    //销售ID集合
    private List<String> topids;
    //销售ID
    private Integer topid;

	public Long getDeptId() {
		return deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public String getUids() {
		return uids;
	}

	public Integer getUid() {
		return uid;
	}

	public String getUtel() {
		return utel;
	}

	public Integer getRegistnum() {
		return registnum;
	}

	public Integer getRechargenum() {
		return rechargenum;
	}


	public Integer getWithdrawnum() {
		return withdrawnum;
	}

	public BigDecimal getWithdrawmoney() {
		return withdrawmoney;
	}

	public BigDecimal getSubtract() {
		return subtract;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public void setUids(String uids) {
		this.uids = uids;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public void setUtel(String utel) {
		this.utel = utel;
	}

	public void setRegistnum(Integer registnum) {
		this.registnum = registnum;
	}

	public void setRechargenum(Integer rechargenum) {
		this.rechargenum = rechargenum;
	}


	public void setWithdrawnum(Integer withdrawnum) {
		this.withdrawnum = withdrawnum;
	}

	public void setWithdrawmoney(BigDecimal withdrawmoney) {
		this.withdrawmoney = withdrawmoney;
	}

	public void setSubtract(BigDecimal subtract) {
		this.subtract = subtract;
	}

	public Integer getBegintime() {
		return begintime;
	}

	public Integer getEndtime() {
		return endtime;
	}

	public void setBegintime(Integer begintime) {
		this.begintime = begintime;
	}

	public void setEndtime(Integer endtime) {
		this.endtime = endtime;
	}

	public List<String> getTopids() {
		return topids;
	}

	public void setTopids(List<String> topids) {
		this.topids = topids;
	}

	public Integer getTopid() {
		return topid;
	}

	public void setTopid(Integer topid) {
		this.topid = topid;
	}

	public BigDecimal getRechargemoney() {
		return rechargemoney;
	}

	public void setRechargemoney(BigDecimal rechargemoney) {
		this.rechargemoney = rechargemoney;
	}

}
