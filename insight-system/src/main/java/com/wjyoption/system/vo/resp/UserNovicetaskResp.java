package com.wjyoption.system.vo.resp;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("用户新手任务")
public class UserNovicetaskResp implements Serializable{
	
	private static final long serialVersionUID = -4959923437051596774L;

	/** 任务 */
    @ApiModelProperty("任务ID")
    private Integer id;
    
    @ApiModelProperty("标题")
    private String title;

    /** 状态 */
    @ApiModelProperty("状态（1待完成，2已提交，3已完成）")
    private Integer status;

    /** 任务上传图片 */
    @ApiModelProperty("任务上传图片")
    private String picurl;

    /** 完成时间 */
    @ApiModelProperty("完成时间")
    private Long finishtime;
    
    /** 奖励金额 */
    @ApiModelProperty(name = "奖励金额")
    private Integer reward;

    /** 顺序 */
    @ApiModelProperty(name = "顺序")
    private Integer orderby;
    
    @ApiModelProperty(name = "用户任务记录ID",hidden=true)
    private Integer rateid;

	public Integer getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public Integer getStatus() {
		return status;
	}

	public String getPicurl() {
		return picurl;
	}

	public Long getFinishtime() {
		return finishtime;
	}

	public Integer getReward() {
		return reward;
	}

	public Integer getOrderby() {
		return orderby;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}

	public void setFinishtime(Long finishtime) {
		this.finishtime = finishtime;
	}

	public void setReward(Integer reward) {
		this.reward = reward;
	}

	public void setOrderby(Integer orderby) {
		this.orderby = orderby;
	}

	public Integer getRateid() {
		return rateid;
	}

	public void setRateid(Integer rateid) {
		this.rateid = rateid;
	}
	
}
