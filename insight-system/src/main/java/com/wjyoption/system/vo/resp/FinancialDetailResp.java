package com.wjyoption.system.vo.resp;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("收益详情")
public class FinancialDetailResp implements Serializable{
	
	private static final long serialVersionUID = -3550574150369340292L;

	/** 主键ID */
	@ApiModelProperty("记录ID")
    private Integer id;

    /** 收益 */
    @ApiModelProperty("收益")
    private Double profit;

    /** 收益日期 */
    @ApiModelProperty("收益日期")
    private Integer daily;

	public Integer getId() {
		return id;
	}

	public Double getProfit() {
		return profit;
	}

	public Integer getDaily() {
		return daily;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setProfit(Double profit) {
		this.profit = profit;
	}

	public void setDaily(Integer daily) {
		this.daily = daily;
	}
	
    
}
