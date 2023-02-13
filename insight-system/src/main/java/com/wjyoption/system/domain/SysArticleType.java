package com.wjyoption.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.wjyoption.common.annotation.Excel;
import com.wjyoption.common.core.domain.BaseEntity;

/**
 * 网站文章类型对象 sys_article_type
 * 
 * @author hs
 * @date 2020-02-06
 */
public class SysArticleType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long id;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 状态 0：正常 1：禁止 */
    @Excel(name = "状态 0：正常 1：禁止")
    private Integer status;

    /** 排序 */
    @Excel(name = "排序")
    private Integer order;

    /** 导航栏类型 */
    @Excel(name = "导航栏类型")
    private Integer navtype;

    /** 类型 */
    @Excel(name = "类型")
    private Integer type;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setOrder(Integer order) 
    {
        this.order = order;
    }

    public Integer getOrder() 
    {
        return order;
    }
    public void setNavtype(Integer navtype) 
    {
        this.navtype = navtype;
    }

    public Integer getNavtype() 
    {
        return navtype;
    }
    public void setType(Integer type) 
    {
        this.type = type;
    }

    public Integer getType() 
    {
        return type;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("title", getTitle())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("status", getStatus())
            .append("order", getOrder())
            .append("navtype", getNavtype())
            .append("type", getType())
            .toString();
    }
}
