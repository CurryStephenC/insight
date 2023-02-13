package com.wjyoption.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.wjyoption.common.annotation.Excel;
import com.wjyoption.common.core.domain.BaseEntity;

/**
 * 文章详情对象 sys_article_detail
 * 
 * @author hs
 * @date 2020-02-06
 */
public class SysArticleDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Integer id;

    /** 文章类型 */
    private Integer type;
    @Excel(name = "文章类型")
    private String typename;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 内容详情 */
    @Excel(name = "内容详情")
    private String content;

    /** 图片地址 */
    @Excel(name = "图片地址")
    private String picurl;

    /** 状态 0：正常, 1:禁止 */
    @Excel(name = "状态 0：正常, 1:禁止")
    private Integer status;

    /** 子标题 */
    @Excel(name = "子标题")
    private String subtitle;

    /** 缩略图 */
    @Excel(name = "缩略图")
    private String picthumb;

    /** 是否词汇 */
    @Excel(name = "是否词汇")
    private Integer wordtype;

    /** 来自 */
    @Excel(name = "来自")
    private String from;
    
    /** SEO 关键字 */
    @Excel(name = "SEO 关键字")
    private String keywords;
    
    /** SEO 描述 */
    @Excel(name = "SEO 描述")
    private String description;
    
    private Integer readnum;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
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
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setPicurl(String picurl) 
    {
        this.picurl = picurl;
    }

    public String getPicurl() 
    {
        return picurl;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setSubtitle(String subtitle) 
    {
        this.subtitle = subtitle;
    }

    public String getSubtitle() 
    {
        return subtitle;
    }
    public void setPicthumb(String picthumb) 
    {
        this.picthumb = picthumb;
    }

    public String getPicthumb() 
    {
        return picthumb;
    }
    public void setWordtype(Integer wordtype) 
    {
        this.wordtype = wordtype;
    }

    public Integer getWordtype() 
    {
        return wordtype;
    }
    public void setFrom(String from) 
    {
        this.from = from;
    }

    public String getFrom() 
    {
        return from;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("type", getType())
            .append("title", getTitle())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("content", getContent())
            .append("picurl", getPicurl())
            .append("status", getStatus())
            .append("subtitle", getSubtitle())
            .append("picthumb", getPicthumb())
            .append("wordtype", getWordtype())
            .append("from", getFrom())
            .toString();
    }

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getReadnum() {
		return readnum;
	}

	public void setReadnum(Integer readnum) {
		this.readnum = readnum;
	}
}
