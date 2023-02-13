package com.wjyoption.system.domain;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.wjyoption.common.annotation.Excel;

/**
 * 系统配置对象 wp_config
 * 
 * @author wjyoption
 * @date 2021-06-08
 */
public class WpConfig implements Serializable
//public class WpConfig extends BaseEntity
{
	private static final long serialVersionUID = -1310423371105878969L;

	/** 配置ID */
    private Integer id;

    /** 配置名称 */
    @Excel(name = "配置名称")
    private String name;

    /** 配置类型 */
    @Excel(name = "配置类型")
    private Integer type;

    /** 配置说明 */
    @Excel(name = "配置说明")
    private String title;

    /** 配置分组 */
    @Excel(name = "配置分组")
    private Integer group;

    /** 配置值 */
    @Excel(name = "配置值")
    private String extra;

    /** 状态 0禁用 1启用*/
    @Excel(name = "状态")
    private Integer status;

    /** 配置值 */
    @Excel(name = "配置值")
    private String value;

    /** 排序 */
    @Excel(name = "排序")
    private Integer sort;
    
    private String remark;
    
    private Long createTime;
    
    private Long updateTime;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
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
    public void setType(Integer type) 
    {
        this.type = type;
    }

    public Integer getType() 
    {
        return type;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setGroup(Integer group) 
    {
        this.group = group;
    }

    public Integer getGroup() 
    {
        return group;
    }
    public void setExtra(String extra) 
    {
        this.extra = extra;
    }

    public String getExtra() 
    {
        return extra;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setValue(String value) 
    {
        this.value = value;
    }

    public String getValue() 
    {
        return value;
    }
    public void setSort(Integer sort) 
    {
        this.sort = sort;
    }

    public Integer getSort() 
    {
        return sort;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("type", getType())
            .append("title", getTitle())
            .append("group", getGroup())
            .append("extra", getExtra())
            .append("remark", getRemark())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("status", getStatus())
            .append("value", getValue())
            .append("sort", getSort())
            .toString();
    }

	public Long getCreateTime() {
		return createTime;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
