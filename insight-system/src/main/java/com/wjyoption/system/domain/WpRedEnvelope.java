package com.wjyoption.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.wjyoption.common.annotation.Excel;
import com.wjyoption.common.core.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.List;

/**
 * 理财红包对象 wp_red_envelope
 * 
 * @author wjyoption
 * @date 2021-06-05
 */
public class WpRedEnvelope extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Integer id;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Integer uid;
    
    private String utel;
    private String putel;
    private String toputel;
    
    private Integer topid;
    

    /** 用户红包 */
    @Excel(name = "用户红包")
    private BigDecimal money;

    /** 可用红包 */
    @Excel(name = "可用红包")
    private BigDecimal lavemoney;

    /** 理财记录ID */
    private Integer refid;

    /** 红包类型:1、赠送 */
    @Excel(name = "红包类型:1、赠送")
    private Integer type;

    /** 过期时间 */
    @Excel(name = "过期时间")
    private Long endtime;
    
    private Long createtime;
    
    
    /**
     * 销售组合
     * 查询使用
     */
    private List<Integer> topids;
    /**
     * 正常用户：1、正常，2、非正常用户
     */
    private Integer normaltype;

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
    public void setMoney(BigDecimal money) 
    {
        this.money = money;
    }

    public BigDecimal getMoney() 
    {
        return money;
    }
    public void setLavemoney(BigDecimal lavemoney) 
    {
        this.lavemoney = lavemoney;
    }

    public BigDecimal getLavemoney() 
    {
        return lavemoney;
    }
    public void setRefid(Integer refid) 
    {
        this.refid = refid;
    }

    public Integer getRefid() 
    {
        return refid;
    }
    public void setType(Integer type) 
    {
        this.type = type;
    }

    public Integer getType() 
    {
        return type;
    }
    public void setEndtime(Long endtime) 
    {
        this.endtime = endtime;
    }

    public Long getEndtime() 
    {
        return endtime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("uid", getUid())
            .append("money", getMoney())
            .append("lavemoney", getLavemoney())
            .append("refid", getRefid())
            .append("type", getType())
            .append("endtime", getEndtime())
            .append("createtime", getCreatetime())
            .toString();
    }

	public Long getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Long createtime) {
		this.createtime = createtime;
	}

	public String getUtel() {
		return utel;
	}

	public String getPutel() {
		return putel;
	}

	public String getToputel() {
		return toputel;
	}

	public void setUtel(String utel) {
		this.utel = utel;
	}

	public void setPutel(String putel) {
		this.putel = putel;
	}

	public void setToputel(String toputel) {
		this.toputel = toputel;
	}

	public Integer getTopid() {
		return topid;
	}

	public void setTopid(Integer topid) {
		this.topid = topid;
	}

	public List<Integer> getTopids() {
		return topids;
	}

	public Integer getNormaltype() {
		return normaltype;
	}

	public void setTopids(List<Integer> topids) {
		this.topids = topids;
	}

	public void setNormaltype(Integer normaltype) {
		this.normaltype = normaltype;
	}
}
