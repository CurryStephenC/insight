package com.wjyoption.common.utils.ip;

import java.util.List;

public class Ip138Bean{
	private String ret;
	private String ip;
	private List<String> data;
	
	public String getRet() {
		return ret;
	}
	public void setRet(String ret) {
		this.ret = ret;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	/**
	 *  [
	 *       "中国",     // 国家
	 *       "福建",     // 省会或直辖市
	 *       "福州",     // 地区或城市
	 *       "电信",     // 运营商
	 *       "361000",  // 邮政编码
	 *       "0592"     // 地区区号
	 *   ]
	 * @return
	 */
	public List<String> getData() {
		return data;
	}
	public void setData(List<String> data) {
		this.data = data;
	}
}