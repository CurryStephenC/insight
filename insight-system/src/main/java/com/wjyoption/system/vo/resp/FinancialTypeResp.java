package com.wjyoption.system.vo.resp;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("理财产品")
public class FinancialTypeResp implements Serializable{

	private static final long serialVersionUID = -1980636618591749932L;

	/** 主键ID */
	@ApiModelProperty("产品ID")
    private Integer id;
	
	@ApiModelProperty("产品类型")
	private Integer type;

    /** 是否正常 */
    @ApiModelProperty("是否正常 1：正常")
    private Integer isnormal;

    /** 名称 */
    @ApiModelProperty("名称")
    private String name;

    /** 利率 */
    @ApiModelProperty("利率")
    private Double rete;

    /** 最小购买金额 */
    @ApiModelProperty("最小购买金额")
    private Integer minbuymoney;
    
    @ApiModelProperty("最大购买金额")
    private Integer maxbuymoney;
    
    @ApiModelProperty("折扣")
    private BigDecimal rebate;

    /** 冻结天数 */
    @ApiModelProperty("冻结天数")
    private Integer freezedate;

    /** 是否火热1：火热 */
    @ApiModelProperty("是否火热1：火热")
    private Integer ishot;

    /** 红包最高占比 */
    @ApiModelProperty("红包最高占比")
    private Double redpercent;

    /** 能否手动赎回 */
    @ApiModelProperty("能否手动赎回 1：不可，2：可提现")
    private Integer canover;
    
    @ApiModelProperty("产品图")
    private String picurl;
    
    @ApiModelProperty("备注")
    private String remark;
    
    @ApiModelProperty("状态：1、可购买，2、不可购买")
    private Integer status;
    
    @ApiModelProperty("详情")
    private FinancialTypeDetailVo detail;

	public Integer getId() {
		return id;
	}

	public Integer getIsnormal() {
		return isnormal;
	}

	public String getName() {
		return name;
	}

	public Double getRete() {
		return rete;
	}

	public Integer getMinbuymoney() {
		return minbuymoney;
	}

	public Integer getFreezedate() {
		return freezedate;
	}

	public Integer getIshot() {
		return ishot;
	}

	public Double getRedpercent() {
		return redpercent;
	}

	public Integer getCanover() {
		return canover;
	}

	public FinancialTypeDetailVo getDetail() {
		return detail;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setIsnormal(Integer isnormal) {
		this.isnormal = isnormal;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRete(Double rete) {
		this.rete = rete;
	}

	public void setMinbuymoney(Integer minbuymoney) {
		this.minbuymoney = minbuymoney;
	}

	public void setFreezedate(Integer freezedate) {
		this.freezedate = freezedate;
	}

	public void setIshot(Integer ishot) {
		this.ishot = ishot;
	}

	public void setRedpercent(Double redpercent) {
		this.redpercent = redpercent;
	}

	public void setCanover(Integer canover) {
		this.canover = canover;
	}

	public void setDetail(FinancialTypeDetailVo detail) {
		this.detail = detail;
	}

	public String getPicurl() {
		return picurl;
	}

	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getMaxbuymoney() {
		return maxbuymoney;
	}

	public void setMaxbuymoney(Integer maxbuymoney) {
		this.maxbuymoney = maxbuymoney;
	}

	public BigDecimal getRebate() {
		return rebate;
	}

	public void setRebate(BigDecimal rebate) {
		this.rebate = rebate;
	}
}
