package com.wjyoption.system.vo.resp;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



@ApiModel("版本更新记录")
public class UpdateVersionResp implements Serializable{
	
	private static final long serialVersionUID = -6938433115064033764L;

	@ApiModelProperty("是否需要更新")
	private Boolean isUpdate;
	
	@ApiModelProperty("ios更新地址")
	private String iOS;
	
	@ApiModelProperty("android更新地址")
	private String Android;
	
	@ApiModelProperty("版本号")
	private Integer version;
	
	@ApiModelProperty("更新方式  all：全更新")
	private String action;
	
	public Boolean getIsUpdate() {
		return isUpdate;
	}

	public String getiOS() {
		return iOS;
	}

	public String getAndroid() {
		return Android;
	}

	public Integer getVersion() {
		return version;
	}

	public void setIsUpdate(Boolean isUpdate) {
		this.isUpdate = isUpdate;
	}

	public void setiOS(String iOS) {
		this.iOS = iOS;
	}

	public void setAndroid(String android) {
		Android = android;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getAction() {
		return action;
	}


	public void setAction(String action) {
		this.action = action;
	}

	
	
}
