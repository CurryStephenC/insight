package com.wjyoption.common.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class PayNotifyVo implements Serializable{

	private static final long serialVersionUID = -2617821980651732911L;
	private String orderNo;
	private String state;
	private String rtime;
	private String sign;
	private BigDecimal pay_amount;
	private String trade_no;
	
	public String getOrderNo() {
		return orderNo;
	}
	public String getState() {
		return state;
	}
	public String getRtime() {
		return rtime;
	}
	public String getSign() {
		return sign;
	}
	public BigDecimal getPay_amount() {
		return pay_amount;
	}
	public String getTrade_no() {
		return trade_no;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public void setState(String state) {
		this.state = state;
	}
	public void setRtime(String rtime) {
		this.rtime = rtime;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public void setPay_amount(BigDecimal pay_amount) {
		this.pay_amount = pay_amount;
	}
	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}
	
	
	
}
