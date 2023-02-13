package com.wjyoption.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.wjyoption.common.annotation.Excel;
import com.wjyoption.common.core.domain.BaseEntity;

/**
 * 网站banner对象 sys_websit_banner
 * 
 * @author hs
 * @date 2020-02-07
 */
public class SysWebsitBanner extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Integer id;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** CODE码 */
    @Excel(name = "CODE码")
    private String code;

    /** 图片地址 */
    @Excel(name = "图片地址")
    private String picurl;
    
//    @Excel(name = "移动端图片地址")
//    private String h5picurl;

    /** 网址链接 */
    @Excel(name = "网址链接")
    private String website;
    
    /**视频地址*/
    private String videourl;
    /**视频地址2*/
    private String videourl2;
    
    /**banner类型：1、PC，2、H5*/
    @Excel(name="banner类型")
    private Integer bannertype;
    
    @Excel(name="二级名称")
    private String name2;
    
    /**
     * 状态0正常，1暂停
     */
    private Integer bannerstatus;
    
    /**
     * 排序
     */
    private Integer bannerorder;
    
    private String remark;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setCode(String code) 
    {
        this.code = code;
    }

    public String getCode() 
    {
        return code;
    }
    public void setPicurl(String picurl) 
    {
        this.picurl = picurl;
    }

    public String getPicurl() 
    {
        return picurl;
    }
    public void setWebsite(String website) 
    {
        this.website = website;
    }

    public String getWebsite() 
    {
        return website;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("code", getCode())
            .append("picurl", getPicurl())
            .append("website", getWebsite())
            .append("website", getBannertype())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }

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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getBannerstatus() {
		return bannerstatus;
	}

	public void setBannerstatus(Integer bannerstatus) {
		this.bannerstatus = bannerstatus;
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
