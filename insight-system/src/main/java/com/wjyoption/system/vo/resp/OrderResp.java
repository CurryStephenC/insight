package com.wjyoption.system.vo.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

@ApiModel("订单model")
public class OrderResp implements Serializable{

	private static final long serialVersionUID = 7745853371922265441L;

	/** 订单ID */
	@ApiModelProperty("订单ID")
    private Integer oid;
	
	@ApiModelProperty("产品ID")
	private Integer pid;

    /** 用户ID */
    @ApiModelProperty("用户ID")
    private Integer uid;

    /** 方向0涨 1跌， */
    @ApiModelProperty("方向0涨 1跌，")
    private Integer ostyle;

    /** 建仓时间 */
    @ApiModelProperty("建仓时间")
    private Long buytime;

    /** 平仓时间 */
    @ApiModelProperty("平仓时间")
    private Long selltime;

    /** 0交易，1平仓 */
    @ApiModelProperty("0交易，1平仓")
    private Integer ostaus;
    
    @ApiModelProperty("时间数(秒)")
    private String endprofit;

    /** 入仓价 */
    @ApiModelProperty("入仓价")
    private BigDecimal buyprice;

    /** 平仓价 */
    @ApiModelProperty("平仓价")
    private BigDecimal sellprice;

    /** 总费用 */
    @ApiModelProperty("总费用")
    private BigDecimal fee;

    /** 订单编号 */
    @ApiModelProperty("订单号")
    private String orderno;

    /** 商品名称 */
    @ApiModelProperty("商品名称")
    private String ptitle;

    /** 盈亏 */
    @ApiModelProperty("盈亏")
    private BigDecimal ploss;

    /** 是否盈利1盈利2亏损3无效 */
    @ApiModelProperty("是否盈利1盈利2亏损3无效")
    private Integer isWin;

    /** 手续费 */
    @ApiModelProperty("手续费")
    private BigDecimal sxFee;

	public Integer getOid() {
		return oid;
	}

	public Integer getUid() {
		return uid;
	}

	public Integer getOstyle() {
		return ostyle;
	}

	public Long getBuytime() {
		return buytime;
	}

	public Long getSelltime() {
		return selltime;
	}

	public Integer getOstaus() {
		return ostaus;
	}

	public BigDecimal getBuyprice() {
		return buyprice;
	}

	public BigDecimal getSellprice() {
		return sellprice;
	}

	public BigDecimal getFee() {
		return fee;
	}

	public String getOrderno() {
		return orderno;
	}

	public String getPtitle() {
		return ptitle;
	}

	public BigDecimal getPloss() {
		return ploss;
	}

	public Integer getIsWin() {
		return isWin;
	}

	public BigDecimal getSxFee() {
		return sxFee;
	}

	public void setOid(Integer oid) {
		this.oid = oid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public void setOstyle(Integer ostyle) {
		this.ostyle = ostyle;
	}

	public void setBuytime(Long buytime) {
		this.buytime = buytime;
	}

	public void setSelltime(Long selltime) {
		this.selltime = selltime;
	}

	public void setOstaus(Integer ostaus) {
		this.ostaus = ostaus;
	}

	public void setBuyprice(BigDecimal buyprice) {
		this.buyprice = buyprice;
	}

	public void setSellprice(BigDecimal sellprice) {
		this.sellprice = sellprice;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

	public void setPtitle(String ptitle) {
		this.ptitle = ptitle;
	}

	public void setPloss(BigDecimal ploss) {
		this.ploss = ploss;
	}

	public void setIsWin(Integer isWin) {
		this.isWin = isWin;
	}

	public void setSxFee(BigDecimal sxFee) {
		this.sxFee = sxFee;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getEndprofit() {
		return endprofit;
	}

	public void setEndprofit(String endprofit) {
		this.endprofit = endprofit;
	}
	
}
