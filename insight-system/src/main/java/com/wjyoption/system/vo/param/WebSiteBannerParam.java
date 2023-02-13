package com.wjyoption.system.vo.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel("网站Banner参数")
public class WebSiteBannerParam implements Serializable {
	
	
	private static final long serialVersionUID = 2484577332681180708L;

	@ApiModelProperty(value="code码 分别为：home,plateform,products,account,training,aboutus")
	private String code;
	
	@ApiModelProperty(value="banner类型 1、PC(默认)，2、H5")
	private Integer bannertype = 1;
	
	@ApiModelProperty("当前页码")
	private Integer pageNum;
	
	@ApiModelProperty("返回记录条数")
	private Integer pageSize;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public Integer getBannertype() {
		return bannertype;
	}

	public void setBannertype(Integer bannertype) {
		this.bannertype = bannertype;
	}
}
