package com.wjyoption.system.vo.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

import com.wjyoption.system.domain.WpBalance;

@ApiModel("提现手动充值模块")
public class BalanceResp implements Serializable{

	private static final long serialVersionUID = -8425200559182058248L;

	public BalanceResp(){}
	public BalanceResp(WpBalance bean){
		this.bptime = bean.getBptime();
		this.bpprice = bean.getBpprice();
		this.remarks = bean.getRemarks();
		this.isverified = bean.getIsverified();
		this.cltime = bean.getCltime();
		this.bpbalance = bean.getBpbalance();
		this.balanceSn = bean.getBalanceSn();
		this.userremark = bean.getUserremark();
		this.ispush = bean.getIspush();
	}
	
	
    /** 操作时间 */
    @ApiModelProperty("提交时间")
    private Long bptime;

    /** 收支金额 */
    @ApiModelProperty("收支金额")
    private BigDecimal bpprice;

    /** 备注 */
    @ApiModelProperty("备注")
    private String remarks;

    /** 判断申请是否通过 */
    @ApiModelProperty("判断申请是否通过0：待审核，1、通过，2、拒绝")
    private Integer isverified;

    /** 审核时间 */
    @ApiModelProperty("审核时间")
    private Long cltime;

    /** 余额 */
    @ApiModelProperty("余额")
    private String bpbalance;

    /** 订单编号 */
    @ApiModelProperty("订单编号")
    private String balanceSn;

    /** 用户备注(转移用户) */
    @ApiModelProperty("用户手动备注")
    private String userremark;
    
    /** 是否推送0：待推送，1：已推送 */
    @ApiModelProperty("是否推送0：待推送，1：已推送 ")
    private Integer ispush;

	public Long getBptime() {
		return bptime;
	}

	public BigDecimal getBpprice() {
		return bpprice;
	}

	public String getRemarks() {
		return remarks;
	}

	public Integer getIsverified() {
		return isverified;
	}

	public Long getCltime() {
		return cltime;
	}

	public String getBpbalance() {
		return bpbalance;
	}

	public String getBalanceSn() {
		return balanceSn;
	}

	public String getUserremark() {
		return userremark;
	}

	public void setBptime(Long bptime) {
		this.bptime = bptime;
	}

	public void setBpprice(BigDecimal bpprice) {
		this.bpprice = bpprice;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public void setIsverified(Integer isverified) {
		this.isverified = isverified;
	}

	public void setCltime(Long cltime) {
		this.cltime = cltime;
	}

	public void setBpbalance(String bpbalance) {
		this.bpbalance = bpbalance;
	}

	public void setBalanceSn(String balanceSn) {
		this.balanceSn = balanceSn;
	}

	public void setUserremark(String userremark) {
		this.userremark = userremark;
	}
	public Integer getIspush() {
		return ispush;
	}
	public void setIspush(Integer ispush) {
		this.ispush = ispush;
	}
    
	
}
