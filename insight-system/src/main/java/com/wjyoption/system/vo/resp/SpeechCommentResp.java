package com.wjyoption.system.vo.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel("言论评论")
public class SpeechCommentResp implements Serializable{

	private static final long serialVersionUID = 2963114301583933332L;

	/** 主键ID */
	@ApiModelProperty("评论ID")
    private Integer id;
	
	@ApiModelProperty("言论对象内容")
	private String scontent;
	@ApiModelProperty("言论对象图片")
	private String spicurl;
	@ApiModelProperty("言论对象号码")
	private String sphone;
	@ApiModelProperty("言论对象发表时间")
	private String screatetime;

    /** 状态 */
    @ApiModelProperty("状态:0、待审核，1、通过，2、拒绝")
    private Integer status;

    /** 审核意见 */
    @ApiModelProperty("审核意见")
    private String auditmsg;

    /** 用户手机 */
    @ApiModelProperty("用户手机")
    private String phone;

    /** 用户头像 */
    @ApiModelProperty("用户头像")
    private String picurl;

    /** 内容 */
    @ApiModelProperty("内容")
    private String content;

    /** 评论数(通过) */
    @ApiModelProperty("评论数(通过)")
    private Integer commentnum;

    /** 点赞数 */
    @ApiModelProperty("点赞数")
    private Integer likenum;
    
    @ApiModelProperty("发表时间")
    private Long createtime;
    
    @ApiModelProperty("是否点赞  0：未点，1：已点")
    private Integer like = 0;

	public Integer getId() {
		return id;
	}

	public Integer getStatus() {
		return status;
	}

	public String getAuditmsg() {
		return auditmsg;
	}

	public String getPhone() {
		return phone;
	}

	public String getPicurl() {
		return picurl;
	}

	public String getContent() {
		return content;
	}

	public Integer getCommentnum() {
		return commentnum;
	}

	public Integer getLikenum() {
		return likenum;
	}

	public Long getCreatetime() {
		return createtime;
	}

	public Integer getLike() {
		return like;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setAuditmsg(String auditmsg) {
		this.auditmsg = auditmsg;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setCommentnum(Integer commentnum) {
		this.commentnum = commentnum;
	}

	public void setLikenum(Integer likenum) {
		this.likenum = likenum;
	}

	public void setCreatetime(Long createtime) {
		this.createtime = createtime;
	}

	public void setLike(Integer like) {
		this.like = like;
	}

	public String getScontent() {
		return scontent;
	}

	public String getSpicurl() {
		return spicurl;
	}

	public String getSphone() {
		return sphone;
	}

	public String getScreatetime() {
		return screatetime;
	}

	public void setScontent(String scontent) {
		this.scontent = scontent;
	}

	public void setSpicurl(String spicurl) {
		this.spicurl = spicurl;
	}

	public void setSphone(String sphone) {
		this.sphone = sphone;
	}

	public void setScreatetime(String screatetime) {
		this.screatetime = screatetime;
	}
}
