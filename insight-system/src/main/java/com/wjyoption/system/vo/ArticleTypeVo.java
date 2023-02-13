package com.wjyoption.system.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel("类型VO")
public class ArticleTypeVo implements Serializable {


	private static final long serialVersionUID = 4126953134965064473L;
	@ApiModelProperty("ID")
	private Integer id;
	/** 标题 */
	@ApiModelProperty("标题")
	private String title;
	@ApiModelProperty("排序")
	private Integer order;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}

}
