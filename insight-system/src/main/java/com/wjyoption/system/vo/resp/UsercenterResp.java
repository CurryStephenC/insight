package com.wjyoption.system.vo.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

@ApiModel("用户中心")
public class UsercenterResp implements Serializable{

	private static final long serialVersionUID = -4111088953548310850L;

	@ApiModelProperty("客户数量")
	private Integer usercount;
	
	@ApiModelProperty("总收益")
	private String totalincome;
	
	@ApiModelProperty("昨日收益")
	private String yestodayincome;
	
	@ApiModelProperty("推广地址")
	private String oidUrl;
	
	@ApiModelProperty("红包总额")
	private Integer lavemoney;
	
	@ApiModelProperty("理财总额")
	private BigDecimal financialmoney;
	
	@ApiModelProperty("IB状态：1、IB用户，0、非IB用户,2、VIP-IB")
	private Integer ibstatus;

	public Integer getUsercount() {
		return usercount;
	}

	public String getTotalincome() {
		return totalincome;
	}

	public String getYestodayincome() {
		return yestodayincome;
	}

	public void setUsercount(Integer usercount) {
		this.usercount = usercount;
	}

	public void setTotalincome(String totalincome) {
		this.totalincome = totalincome;
	}

	public void setYestodayincome(String yestodayincome) {
		this.yestodayincome = yestodayincome;
	}

	public String getOidUrl() {
		return oidUrl;
	}

	public void setOidUrl(String oidUrl) {
		this.oidUrl = oidUrl;
	}

	public Integer getLavemoney() {
		return lavemoney;
	}

	public void setLavemoney(Integer lavemoney) {
		this.lavemoney = lavemoney;
	}

	public Integer getIbstatus() {
		return ibstatus;
	}

	public void setIbstatus(Integer ibstatus) {
		this.ibstatus = ibstatus;
	}

	public BigDecimal getFinancialmoney() {
		return financialmoney;
	}

	public void setFinancialmoney(BigDecimal financialmoney) {
		this.financialmoney = financialmoney;
	}

}
