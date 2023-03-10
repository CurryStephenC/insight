package com.wjyoption.common.utils;

import java.util.Random;

public class RandUtil {

	public static String getRand(int len){
		if(len < 1){
			return "";
		}
		StringBuilder rand = new StringBuilder("");
		while(rand.length() != len){
			rand.append(new Random().nextInt(10));
		}
		return rand.toString();
	}

	/**
	 * 获取指定位数的随机字符串(包含小写字母、大写字母、数字,0<length)
	 *
	 * @param length
	 * @return
	 */
	public static String getRandomString(int length) {
		//随机字符串的随机字符库
		String KeyString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		StringBuffer sb = new StringBuffer();
		int len = KeyString.length();
		for (int i = 0; i < length; i++) {
			sb.append(KeyString.charAt((int) Math.round(Math.random() * (len - 1))));
		}
		return sb.toString();
	}
	
}
