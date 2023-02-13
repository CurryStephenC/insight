package com.wjyoption.system.vo.report;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("产品K线数据")
public class ProductKlineVo implements Serializable{

	
	private static final long serialVersionUID = -2466592963628576628L;

	@ApiModelProperty("时间")
	private Long time;
	
	@ApiModelProperty("开盘价")
	private BigDecimal open;
	
	@ApiModelProperty("收盘价")
	private BigDecimal close;
	
	@ApiModelProperty("最低")
	private String low;
	
	@ApiModelProperty("最高")
	private String high;
	
	public Long getTime() {
		return time;
	}

	public BigDecimal getOpen() {
		return open;
	}

	public BigDecimal getClose() {
		return close;
	}

	public String getLow() {
		return low;
	}

	public String getHigh() {
		return high;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public void setOpen(BigDecimal open) {
		this.open = open;
	}

	public void setClose(BigDecimal close) {
		this.close = close;
	}

	public void setLow(String low) {
		this.low = low;
	}

	public void setHigh(String high) {
		this.high = high;
	}

}
