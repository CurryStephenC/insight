package com.wjyoption.common.utils.sms;

public enum SmsTemplateEnum {

	TEMPLATE_1("91","yd_yx_1","[Perpetual Star Forex] Submission Successfully.Add your exclusive customer service WhatsApp: %s to get FREE %s bonus only Today!"),
	TEMPLATE_GAMES_VERIFY_1("91","yd_games_veri","Your verification code is %s"),//游戏用户填表单短信验证码
	TEMPLATE_VERIFY_2("886","tw_veri","您的驗證碼是: %s"),//游戏用户填表单短信验证码
	TEMPLATE_2("60","ml_yx_1","Perpetualstar Forex We will contact you soon. For more forex trading information, please visit our website:%s"),
	TEMPLATE_GAMES_INVITATIONREMINDER("91","yd_games_invitationreminder","Friends you invited has successfully participated. Check now：%s"),
	;
	private String phoneprefix;
	private String code;
	private String content;
	
	private SmsTemplateEnum(String phoneprefix,String code,String content){
		this.phoneprefix = phoneprefix;
		this.code = code;
		this.content = content;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public static String getContent(SmsTemplateEnum template,Object... args){
		return String.format(template.content, args);
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getPhoneprefix() {
		return phoneprefix;
	}
	public void setPhoneprefix(String phoneprefix) {
		this.phoneprefix = phoneprefix;
	}
}
