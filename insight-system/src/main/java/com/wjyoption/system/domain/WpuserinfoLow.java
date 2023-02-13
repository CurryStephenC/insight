package com.wjyoption.system.domain;

import java.math.BigDecimal;

import com.wjyoption.common.annotation.Excel;

public class WpuserinfoLow {

	@Excel(name = "用户")
	private String utel;
	
	@Excel(name = "用户余额")
	private BigDecimal usermoney;
	
	@Excel(name = "用户充值")
	private BigDecimal rechargemoney;
	
	@Excel(name = "用户理财")
	private BigDecimal financialmoney;

	public String getUtel() {
		return utel;
	}

	public BigDecimal getUsermoney() {
		return usermoney;
	}

	public BigDecimal getRechargemoney() {
		return rechargemoney;
	}

	public BigDecimal getFinancialmoney() {
		return financialmoney;
	}

	public void setUtel(String utel) {
		this.utel = utel;
	}

	public void setUsermoney(BigDecimal usermoney) {
		this.usermoney = usermoney;
	}

	public void setRechargemoney(BigDecimal rechargemoney) {
		this.rechargemoney = rechargemoney;
	}

	public void setFinancialmoney(BigDecimal financialmoney) {
		this.financialmoney = financialmoney;
	}
	
}
