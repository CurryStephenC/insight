package com.wjyoption.common.vo;

import java.io.Serializable;

/**
 * 出金回调model
 * @author 
 *
 */
public class CashNotifyVo implements Serializable {

	
	private static final long serialVersionUID = 6366313699846057333L;

	/**
	 * 2:成功，其他失败
	 */
	private String orderid;
	
	private Integer state;
	
	private String sign;
	
	private String amount;
	
	private String trade_no;

	public String getOrderid() {
		return orderid;
	}

	/**
	 * 2:成功，其他失败
	 */
	public Integer getState() {
		return state;
	}

	public String getSign() {
		return sign;
	}

	public String getTrade_no() {
		return trade_no;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
	
}
