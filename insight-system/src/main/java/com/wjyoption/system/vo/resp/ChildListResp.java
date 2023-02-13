package com.wjyoption.system.vo.resp;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("客户Model")
public class ChildListResp implements Serializable{
	
	private static final long serialVersionUID = -7323094940332500682L;

	
	@ApiModelProperty(value = "上级ID",hidden = true)
	private Integer oid;
	
	@ApiModelProperty("客户手机号")
	private String utel;
	
	@ApiModelProperty("购买金额")
	private BigDecimal buymoney;
	
	@ApiModelProperty("收益总额")
	private BigDecimal creditmoney;
	
	@ApiModelProperty("注册时间")
	private Long utime;
	
	@ApiModelProperty("来自新手任务，0不是，1是")
	private Integer comenewtask;

	public String getUtel() {
		return utel;
	}

	public BigDecimal getBuymoney() {
		return buymoney;
	}

	public BigDecimal getCreditmoney() {
		return creditmoney;
	}

	public Long getUtime() {
		return utime;
	}

	public void setUtel(String utel) {
		this.utel = utel;
	}

	public void setBuymoney(BigDecimal buymoney) {
		this.buymoney = buymoney;
	}

	public void setCreditmoney(BigDecimal creditmoney) {
		this.creditmoney = creditmoney;
	}

	public void setUtime(Long utime) {
		this.utime = utime;
	}

	public Integer getOid() {
		return oid;
	}

	public void setOid(Integer oid) {
		this.oid = oid;
	}

	public Integer getComenewtask() {
		return comenewtask;
	}

	public void setComenewtask(Integer comenewtask) {
		this.comenewtask = comenewtask;
	}
	
}
