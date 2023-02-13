package com.wjyoption.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.wjyoption.common.annotation.Excel;
import com.wjyoption.common.core.domain.BaseEntity;

/**
 * 理财类型详情对象 wp_financial_type_detail
 * 
 * @author wjyoption
 * @date 2021-06-04
 */
public class WpFinancialTypeDetail extends BaseEntity
{

	private static final long serialVersionUID = 7870319157390435087L;

	/** null */
    private Integer id;

    /** 理财类型ID */
    @Excel(name = "理财类型ID")
    private Integer typeid;

    /** 结算方式 */
    @Excel(name = "结算方式")
    private String dividend;

    /** 担保机构 */
    @Excel(name = "担保机构")
    private String agency;

    /** 风险提示 */
    @Excel(name = "风险提示")
    private String riskmsg;

    /** 赎回方式 */
    @Excel(name = "赎回方式")
    private String repayment;

    /** 结算时间 */
    @Excel(name = "结算时间")
    private String settlementtime;

    /** 托管银行 */
    @Excel(name = "托管银行")
    private String custodianbank;

    /** 安全承诺 */
    @Excel(name = "安全承诺")
    private String security;

    /** 推荐奖励 */
    @Excel(name = "推荐奖励")
    private String referralreward;
    @Excel(name = "监管机构")
    private String jianguan;
    
    /**
     * pdf文件
     */
    private String pdfurl;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setTypeid(Integer typeid) 
    {
        this.typeid = typeid;
    }

    public Integer getTypeid() 
    {
        return typeid;
    }
    public void setDividend(String dividend) 
    {
        this.dividend = dividend;
    }

    public String getDividend() 
    {
        return dividend;
    }
    public void setAgency(String agency) 
    {
        this.agency = agency;
    }

    public String getAgency() 
    {
        return agency;
    }
    public void setRiskmsg(String riskmsg) 
    {
        this.riskmsg = riskmsg;
    }

    public String getRiskmsg() 
    {
        return riskmsg;
    }
    public void setRepayment(String repayment) 
    {
        this.repayment = repayment;
    }

    public String getRepayment() 
    {
        return repayment;
    }
    public void setSettlementtime(String settlementtime) 
    {
        this.settlementtime = settlementtime;
    }

    public String getSettlementtime() 
    {
        return settlementtime;
    }
    public void setCustodianbank(String custodianbank) 
    {
        this.custodianbank = custodianbank;
    }

    public String getCustodianbank() 
    {
        return custodianbank;
    }
    public void setSecurity(String security) 
    {
        this.security = security;
    }

    public String getSecurity() 
    {
        return security;
    }
    public void setReferralreward(String referralreward) 
    {
        this.referralreward = referralreward;
    }

    public String getReferralreward() 
    {
        return referralreward;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("typeid", getTypeid())
            .append("dividend", getDividend())
            .append("agency", getAgency())
            .append("riskmsg", getRiskmsg())
            .append("repayment", getRepayment())
            .append("settlementtime", getSettlementtime())
            .append("custodianbank", getCustodianbank())
            .append("security", getSecurity())
            .append("referralreward", getReferralreward())
            .append("remark", getRemark())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }

	public String getJianguan() {
		return jianguan;
	}

	public void setJianguan(String jianguan) {
		this.jianguan = jianguan;
	}

	public String getPdfurl() {
		return pdfurl;
	}

	public void setPdfurl(String pdfurl) {
		this.pdfurl = pdfurl;
	}
}
