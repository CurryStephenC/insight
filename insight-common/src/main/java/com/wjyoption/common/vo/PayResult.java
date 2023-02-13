package com.wjyoption.common.vo;

public class PayResult {

	/**
	 * 0：成功，其他失败
	 */
	private Integer code;
	
	/**
	 * 支付地址
	 */
	private String payUrl;
	
	/**
	 * 第三方ID
	 */
	private String thirdNo;
	
	/**
	 * 原始数据
	 */
	private String sourceData;

	public Integer getCode() {
		return code;
	}

	public String getPayUrl() {
		return payUrl;
	}

	public String getThirdNo() {
		return thirdNo;
	}

	public String getSourceData() {
		return sourceData;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public void setPayUrl(String payUrl) {
		this.payUrl = payUrl;
	}

	public void setThirdNo(String thirdNo) {
		this.thirdNo = thirdNo;
	}

	public void setSourceData(String sourceData) {
		this.sourceData = sourceData;
	}
	
}
