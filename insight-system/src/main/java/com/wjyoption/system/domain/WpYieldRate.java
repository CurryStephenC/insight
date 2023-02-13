package com.wjyoption.system.domain;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.wjyoption.common.annotation.Excel;
import com.wjyoption.common.core.domain.BaseEntity;

/**
 * 收益利率对象 wp_yield_rate
 * 
 * @author wjyoption
 * @date 2021-06-06
 */
public class WpYieldRate extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Integer id;

    /** 收益率 */
    @Excel(name = "收益率")
    private BigDecimal rate;

    /** 收益日期 */
    @Excel(name = "收益日期")
    private Integer daily;

    /** 理财产品类型 */
    private Integer typeid;
    @Excel(name = "理财产品类型")
    private String typename;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
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
    public void setTypeid(Integer typeid) 
    {
        this.typeid = typeid;
    }

    public Integer getTypeid() 
    {
        return typeid;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("rate", getRate())
            .append("daily", getDaily())
            .append("typeid", getTypeid())
            .toString();
    }

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}
}
