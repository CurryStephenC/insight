package com.wjyoption.system.vo.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("理财类型详情")
public class FinancialTypeDetailVo {

	
	/** 理财类型ID */
    @ApiModelProperty("理财类型ID")
    private Integer typeid;

    /** 结算方式 */
    @ApiModelProperty("结算方式")
    private String dividend;

    /** 担保机构 */
    @ApiModelProperty("担保机构")
    private String agency;

    /** 风险提示 */
    @ApiModelProperty("风险提示")
    private String riskmsg;

    /** 赎回方式 */
    @ApiModelProperty("赎回方式")
    private String repayment;

    /** 结算时间 */
    @ApiModelProperty("结算时间")
    private String settlementtime;

    /** 托管银行 */
    @ApiModelProperty("托管银行")
    private String custodianbank;

    /** 安全承诺 */
    @ApiModelProperty("安全承诺")
    private String security;

    /** 推荐奖励 */
    @ApiModelProperty("推荐奖励")
    private String referralreward;
    @ApiModelProperty("监管机构")
    private String jianguan;
    
    @ApiModelProperty("备注")
    private String remark;
    @ApiModelProperty("pdf文件")
    private String pdfurl;
    
    

	public Integer getTypeid() {
		return typeid;
	}

	public String getDividend() {
		return dividend;
	}

	public String getAgency() {
		return agency;
	}

	public String getRiskmsg() {
		return riskmsg;
	}

	public String getRepayment() {
		return repayment;
	}

	public String getSettlementtime() {
		return settlementtime;
	}

	public String getCustodianbank() {
		return custodianbank;
	}

	public String getSecurity() {
		return security;
	}

	public String getReferralreward() {
		return referralreward;
	}

	public String getJianguan() {
		return jianguan;
	}

	public String getRemark() {
		return remark;
	}

	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}

	public void setDividend(String dividend) {
		this.dividend = dividend;
	}

	public void setAgency(String agency) {
		this.agency = agency;
	}

	public void setRiskmsg(String riskmsg) {
		this.riskmsg = riskmsg;
	}

	public void setRepayment(String repayment) {
		this.repayment = repayment;
	}

	public void setSettlementtime(String settlementtime) {
		this.settlementtime = settlementtime;
	}

	public void setCustodianbank(String custodianbank) {
		this.custodianbank = custodianbank;
	}

	public void setSecurity(String security) {
		this.security = security;
	}

	public void setReferralreward(String referralreward) {
		this.referralreward = referralreward;
	}

	public void setJianguan(String jianguan) {
		this.jianguan = jianguan;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPdfurl() {
		return pdfurl;
	}

	public void setPdfurl(String pdfurl) {
		this.pdfurl = pdfurl;
	}
}
