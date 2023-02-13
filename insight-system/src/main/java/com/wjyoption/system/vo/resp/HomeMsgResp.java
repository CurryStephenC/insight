package com.wjyoption.system.vo.resp;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("首页信息")
public class HomeMsgResp implements Serializable{

	
	private static final long serialVersionUID = 4337921457867584471L;

	@ApiModelProperty("总人数")
	private Long allUser;
	
	@ApiModelProperty("总购买金额")
	private Long allBuy;
	
	@ApiModelProperty("总收益")
	private Long allProfit;
	
	@ApiModelProperty("滚动信息")
	private List<String> scrollMsg;

	public Long getAllUser() {
		return allUser;
	}

	public Long getAllBuy() {
		return allBuy;
	}

	public Long getAllProfit() {
		return allProfit;
	}

	public List<String> getScrollMsg() {
		return scrollMsg;
	}

	public void setAllUser(Long allUser) {
		this.allUser = allUser;
	}

	public void setAllBuy(Long allBuy) {
		this.allBuy = allBuy;
	}

	public void setAllProfit(Long allProfit) {
		this.allProfit = allProfit;
	}

	public void setScrollMsg(List<String> scrollMsg) {
		this.scrollMsg = scrollMsg;
	}
	
}
