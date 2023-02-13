package com.wjyoption.system.vo.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ApiModel("短信model")
public class SmsParam {

	@NotNull(message="Phone Not Null")
	@ApiModelProperty(value="电话号码",required = true)
	private Long phone;
	
	@NotBlank(message = "Not Null")
	@ApiModelProperty(value = "模板Code",required = true)
	private String code;
	
	@ApiModelProperty(value = "模板需要替换的数据(json数组)")
	private String dataJson;
	
	@ApiModelProperty(value = "来源",required = true)
	private String source;
	
	@ApiModelProperty(value = "类型：1、注册，2、忘记密码，3、修改手机号")
	private Integer status;

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDataJson() {
		return dataJson;
	}

	public void setDataJson(String dataJson) {
		this.dataJson = dataJson;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	/**
	 * 类型：1、注册，2、忘记密码，3、修改手机号
	 * @return
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * 类型：1、注册，2、忘记密码，3、修改手机号
	 * @param status
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
}
