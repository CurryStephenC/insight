package com.wjyoption.system.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel("文章详情")
public class ArticleDetailVo implements Serializable {

	private static final long serialVersionUID = -879853415779798725L;

	@ApiModelProperty("文章ID")
	private Integer id;
	/** 标题 */
	@ApiModelProperty("标题")
    private String title;

    /** 内容详情 */
    @ApiModelProperty("内容详情")
    private String content;

    /** 图片地址 */
    @ApiModelProperty("图片地址")
    private String picurl;

    /** 子标题 */
    @ApiModelProperty("子标题")
    private String subtitle;

    /** 缩略图 */
    @ApiModelProperty("缩略图")
    private String picthumb;

    /** 来自 */
    @ApiModelProperty("来自")
    private String from;
    
    @ApiModelProperty("SEO 关键字")
    private String keywords;
    @ApiModelProperty("SEO 描述")
    private String description;
    
    @ApiModelProperty("更新时间")
    private String update_time;
    
    /**阅读量**/
    @ApiModelProperty("阅读量")
    private Integer readnum;

	public String getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPicurl() {
		return picurl;
	}

	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getPicthumb() {
		return picthumb;
	}

	public void setPicthumb(String picthumb) {
		this.picthumb = picthumb;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
