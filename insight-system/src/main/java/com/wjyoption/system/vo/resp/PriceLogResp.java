package com.wjyoption.system.vo.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

@ApiModel("金额流水表")
public class PriceLogResp implements Serializable{
    
	private static final long serialVersionUID = 8160032887359229159L;

	@ApiModelProperty("用户号码")
    private String utel;

    /** 1增加2减少 */
    @ApiModelProperty("1增加2减少")
    private Integer type;

    /** 变动金额 */
    @ApiModelProperty("变动金额")
    private String account;

    /** 标题 */
    @ApiModelProperty("标题")
    private String title;

    /** 说明 */
    @ApiModelProperty("说明")
    private String content;

    /** 时间 */
    @ApiModelProperty("时间")
    private Long time;

    /** 此刻余额 */
    @ApiModelProperty("此刻余额")
    private BigDecimal nowmoney;

	public String getUtel() {
		return utel;
	}

	public Integer getType() {
		return type;
	}

	public String getAccount() {
		return account;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public Long getTime() {
		return time;
	}

	public BigDecimal getNowmoney() {
		return nowmoney;
	}

	public void setUtel(String utel) {
		this.utel = utel;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public void setNowmoney(BigDecimal nowmoney) {
		this.nowmoney = nowmoney;
	}
	
}
