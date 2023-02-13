package com.wjyoption.common.enums;

public enum PayType {

	PAY777("pay777",2,7,401),
	YYPAY("yypay",3,6,501),
	UPAY("upay",4,8,601),
	HSPAY("hspay",5,100,1001),
	;
	private PayType(String pay,Integer payNum,Integer payType,Integer pmid){
		this.pay = pay;
		this.payNum = payNum;
		this.payType = payType;
		this.pmid = pmid;
	}
	
	public static PayType getMsg(String pay){
		if(pay == null) return null;
		for(PayType p:PayType.values()){
			if(pay.equalsIgnoreCase(p.pay)){
				return p;
			}
		}
		return null;
	}
	
	private String pay;
	private Integer payNum;
	private Integer payType;
	private Integer platform;
	private Integer pmid;
	public String getPay() {
		return pay;
	}

	public Integer getPayType() {
		return payType;
	}

	public Integer getPlatform() {
		return platform;
	}

	public Integer getPmid() {
		return pmid;
	}

	public void setPay(String pay) {
		this.pay = pay;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public void setPlatform(Integer platform) {
		this.platform = platform;
	}

	public void setPmid(Integer pmid) {
		this.pmid = pmid;
	}

	public Integer getPayNum() {
		return payNum;
	}

	public void setPayNum(Integer payNum) {
		this.payNum = payNum;
	}
	
}
