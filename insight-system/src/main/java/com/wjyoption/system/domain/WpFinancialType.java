package com.wjyoption.system.domain;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.wjyoption.common.annotation.Excel;
import com.wjyoption.common.core.domain.BaseEntity;

/**
 * 理财类型对象 wp_financial_type
 * 
 * @author wjyoption
 * @date 2021-06-04
 */
public class WpFinancialType extends BaseEntity
{

	private static final long serialVersionUID = -7599532584506080131L;

	/** 主键ID */
    private Integer id;

    /** 是否正常 */
    @Excel(name = "是否正常")
    private Integer isnormal;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 利率 */
    @Excel(name = "利率")
    private Double rete;

    /** 最小购买金额 */
    @Excel(name = "最小购买金额")
    private Integer minbuymoney;
    
    /**
     * 最大购买金额
     */
    @Excel(name = "最大购买金额")
    private Integer maxbuymoney;
    
    @Excel(name = "折扣")
    private BigDecimal rebate;

    /** 冻结天数 */
    @Excel(name = "冻结天数")
    private Integer freezedate;

    /** 是否火热1：火热 */
    @Excel(name = "是否火热1：火热")
    private Integer ishot;

    /** 红包最高占比 */
    @Excel(name = "红包最高占比")
    private Double redpercent;

    /** 能否手动赎回 */
    @Excel(name = "能否手动赎回")
    private Integer canover;
    
    private Long createtime;
    
    /**
     * 产品图
     */
    private String picurl;
    
    /**
     * 产品类型
     */
    private Integer type;
    
    private String remark;
    
    /**
     * 状态：1、可购买，2、不可购买
     */
    private Integer status;
    
    /**
     * 排序
     */
    private Integer orderby;
    
    @Excel(name = "提前赎回违约金")
    private Integer feerate;
    

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setIsnormal(Integer isnormal) 
    {
        this.isnormal = isnormal;
    }

    public Integer getIsnormal() 
    {
        return isnormal;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setRete(Double rete) 
    {
        this.rete = rete;
    }

    public Double getRete() 
    {
        return rete;
    }
    public void setMinbuymoney(Integer minbuymoney) 
    {
        this.minbuymoney = minbuymoney;
    }

    public Integer getMinbuymoney() 
    {
        return minbuymoney;
    }
    public void setFreezedate(Integer freezedate) 
    {
        this.freezedate = freezedate;
    }

    public Integer getFreezedate() 
    {
        return freezedate;
    }
    public void setIshot(Integer ishot) 
    {
        this.ishot = ishot;
    }

    public Integer getIshot() 
    {
        return ishot;
    }
    public void setRedpercent(Double redpercent) 
    {
        this.redpercent = redpercent;
    }

    public Double getRedpercent() 
    {
        return redpercent;
    }
    public void setCanover(Integer canover) 
    {
        this.canover = canover;
    }

    public Integer getCanover() 
    {
        return canover;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("isnormal", getIsnormal())
            .append("name", getName())
            .append("rete", getRete())
            .append("minbuymoney", getMinbuymoney())
            .append("freezedate", getFreezedate())
            .append("ishot", getIshot())
            .append("redpercent", getRedpercent())
            .append("canover", getCanover())
            .append("createtime", getCreatetime())
            .toString();
    }

	public Long getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Long createtime) {
		this.createtime = createtime;
	}

	public String getPicurl() {
		return picurl;
	}

	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getOrderby() {
		return orderby;
	}

	public void setOrderby(Integer orderby) {
		this.orderby = orderby;
	}

	public Integer getMaxbuymoney() {
		return maxbuymoney;
	}

	public void setMaxbuymoney(Integer maxbuymoney) {
		this.maxbuymoney = maxbuymoney;
	}

	public Integer getFeerate() {
		return feerate;
	}

	public void setFeerate(Integer feerate) {
		this.feerate = feerate;
	}

	public BigDecimal getRebate() {
		return rebate;
	}

	public void setRebate(BigDecimal rebate) {
		this.rebate = rebate;
	}
}
