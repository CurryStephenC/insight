package com.wjyoption.system.vo.report;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

@ApiModel("产品当前行情")
public class ProductNowDataVo implements Serializable{
	

	private static final long serialVersionUID = -8121664691257722484L;

	/** 当前价格 */
    @ApiModelProperty("买入价")
    private BigDecimal bid_price;

    /** 开盘价 */
    @ApiModelProperty("卖出价")
    private BigDecimal ask_price;

    /**
     * 产品ID
     */
    @ApiModelProperty("产品code")
    private String symbol;
    
    @ApiModelProperty("时间")
    private Long time;

	public BigDecimal getBid_price() {
		return bid_price;
	}

	public BigDecimal getAsk_price() {
		return ask_price;
	}

	public String getSymbol() {
		return symbol;
	}

	public Long getTime() {
		return time;
	}

	public void setBid_price(BigDecimal bid_price) {
		this.bid_price = bid_price;
	}

	public void setAsk_price(BigDecimal ask_price) {
		this.ask_price = ask_price;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public void setTime(Long time) {
		this.time = time;
	}

}
