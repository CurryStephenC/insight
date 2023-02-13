package com.wjyoption.system.vo.resp;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("理财产品类型")
public class FinancialTypeTypeResp implements Serializable{

	private static final long serialVersionUID = 1451822200647553999L;

	public FinancialTypeTypeResp(String typeid,String value){
		this.typeid = typeid;
		this.value = value;
	}
	
	@ApiModelProperty("类型ID")
	private String typeid;
	
	@ApiModelProperty("名称")
	private String value;

	public String getTypeid() {
		return typeid;
	}

	public String getValue() {
		return value;
	}

	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
