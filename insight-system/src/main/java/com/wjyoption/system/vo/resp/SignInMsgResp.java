package com.wjyoption.system.vo.resp;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("签到信息")
public class SignInMsgResp implements Serializable{

	
	private static final long serialVersionUID = -5186335018192561639L;

	@ApiModelProperty("签名总次数")
	private Integer signincount;
	
	@ApiModelProperty("奖金")
	private Integer reward;
	
	@ApiModelProperty("今日签到状态 1、已签到")
	private Integer onboardStatus;

	public Integer getSignincount() {
		return signincount;
	}

	public Integer getReward() {
		return reward;
	}

	public Integer getOnboardStatus() {
		return onboardStatus;
	}

	public void setSignincount(Integer signincount) {
		this.signincount = signincount;
	}

	public void setReward(Integer reward) {
		this.reward = reward;
	}

	public void setOnboardStatus(Integer onboardStatus) {
		this.onboardStatus = onboardStatus;
	}
	
	
	
}
