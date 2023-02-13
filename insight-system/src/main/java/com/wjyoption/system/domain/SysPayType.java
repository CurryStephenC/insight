package com.wjyoption.system.domain;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.wjyoption.common.annotation.Excel;
import com.wjyoption.common.core.domain.BaseEntity;

/**
 * 支付方式对象 sys_pay_type
 * 
 * @author wjyoption
 * @date 2021-06-26
 */
public class SysPayType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 支付code */
    @Excel(name = "支付code")
    private String code;

    /** 支付类别(type) */
    @Excel(name = "支付类别(type)")
    private Integer paynum;

    /** HS对应渠道 */
    @Excel(name = "HS对应渠道")
    private Integer paytype;

    /** PMID */
    @Excel(name = "PMID")
    private Integer pmid;

    /** 状态（0正常 1停用） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private Integer status;
    
    @Excel(name = "最小充值金额")
    private BigDecimal minprice;
    @Excel(name = "最大充值金额")
    private BigDecimal maxprice;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCode(String code) 
    {
        this.code = code;
    }

    public String getCode() 
    {
        return code;
    }
    public void setPaynum(Integer paynum) 
    {
        this.paynum = paynum;
    }

    public Integer getPaynum() 
    {
        return paynum;
    }
    public void setPaytype(Integer paytype) 
    {
        this.paytype = paytype;
    }

    public Integer getPaytype() 
    {
        return paytype;
    }
    public void setPmid(Integer pmid) 
    {
        this.pmid = pmid;
    }

    public Integer getPmid() 
    {
        return pmid;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("code", getCode())
            .append("paynum", getPaynum())
            .append("paytype", getPaytype())
            .append("pmid", getPmid())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }

	public BigDecimal getMinprice() {
		return minprice;
	}

	public BigDecimal getMaxprice() {
		return maxprice;
	}

	public void setMinprice(BigDecimal minprice) {
		this.minprice = minprice;
	}

	public void setMaxprice(BigDecimal maxprice) {
		this.maxprice = maxprice;
	}
}
