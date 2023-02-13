package com.wjyoption.system.vo.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

import com.wjyoption.system.domain.CodepayOrder;

@ApiModel("支付Model")
public class PayResp implements Serializable{

	private static final long serialVersionUID = -6566020107538174809L;
	
	public PayResp(){}
	public PayResp(CodepayOrder order){
		this.id=order.getId();
		this.money = order.getMoney();
		this.payNo = order.getPayNo();
		this.status = order.getStatus();
		this.creatTime = order.getCreatTime();
	}
	
	
	@ApiModelProperty("记录ID")
	private Long id;
	/** 实际金额 */
    @ApiModelProperty("实际金额")
    private BigDecimal money;
    
    /** 流水号 */
    @ApiModelProperty("流水号")
    private String payNo;

    /** 订单状态 */
    @ApiModelProperty("订单状态 0:Wait,1:Success,2:Fail")
    private Integer status;

    /** 创建时间 */
    @ApiModelProperty("创建时间")
    private Long creatTime;

	public Long getId() {
		return id;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public String getPayNo() {
		return payNo;
	}

	public Integer getStatus() {
		return status;
	}

	public Long getCreatTime() {
		return creatTime;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public void setPayNo(String payNo) {
		this.payNo = payNo;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setCreatTime(Long creatTime) {
		this.creatTime = creatTime;
	}
}
