package com.wjyoption.system.vo.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel("数字产品")
public class PaydigitalTypeResp implements Serializable{

	private static final long serialVersionUID = 3862920879284275991L;

	public PaydigitalTypeResp(String typeid,String value){
		String[] val = typeid.split("\\|\\|");
		this.type = val[0];
		this.typename=val[1];
		this.value = value;
	}
	@ApiModelProperty("type")
	private String type;
	
	@ApiModelProperty("名称")
	private String typename;
	
	@ApiModelProperty("收款账号")
	private String value;
	


	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public String getTypename() {
		return typename;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}
	
}
