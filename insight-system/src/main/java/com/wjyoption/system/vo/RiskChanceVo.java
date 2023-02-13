package com.wjyoption.system.vo;

import java.math.BigDecimal;

public class RiskChanceVo {

	/**
	 * 最小金额
	 */
	private BigDecimal minPrice;
	
	/**
	 * 最大金额
	 */
	private BigDecimal maxPrice;
	
	/**
	 * 获胜几率
	 */
	private Integer winChance;

	public BigDecimal getMinPrice() {
		return minPrice;
	}

	public BigDecimal getMaxPrice() {
		return maxPrice;
	}

	public Integer getWinChance() {
		return winChance;
	}

	public void setMinPrice(BigDecimal minPrice) {
		this.minPrice = minPrice;
	}

	public void setMaxPrice(BigDecimal maxPrice) {
		this.maxPrice = maxPrice;
	}

	public void setWinChance(Integer winChance) {
		this.winChance = winChance;
	}
	
}
