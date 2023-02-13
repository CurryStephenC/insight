package com.wjyoption.system.vo.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel("收益购买详情")
public class FinancialBuyResp implements Serializable{

	private static final long serialVersionUID = 5024779577909599817L;

	/** 主键ID */
	@ApiModelProperty("记录ID")
    private Integer id;

    /** 理财详情ID */
    @ApiModelProperty("理财详情ID")
    private Integer detailid;

    /** 产品标题 */
    @ApiModelProperty("产品标题")
    private String ptitle;

    /** 方向 */
    @ApiModelProperty("方向1：up，2：down")
    private Integer direct;

    /** 收益 */
    @ApiModelProperty("收益")
    private Double profit;

    /** 收益日期 */
    @ApiModelProperty("收益日期")
    private Integer daily;

	public Integer getId() {
		return id;
	}

	public Integer getDetailid() {
		return detailid;
	}

	public String getPtitle() {
		return ptitle;
	}

	public Integer getDirect() {
		return direct;
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

	public void setDetailid(Integer detailid) {
		this.detailid = detailid;
	}

	public void setPtitle(String ptitle) {
		this.ptitle = ptitle;
	}

	public void setDirect(Integer direct) {
		this.direct = direct;
	}

	public void setProfit(Double profit) {
		this.profit = profit;
	}

	public void setDaily(Integer daily) {
		this.daily = daily;
	}
	
}
