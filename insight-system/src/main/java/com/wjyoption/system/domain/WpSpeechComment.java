package com.wjyoption.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.wjyoption.common.annotation.Excel;
import com.wjyoption.common.core.domain.BaseEntity;

/**
 * 言论评论对象 wp_speech_comment
 * 
 * @author wjyoption
 * @date 2021-06-17
 */
public class WpSpeechComment extends BaseEntity
{

	private static final long serialVersionUID = 7659654996507232599L;

	/** 主键ID */
    private Integer id;

    /** 言论表id */
    @Excel(name = "言论表id")
    private Integer sid;
    /**
     * 言论内容
     */
    private String scontent;

    /** 用户id */
    @Excel(name = "用户id")
    private Integer uid;
    private String utel;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;

    /** 审核意见 */
    @Excel(name = "审核意见")
    private String auditmsg;

    /** 用户手机 */
    @Excel(name = "用户手机")
    private String phone;

    /** 用户头像 */
    private String picurl;

    /** 内容 */
    @Excel(name = "内容")
    private String content;

    /** 评论数(通过) */
    @Excel(name = "评论数(通过)")
    private Integer commentnum;

    /** 点赞数 */
    @Excel(name = "点赞数")
    private Integer likenum;
    
    private Long createtime;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setSid(Integer sid) 
    {
        this.sid = sid;
    }

    public Integer getSid() 
    {
        return sid;
    }
    public void setUid(Integer uid) 
    {
        this.uid = uid;
    }

    public Integer getUid() 
    {
        return uid;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setAuditmsg(String auditmsg) 
    {
        this.auditmsg = auditmsg;
    }

    public String getAuditmsg() 
    {
        return auditmsg;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setPicurl(String picurl) 
    {
        this.picurl = picurl;
    }

    public String getPicurl() 
    {
        return picurl;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setCommentnum(Integer commentnum) 
    {
        this.commentnum = commentnum;
    }

    public Integer getCommentnum() 
    {
        return commentnum;
    }
    public void setLikenum(Integer likenum) 
    {
        this.likenum = likenum;
    }

    public Integer getLikenum() 
    {
        return likenum;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("sid", getSid())
            .append("uid", getUid())
            .append("status", getStatus())
            .append("auditmsg", getAuditmsg())
            .append("phone", getPhone())
            .append("picurl", getPicurl())
            .append("content", getContent())
            .append("commentnum", getCommentnum())
            .append("likenum", getLikenum())
            .append("createtime", getCreatetime())
            .toString();
    }

	public Long getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Long createtime) {
		this.createtime = createtime;
	}

	public String getUtel() {
		return utel;
	}

	public void setUtel(String utel) {
		this.utel = utel;
	}

	public String getScontent() {
		return scontent;
	}

	public void setScontent(String scontent) {
		this.scontent = scontent;
	}
}
