package com.wjyoption.system.vo.param;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("理财请求参数")
public class FinancialTypeParam implements Serializable{

	
	private static final long serialVersionUID = 4197999092565140560L;

	/** 是否正常 */
    @ApiModelProperty(value = "是否正常 1：正常")
    private Integer isnormal;

    /** 名称 */
    @ApiModelProperty("名称")
    private String name;

    /** 是否火热1：火热 */
    @ApiModelProperty("是否火热1：火热")
    private Integer ishot;
    
    @ApiModelProperty("类型：1、Recommend，2、Rate,3、Funds")
    private Integer type;
    
    /** 当前记录起始索引 */
    @ApiModelProperty("当前页")
    private Integer pageNum;
    
    /** 每页显示记录数 */
    @ApiModelProperty("每页记录")
    private Integer pageSize;
    @ApiModelProperty("状态：1、正常，2、下架")
    private Integer status;
    
    /** 排序列 */
    @ApiModelProperty("排序字段：如 ishot")
    private String orderByColumn;
    
    /** 排序的方向 "desc" 或者 "asc". */
    @ApiModelProperty("排序方式->顺序：asc,倒序：desc")
    private String isAsc;

	public Integer getIsnormal() {
		return isnormal;
	}

	public String getName() {
		return name;
	}

	public Integer getIshot() {
		return ishot;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public String getOrderByColumn() {
		return orderByColumn;
	}

	public String getIsAsc() {
		return isAsc;
	}

	public void setIsnormal(Integer isnormal) {
		this.isnormal = isnormal;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setIshot(Integer ishot) {
		this.ishot = ishot;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public void setOrderByColumn(String orderByColumn) {
		this.orderByColumn = orderByColumn;
	}

	public void setIsAsc(String isAsc) {
		this.isAsc = isAsc;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
