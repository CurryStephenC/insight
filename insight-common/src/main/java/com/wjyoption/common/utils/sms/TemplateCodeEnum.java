package com.wjyoption.common.utils.sms;

public enum TemplateCodeEnum {

	
	//充值成功提醒
	TOP_UP_SUCCESS(SmsSourceEnum.ALI,"SMS_202805238","充值成功提醒","客户%s充值%s成功，请尽快处理。"),
	;
	private SmsSourceEnum source;
	private String code;
	private String desc;
	private String template;
	private TemplateCodeEnum(SmsSourceEnum source,String code,String desc,String template){
		this.source = source;
		this.code=code;
		this.desc=desc;
		this.template = template;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public SmsSourceEnum getSource() {
		return source;
	}
	public void setSource(SmsSourceEnum source) {
		this.source = source;
	}
	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
	}
}
