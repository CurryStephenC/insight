package com.wjyoption.system.vo;

import java.io.Serializable;

import com.wjyoption.system.domain.SysWebsitBanner;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("网站Banner实体")
public class WebSiteBannerVo implements Serializable{

	
	private static final long serialVersionUID = 9012577125504083960L;
	
	public WebSiteBannerVo(){}
	public WebSiteBannerVo(SysWebsitBanner banner){
		this.name=banner.getName();
		this.name2=banner.getName2();
		this.code=banner.getCode();
		this.picurl=banner.getPicurl();
		this.website=banner.getWebsite();
		this.bannertype=banner.getBannertype();
		this.bannerorder = banner.getBannerorder();
		this.videourl = banner.getVideourl();
		this.videourl2 = banner.getVideourl2();
	}
	

	/** 名称 */
	@ApiModelProperty("名称")
    private String name;
	
	@ApiModelProperty("二级名称")
    private String name2;

    /** CODE码 */
    @ApiModelProperty(value="code码",example="分别为：home,plateform,products,account,training,aboutus")
    private String code;

    /** 图片地址 */
    @ApiModelProperty("图片地址")
    private String picurl;
    
    @ApiModelProperty("视频地址")
    private String videourl;
    @ApiModelProperty("视频地址2")
    private String videourl2;
    
//    @ApiModelProperty("移动端图片地址")
//    private String h5picurl;

    /** 网址链接 */
    @ApiModelProperty("网址链接")
    private String website;
    
    @ApiModelProperty("banner类型：1、PC，2、H5")
    private Integer bannertype;
    
    @ApiModelProperty("排序")
    private Integer bannerorder;
    
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPicurl() {
		return picurl;
	}

	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}
//	public String getH5picurl() {
//		return h5picurl;
//	}
//	public void setH5picurl(String h5picurl) {
//		this.h5picurl = h5picurl;
//	}
	public Integer getBannertype() {
		return bannertype;
	}
	public void setBannertype(Integer bannertype) {
		this.bannertype = bannertype;
	}
	public String getName2() {
		return name2;
	}
	public void setName2(String name2) {
		this.name2 = name2;
	}
	public Integer getBannerorder() {
		return bannerorder;
	}
	public void setBannerorder(Integer bannerorder) {
		this.bannerorder = bannerorder;
	}
	public String getVideourl() {
		return videourl;
	}
	public void setVideourl(String videourl) {
		this.videourl = videourl;
	}
	public String getVideourl2() {
		return videourl2;
	}
	public void setVideourl2(String videourl2) {
		this.videourl2 = videourl2;
	}
}
