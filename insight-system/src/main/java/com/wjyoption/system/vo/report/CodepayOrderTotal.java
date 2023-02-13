package com.wjyoption.system.vo.report;
import java.math.BigDecimal;

public class CodepayOrderTotal {

	/**
	 * 到账总金额
	 */
	private BigDecimal finishTotalMoney;
	
	/**
	 * 今日充值金额
	 */
	private BigDecimal todayMoney;
	//今日充值人次
	private Integer todayNum;
	//昨日充值金额
	private BigDecimal yesMoney;
	//昨日充值人次
	private Integer yesNum;
	

	public BigDecimal getFinishTotalMoney() {
		return finishTotalMoney;
	}

	public void setFinishTotalMoney(BigDecimal finishTotalMoney) {
		this.finishTotalMoney = finishTotalMoney;
	}

	public BigDecimal getTodayMoney() {
		return todayMoney;
	}

	public Integer getTodayNum() {
		return todayNum;
	}

	public BigDecimal getYesMoney() {
		return yesMoney;
	}

	public Integer getYesNum() {
		return yesNum;
	}

	public void setTodayMoney(BigDecimal todayMoney) {
		this.todayMoney = todayMoney;
	}

	public void setTodayNum(Integer todayNum) {
		this.todayNum = todayNum;
	}

	public void setYesMoney(BigDecimal yesMoney) {
		this.yesMoney = yesMoney;
	}

	public void setYesNum(Integer yesNum) {
		this.yesNum = yesNum;
	}
	
}
