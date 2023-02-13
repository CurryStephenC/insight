package com.wjyoption.system.vo.resp;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("公告通知Model")
public class NoticeResp implements Serializable{
	
	private static final long serialVersionUID = 3202880899089140081L;

	
	/** 公告标题 */
	@ApiModelProperty("公告ID")
	private Integer noticeId;
	
	/** 公告标题 */
	@ApiModelProperty("标题")
    private String noticeTitle;

    /** 公告类型（1通知 2公告） */
	@ApiModelProperty(value="公告类型（1通知 2公告）",required=true)
    private String noticeType;

    /** 公告内容 */
	@ApiModelProperty("公告内容")
    private String noticeContent;

    /** 公告状态（0正常 1关闭） */
	@ApiModelProperty(value="状态",hidden=true)
    private String status;
    
    /**
     * 开始时间
     */
	@ApiModelProperty(value="开始时间",hidden=true)
    private String begintime;
    
    /**
     * 结束时间
     */
	@ApiModelProperty(value="结束时间",hidden=true)
    private String endtime;
    
    /**
     * 图片地址
     */
	@ApiModelProperty("图片")
    private String picurl;
	/**
	 * 跳转地址
	 */
	@ApiModelProperty("跳转地址")
	private String tourl;

	public String getNoticeTitle() {
		return noticeTitle;
	}

	public String getNoticeType() {
		return noticeType;
	}

	public String getNoticeContent() {
		return noticeContent;
	}

	public String getStatus() {
		return status;
	}

	public String getBegintime() {
		return begintime;
	}

	public String getEndtime() {
		return endtime;
	}

	public String getPicurl() {
		return picurl;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	public void setNoticeType(String noticeType) {
		this.noticeType = noticeType;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setBegintime(String begintime) {
		this.begintime = begintime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}

	public String getTourl() {
		return tourl;
	}

	public void setTourl(String tourl) {
		this.tourl = tourl;
	}

	public Integer getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(Integer noticeId) {
		this.noticeId = noticeId;
	}

}
