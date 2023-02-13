package com.wjyoption.system.vo.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel("言论Model")
public class SpeechResp implements Serializable{

	
	private static final long serialVersionUID = 6536366648541901488L;

	@ApiModelProperty("言论表ID")
	private Integer id;
	
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
    @ApiModelProperty("评论数")
    private Integer commentnum;

    /** 点赞数 */
    @ApiModelProperty("点赞数")
    private Integer likenum;

    /** 发表日期 */
    @ApiModelProperty("发表日期")
    private Integer daily;
    
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

	public Integer getDaily() {
		return daily;
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

	public void setDaily(Integer daily) {
		this.daily = daily;
	}

	public void setLike(Integer like) {
		this.like = like;
	}

	public Long getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Long createtime) {
		this.createtime = createtime;
	}
    
	
}
