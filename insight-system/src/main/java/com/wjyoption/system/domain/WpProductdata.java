package com.wjyoption.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.wjyoption.common.annotation.Excel;
import com.wjyoption.common.core.domain.BaseEntity;
import java.math.BigDecimal;

/**
 * 产品数据对象 wp_productdata
 * 
 * @author wjyoption
 * @date 2021-07-10
 */
public class WpProductdata extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Integer id;

    /** 产品id */
    @Excel(name = "产品id")
    private Integer pid;

    /** 产品code */
    @Excel(name = "产品code")
    private String name;

    /** 当前价格 */
    @Excel(name = "当前价格")
    private BigDecimal price;

    /** 开盘价 */
    @Excel(name = "开盘价")
    private BigDecimal open;

    /** 收盘价 */
    @Excel(name = "收盘价")
    private BigDecimal close;

    /** 最高 */
    @Excel(name = "最高")
    private String high;

    /** 最低 */
    @Excel(name = "最低")
    private String low;

    /** 振幅 */
    private String diff;

    /** 波幅 */
    private String diffrate;

    /** null */
    private String rands;

    /** null */
    private String point;

    /** null */
    private Integer isdelete;
    
    private Long updatetime;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setPid(Integer pid) 
    {
        this.pid = pid;
    }

    public Integer getPid() 
    {
        return pid;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setPrice(BigDecimal price) 
    {
        this.price = price;
    }

    public BigDecimal getPrice() 
    {
        return price;
    }
    public void setOpen(BigDecimal open) 
    {
        this.open = open;
    }

    public BigDecimal getOpen() 
    {
        return open;
    }
    public void setClose(BigDecimal close) 
    {
        this.close = close;
    }

    public BigDecimal getClose() 
    {
        return close;
    }
    public void setHigh(String high) 
    {
        this.high = high;
    }

    public String getHigh() 
    {
        return high;
    }
    public void setLow(String low) 
    {
        this.low = low;
    }

    public String getLow() 
    {
        return low;
    }
    public void setDiff(String diff) 
    {
        this.diff = diff;
    }

    public String getDiff() 
    {
        return diff;
    }
    public void setDiffrate(String diffrate) 
    {
        this.diffrate = diffrate;
    }

    public String getDiffrate() 
    {
        return diffrate;
    }
    public void setRands(String rands) 
    {
        this.rands = rands;
    }

    public String getRands() 
    {
        return rands;
    }
    public void setPoint(String point) 
    {
        this.point = point;
    }

    public String getPoint() 
    {
        return point;
    }
    public void setIsdelete(Integer isdelete) 
    {
        this.isdelete = isdelete;
    }

    public Integer getIsdelete() 
    {
        return isdelete;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("pid", getPid())
            .append("name", getName())
            .append("price", getPrice())
            .append("open", getOpen())
            .append("close", getClose())
            .append("high", getHigh())
            .append("low", getLow())
            .append("diff", getDiff())
            .append("diffrate", getDiffrate())
            .append("updatetime", getUpdatetime())
            .append("rands", getRands())
            .append("point", getPoint())
            .append("isdelete", getIsdelete())
            .toString();
    }

	public Long getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Long updatetime) {
		this.updatetime = updatetime;
	}
}
