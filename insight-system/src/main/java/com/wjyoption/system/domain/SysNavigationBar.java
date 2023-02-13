package com.wjyoption.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.wjyoption.common.annotation.Excel;
import com.wjyoption.common.core.domain.BaseEntity;

/**
 * 导航栏对象 sys_navigation_bar
 * 
 * @author hs
 * @date 2020-07-01
 */
public class SysNavigationBar extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long id;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 跳转地址 */
    @Excel(name = "跳转地址")
    private String tourl;

    /** 层级 */
    @Excel(name = "层级")
    private Integer barlevel;

    /** 顺序 */
    @Excel(name = "顺序")
    private Integer barorder;

    /** 类型 */
    @Excel(name = "类型")
    private Integer bartype;
    
    @Excel(name = "状态")
    private Integer barstatus;

    /** 页面打开方式 */
    @Excel(name = "页面打开方式")
    private String target;

    /** code */
    @Excel(name = "code")
    private String barcode;

    /** 上级ID */
    private Long parentid;
    @Excel(name = "上级")
    private String parentname;
    
    private String iconurl;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setTourl(String tourl) 
    {
        this.tourl = tourl;
    }

    public String getTourl() 
    {
        return tourl;
    }
    public void setBarlevel(Integer barlevel) 
    {
        this.barlevel = barlevel;
    }

    public Integer getBarlevel() 
    {
        return barlevel;
    }
    public void setBarorder(Integer barorder) 
    {
        this.barorder = barorder;
    }

    public Integer getBarorder() 
    {
        return barorder;
    }
    public void setBartype(Integer bartype) 
    {
        this.bartype = bartype;
    }

    public Integer getBartype() 
    {
        return bartype;
    }
    public void setTarget(String target) 
    {
        this.target = target;
    }

    public String getTarget() 
    {
        return target;
    }
    public void setBarcode(String barcode) 
    {
        this.barcode = barcode;
    }

    public String getBarcode() 
    {
        return barcode;
    }
    public void setParentid(Long parentid) 
    {
        this.parentid = parentid;
    }

    public Long getParentid() 
    {
        return parentid;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("tourl", getTourl())
            .append("barlevel", getBarlevel())
            .append("barorder", getBarorder())
            .append("bartype", getBartype())
            .append("target", getTarget())
            .append("barcode", getBarcode())
            .append("parentid", getParentid())
            .append("updateTime", getUpdateTime())
            .append("createTime", getCreateTime())
            .toString();
    }

	public String getParentname() {
		return parentname;
	}

	public void setParentname(String parentname) {
		this.parentname = parentname;
	}

	public Integer getBarstatus() {
		return barstatus;
	}

	public void setBarstatus(Integer barstatus) {
		this.barstatus = barstatus;
	}

	public String getIconurl() {
		return iconurl;
	}

	public void setIconurl(String iconurl) {
		this.iconurl = iconurl;
	}
}
