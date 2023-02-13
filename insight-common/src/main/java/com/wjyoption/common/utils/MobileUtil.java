package com.wjyoption.common.utils;

import java.util.regex.Pattern;

public class MobileUtil {

	
	/**
	 * 判断手机号是否为纯数字
	 * @param phone
	 * @return
	 * true:纯数字
	 */
	public static boolean verifyPhoneNum(String phone){
		Pattern p = Pattern.compile("\\d+");
		return p.matcher(phone).matches();
	}
}
