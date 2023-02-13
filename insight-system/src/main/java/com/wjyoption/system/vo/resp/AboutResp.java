package com.wjyoption.system.vo.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;
import java.util.Map;

@ApiModel("关于我们Model")
public class AboutResp {

	@ApiModelProperty("图片数组")
	private List<String> picList;
	
	@ApiModelProperty("保险图片数组")
	private List<String> baoxianList;
	
	@ApiModelProperty("地址")
	private String address;
	@ApiModelProperty("新地址字段")
	private Map<String,String> newaddress;
	@ApiModelProperty("Email")
	private String email;
	@ApiModelProperty("FB")
	private String facbook;
	@ApiModelProperty("推特")
	private String twitter;
	@ApiModelProperty("YouTube")
	private String youtube;
	
	@ApiModelProperty("联系电话")
	private String phone;
	
	
	public List<String> getPicList() {
		return picList;
	}
	public List<String> getBaoxianList() {
		return baoxianList;
	}
	public String getAddress() {
		return address;
	}
	public String getEmail() {
		return email;
	}
	public String getFacbook() {
		return facbook;
	}
	public String getTwitter() {
		return twitter;
	}
	public String getYoutube() {
		return youtube;
	}
	public void setPicList(List<String> picList) {
		this.picList = picList;
	}
	public void setBaoxianList(List<String> baoxianList) {
		this.baoxianList = baoxianList;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setFacbook(String facbook) {
		this.facbook = facbook;
	}
	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}
	public void setYoutube(String youtube) {
		this.youtube = youtube;
	}
	public Map<String, String> getNewaddress() {
		return newaddress;
	}
	public void setNewaddress(Map<String, String> newaddress) {
		this.newaddress = newaddress;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
	
}
