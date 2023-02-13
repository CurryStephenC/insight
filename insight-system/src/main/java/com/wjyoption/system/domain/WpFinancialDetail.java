package com.wjyoption.system.domain;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.wjyoption.common.annotation.Excel;
import com.wjyoption.common.core.domain.BaseEntity;

/**
 * 理财收益详情对象 wp_financial_detail
 * 
 * @author wjyoption
 * @date 2021-06-07
 */
public class WpFinancialDetail extends BaseEntity
{

	private static final long serialVersionUID = 4058213807690530635L;

	/** 主键ID */
    private Integer id;

    /** 用户 */
    @Excel(name = "用户")
    private Integer uid;
    /** 理财表ID */
    @Excel(name = "理财表ID")
    private Integer refid;

    /** 收益 */
    @Excel(name = "收益")
    private BigDecimal profit;

    /** 收益率 */
    @Excel(name = "收益率")
    private BigDecimal rate;

    /** 收益日期 */
    @Excel(name = "收益日期")
    private Integer daily;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setUid(Integer uid) 
    {
        this.uid = uid;
    }

    public Integer getUid() 
    {
        return uid;
    }
    public void setRefid(Integer refid) 
    {
        this.refid = refid;
    }

    public Integer getRefid() 
    {
        return refid;
    }
    public void setProfit(BigDecimal profit) 
    {
        this.profit = profit;
    }

    public BigDecimal getProfit() 
    {
        return profit;
    }
    public void setRate(BigDecimal rate) 
    {
        this.rate = rate;
    }

    public BigDecimal getRate() 
    {
        return rate;
    }
    public void setDaily(Integer daily) 
    {
        this.daily = daily;
    }

    public Integer getDaily() 
    {
        return daily;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("uid", getUid())
            .append("refid", getRefid())
            .append("profit", getProfit())
            .append("rate", getRate())
            .append("daily", getDaily())
            .toString();
    }
}
