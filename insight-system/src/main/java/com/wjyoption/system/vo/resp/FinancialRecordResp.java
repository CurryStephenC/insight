package com.wjyoption.system.vo.resp;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("理财记录")
public class FinancialRecordResp implements Serializable{

	private static final long serialVersionUID = 8288196395078446524L;

	/** 主键ID */
	@ApiModelProperty("记录ID")
    private Integer id;

    /** 理财产品类型 */
    @ApiModelProperty("理财产品")
    private Integer typeid;
    @ApiModelProperty("能否手动赎回 1：不可，2：可提现")
    private Integer canover;
    @ApiModelProperty("产品名称")
    private String typename;
    @ApiModelProperty("产品利率")
    private String typerete;

    /** 购买金额 */
    @ApiModelProperty("购买金额")
    private BigDecimal buymoney;

    /** 赠送金额 */
    @ApiModelProperty("赠送金额")
    private BigDecimal creditmoney;

    /** 利润 */
    @ApiModelProperty("利润")
    private Double profit;

    /** 开始计息时间 */
    @ApiModelProperty("开始计息时间")
    private Long begintime;

    /** 结束计息时间 */
    @ApiModelProperty("结束计息时间")
    private Long endtime;

    /** 可操作下一步时间 */
    @ApiModelProperty("可操作下一步时间")
    private Long returntime;

    /** 冻结天数 */
    @ApiModelProperty("冻结天数")
    private Integer freezedate;

    /** 状态：0、收益中，1、已赎回 */
    @ApiModelProperty("状态：0、收益中，1、已赎回")
    private Integer status;

    /** 下一个步骤：0、到期继续，1、到期赎回 */
    @ApiModelProperty("下一个步骤：0、到期继续，1、到期赎回")
    private Integer nextstep;
    
    @ApiModelProperty("用户手动赎回：1、否，2、赎回中，3、已赎回")
	private Integer overself;

    @ApiModelProperty("购买时间")
    private Long createtime;
    
    @ApiModelProperty("上次收益")
    private Double lastProfit;
    
    @ApiModelProperty(value = "用户手机",hidden = true)
    private String utel;
    
    @ApiModelProperty("折扣")
    private BigDecimal rebate;

	public Integer getId() {
		return id;
	}

	public Integer getTypeid() {
		return typeid;
	}

	public Integer getCanover() {
		return canover;
	}

	public BigDecimal getBuymoney() {
		return buymoney;
	}

	public BigDecimal getCreditmoney() {
		return creditmoney;
	}

	public Double getProfit() {
		return profit;
	}

	public Long getBegintime() {
		return begintime;
	}

	public Long getEndtime() {
		return endtime;
	}

	public Long getReturntime() {
		return returntime;
	}

	public Integer getFreezedate() {
		return freezedate;
	}

	public Integer getStatus() {
		return status;
	}

	public Integer getNextstep() {
		return nextstep;
	}

	public Integer getOverself() {
		return overself;
	}

	public Long getCreatetime() {
		return createtime;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}

	public void setCanover(Integer canover) {
		this.canover = canover;
	}

	public void setBuymoney(BigDecimal buymoney) {
		this.buymoney = buymoney;
	}

	public void setCreditmoney(BigDecimal creditmoney) {
		this.creditmoney = creditmoney;
	}

	public void setProfit(Double profit) {
		this.profit = profit;
	}

	public void setBegintime(Long begintime) {
		this.begintime = begintime;
	}

	public void setEndtime(Long endtime) {
		this.endtime = endtime;
	}

	public void setReturntime(Long returntime) {
		this.returntime = returntime;
	}

	public void setFreezedate(Integer freezedate) {
		this.freezedate = freezedate;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setNextstep(Integer nextstep) {
		this.nextstep = nextstep;
	}

	public void setOverself(Integer overself) {
		this.overself = overself;
	}

	public void setCreatetime(Long createtime) {
		this.createtime = createtime;
	}

	public Double getLastProfit() {
		return lastProfit;
	}

	public void setLastProfit(Double lastProfit) {
		this.lastProfit = lastProfit;
	}

	public String getTypename() {
		return typename;
	}


	public void setTypename(String typename) {
		this.typename = typename;
	}

	public String getTyperete() {
		return typerete;
	}

	public void setTyperete(String typerete) {
		this.typerete = typerete;
	}

	public String getUtel() {
		return utel;
	}

	public void setUtel(String utel) {
		this.utel = utel;
	}

	public BigDecimal getRebate() {
		return rebate;
	}

	public void setRebate(BigDecimal rebate) {
		this.rebate = rebate;
	}

    
	
}
