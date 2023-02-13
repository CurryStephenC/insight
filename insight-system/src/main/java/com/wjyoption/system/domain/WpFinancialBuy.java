package com.wjyoption.system.domain;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.wjyoption.common.annotation.Excel;
import com.wjyoption.common.core.domain.BaseEntity;

/**
 * 老师购买记录对象 wp_teacher_buy
 * 
 * @author wjyoption
 * @date 2021-06-07
 */
public class WpFinancialBuy extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Integer id;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Integer uid;

    /** 理财详情ID */
    @Excel(name = "理财详情ID")
    private Integer detailid;

    /** 产品表ID */
    @Excel(name = "产品表ID")
    private Integer pid;

    /** 产品标题 */
    @Excel(name = "产品标题")
    private String ptitle;

    /** 方向 */
    @Excel(name = "方向")
    private Integer direct;

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
    public void setDetailid(Integer detailid) 
    {
        this.detailid = detailid;
    }

    public Integer getDetailid() 
    {
        return detailid;
    }
    public void setPid(Integer pid) 
    {
        this.pid = pid;
    }

    public Integer getPid() 
    {
        return pid;
    }
    public void setPtitle(String ptitle) 
    {
        this.ptitle = ptitle;
    }

    public String getPtitle() 
    {
        return ptitle;
    }
    public void setDirect(Integer direct) 
    {
        this.direct = direct;
    }

    public Integer getDirect() 
    {
        return direct;
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
            .append("detailid", getDetailid())
            .append("pid", getPid())
            .append("ptitle", getPtitle())
            .append("direct", getDirect())
            .append("profit", getProfit())
            .append("rate", getRate())
            .append("daily", getDaily())
            .toString();
    }
}
