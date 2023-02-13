package com.wjyoption.common.utils.sms.vo;

public class SmsResp {

	/**
	 * 短信通道
	 */
	private String smschannel;
	
	private String code;
	
	private String error;
	
	private String msgid;
	
	/**
	 * 返回原始数据
	 */
	private String resultData;

	/**
	 * 0代表发送成功，其他code代表出错
	 * @return
	 */
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMsgid() {
		return msgid;
	}

	public void setMsgid(String msgid) {
		this.msgid = msgid;
	}

	public String getSmschannel() {
		return smschannel;
	}

	public void setSmschannel(String smschannel) {
		this.smschannel = smschannel;
	}

	public String getResultData() {
		return resultData;
	}

	public void setResultData(String resultData) {
		this.resultData = resultData;
	}
	
}
