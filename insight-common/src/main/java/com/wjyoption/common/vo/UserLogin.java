package com.wjyoption.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 用户登陆信息
 * @author 
 *
 */
@ApiModel("用户信息")
public class UserLogin implements Serializable {
	

	private static final long serialVersionUID = -4029213236158741638L;

	/**
	 * 用户ID
	 */
	@ApiModelProperty("用户唯一标志")
	private Integer uid;
	
	@ApiModelProperty("用户手机号")
	private String utel;
	
	/**
	 * 注册时使用
	 */
	@ApiModelProperty("用户密码")
	private String password;
	
	@ApiModelProperty("用户名称")
	private String username;
	
	@ApiModelProperty("用户email")
	private String email;
	
	@ApiModelProperty("实名")
	private String realName;
	
	@ApiModelProperty("提现密码0：未设置，1：已设置")
	private int withdrawpsd;
	
	@ApiModelProperty(value="token",hidden=true)
	private String token;
	@ApiModelProperty("用户头像")
	private String userphoto;
	
	@ApiModelProperty("用户钱包")
	private BigDecimal wallet;
	
	@ApiModelProperty("体验理财：0、未购买过，1、购买过")
	private int experience;
	
	@ApiModelProperty("新手任务：0未完成，1完成")
	private int newtask;

	public Integer getUid() {
		return uid;
	}

	public String getUtel() {
		return utel;
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	public String getRealName() {
		return realName;
	}


	public String getToken() {
		return token;
	}

	public BigDecimal getWallet() {
		return wallet;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public void setUtel(String utel) {
		this.utel = utel;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}


	public void setToken(String token) {
		this.token = token;
	}

	public void setWallet(BigDecimal wallet) {
		this.wallet = wallet;
	}

	public int getWithdrawpsd() {
		return withdrawpsd;
	}

	public void setWithdrawpsd(int withdrawpsd) {
		this.withdrawpsd = withdrawpsd;
	}

	public String getUserphoto() {
		return userphoto;
	}

	public void setUserphoto(String userphoto) {
		this.userphoto = userphoto;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public int getNewtask() {
		return newtask;
	}

	public void setNewtask(int newtask) {
		this.newtask = newtask;
	}

	
}
