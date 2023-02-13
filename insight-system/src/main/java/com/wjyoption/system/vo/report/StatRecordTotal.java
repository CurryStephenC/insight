package com.wjyoption.system.vo.report;

import java.math.BigDecimal;

/**
 * 统计记录总数
 * @author 
 *
 */
public class StatRecordTotal {

	 /** 当天注册人数 */
    private Integer registnum;

    /** 当天赠送人数 */
    private Integer giveusernum;

    /** 当天赠送总金额 */
    private BigDecimal giveprice;

    /** 新充值人数 */
    private Integer rechargenum;

    /** 充值金额 */
    private BigDecimal rechargeprice;

    /** 总充值人数 */
//    private Integer totalrechargenum;

    /** 总充值金额 */
//    private BigDecimal totalrechargeprice;

    /** 当天提现人数 */
    private Integer cashnum;

    /** 当天提现金额 */
    private BigDecimal cashprice;

	public Integer getRegistnum() {
		return registnum;
	}

	public Integer getGiveusernum() {
		return giveusernum;
	}

	public BigDecimal getGiveprice() {
		return giveprice;
	}

	public Integer getRechargenum() {
		return rechargenum;
	}

	public BigDecimal getRechargeprice() {
		return rechargeprice;
	}

	public Integer getCashnum() {
		return cashnum;
	}

	public BigDecimal getCashprice() {
		return cashprice;
	}

	public void setRegistnum(Integer registnum) {
		this.registnum = registnum;
	}

	public void setGiveusernum(Integer giveusernum) {
		this.giveusernum = giveusernum;
	}

	public void setGiveprice(BigDecimal giveprice) {
		this.giveprice = giveprice;
	}

	public void setRechargenum(Integer rechargenum) {
		this.rechargenum = rechargenum;
	}

	public void setRechargeprice(BigDecimal rechargeprice) {
		this.rechargeprice = rechargeprice;
	}

	public void setCashnum(Integer cashnum) {
		this.cashnum = cashnum;
	}

	public void setCashprice(BigDecimal cashprice) {
		this.cashprice = cashprice;
	}
	
	
	
	
}
