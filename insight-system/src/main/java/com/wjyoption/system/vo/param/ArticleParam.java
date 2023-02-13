package com.wjyoption.system.vo.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel("文章查询VO")
public class ArticleParam implements Serializable {

	private static final long serialVersionUID = -8507581467701057146L;

	@ApiModelProperty("文章ID")
	private Integer id;
	
	@ApiModelProperty("文章类型 找新总要")
    private Integer type;
	
	@ApiModelProperty("当前页码")
	private Integer pageNum;
	
	@ApiModelProperty("返回记录条数")
	private Integer pageSize;
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
