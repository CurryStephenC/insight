package com.wjyoption.system.vo.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 银行信息
 * 
 * @author hs
 * @date 2021-03-31
 */
@ApiModel("银行Model")
public class BanksResp implements Serializable
{

	
	private static final long serialVersionUID = 1083805281124397451L;
	/** 平台ID */
	@ApiModelProperty("银行ID")
	private Integer id;
    /** 平台名称 */
    @ApiModelProperty("银行名称")
    private String bankName;

    /** 平台类型 */
    @ApiModelProperty("银行ICON")
    private String bankIcon;
    

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBankName() {
		return bankName;
	}

	public String getBankIcon() {
		return bankIcon;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public void setBankIcon(String bankIcon) {
		this.bankIcon = bankIcon;
	}

}
