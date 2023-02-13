package com.wjyoption.system.vo.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel("回复Model")
public class SpeechReplyResp implements Serializable {

	
	
	private static final long serialVersionUID = -3592450269281575552L;

	/** 主键ID */
	@ApiModelProperty("回复表ID")
    private Integer id;
	
	@ApiModelProperty(value = "评论表ID",hidden = true)
	private Integer cid;
	@ApiModelProperty("评论对象内容")
	private String ccontent;
	@ApiModelProperty("评论对象图片")
	private String cpicurl;
	@ApiModelProperty("评论对象号码")
	private String cphone;
	@ApiModelProperty("评论对象发表时间")
	private String ccreatetime;

    /** 状态 */
    @ApiModelProperty("状态:0、待审核，1、通过，2、拒绝")
    private Integer status;

    /** 审核意见 */
    @ApiModelProperty("审核意见")
    private String auditmsg;

    /** 用户手机 */
    @ApiModelProperty("用户手机")
    private String phone;

    /** 回复表ID */
    @ApiModelProperty("回复表ID")
    private Integer toreplyid;

    /** 回复用户ID */
    @ApiModelProperty("回复用户ID")
    private Integer touid;

    /** 回复用户手机 */
    @ApiModelProperty("回复用户手机")
    private String replyphone;
    @ApiModelProperty("回复的回复内容")
    private String replycontent;

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

	public Integer getToreplyid() {
		return toreplyid;
	}

	public Integer getTouid() {
		return touid;
	}

	public String getReplyphone() {
		return replyphone;
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

	public void setToreplyid(Integer toreplyid) {
		this.toreplyid = toreplyid;
	}

	public void setTouid(Integer touid) {
		this.touid = touid;
	}

	public void setReplyphone(String replyphone) {
		this.replyphone = replyphone;
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

	public Integer getCid() {
		return cid;
	}

	public String getCcontent() {
		return ccontent;
	}

	public String getCpicurl() {
		return cpicurl;
	}

	public String getCphone() {
		return cphone;
	}

	public String getCcreatetime() {
		return ccreatetime;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public void setCcontent(String ccontent) {
		this.ccontent = ccontent;
	}

	public void setCpicurl(String cpicurl) {
		this.cpicurl = cpicurl;
	}

	public void setCphone(String cphone) {
		this.cphone = cphone;
	}

	public void setCcreatetime(String ccreatetime) {
		this.ccreatetime = ccreatetime;
	}

	public String getReplycontent() {
		return replycontent;
	}

	public void setReplycontent(String replycontent) {
		this.replycontent = replycontent;
	}
}
