package com.wjyoption.system.vo.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel("累计签到奖励")
public class CheckinRewardUserResp implements Serializable{

	private static final long serialVersionUID = -4932475200948107749L;

	@ApiModelProperty(value="记录ID(领取时使用)")
	private Integer id;
	
	@ApiModelProperty(value="名称")
	private String name;

    /** 签到次数 */
    @ApiModelProperty(value = "签到次数")
    private Integer times;

	@ApiModelProperty(value="奖金")
	private Integer money;
	 
	@ApiModelProperty(value="状态：1、待领取，2、领取")
	private Integer status;
	
	/***查询条件**/
	@ApiModelProperty(value="用户ID",hidden=true)
	private Integer uid;

	public Integer getId() {
		return id;
	}

	public Integer getMoney() {
		return money;
	}

	public Integer getStatus() {
		return status;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setMoney(Integer money) {
		this.money = money;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public Integer getTimes() {
		return times;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTimes(Integer times) {
		this.times = times;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}
	
}
