package com.wjyoption.system.vo.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel("推广CODE码信息")
public class NavigationBarResp implements Serializable{

	
	private static final long serialVersionUID = 4861650746413172151L;

	/** 主键ID */
	@ApiModelProperty("id")
    private Long id;

    /** 名称 */
    @ApiModelProperty("名称")
    private String name;

    /** 跳转地址 */
    @ApiModelProperty("跳转地址")
    private String tourl;

    /** 层级 */
    @ApiModelProperty("层级")
    private Integer barlevel;

    /** 顺序 */
    @ApiModelProperty("顺序")
    private Integer barorder;

    /** 类型 */
    @ApiModelProperty("类型：1、PC，2、H5")
    private Integer bartype;
    
    /** 页面打开方式 */
    @ApiModelProperty("页面打开方式")
    private String target;

    /** code */
    @ApiModelProperty("code")
    private String barcode;

    /** 上级ID */
    @ApiModelProperty("上级ID")
    private Long parentid;
    
    @ApiModelProperty("ICON")
    private String iconurl;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTourl() {
		return tourl;
	}

	public void setTourl(String tourl) {
		this.tourl = tourl;
	}

	public Integer getBarlevel() {
		return barlevel;
	}

	public void setBarlevel(Integer barlevel) {
		this.barlevel = barlevel;
	}

	public Integer getBarorder() {
		return barorder;
	}

	public void setBarorder(Integer barorder) {
		this.barorder = barorder;
	}

	public Integer getBartype() {
		return bartype;
	}

	public void setBartype(Integer bartype) {
		this.bartype = bartype;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public Long getParentid() {
		return parentid;
	}

	public void setParentid(Long parentid) {
		this.parentid = parentid;
	}

	public String getIconurl() {
		return iconurl;
	}

	public void setIconurl(String iconurl) {
		this.iconurl = iconurl;
	}
	
}
