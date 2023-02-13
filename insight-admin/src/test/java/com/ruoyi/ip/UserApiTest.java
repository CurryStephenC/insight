package com.ruoyi.ip;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.wjyoption.common.constant.Constants;
import com.wjyoption.common.utils.http.HttpUtils;
import com.wjyoption.common.utils.security.Md5Util;

public class UserApiTest {

	String domain = "http://localhost:8081/";
	
	@Test
	public void login(){
		String url = domain + "api/user/login";
		Map<String, String> params = new HashMap<String, String>();
		Map<String, String> headers = new HashMap<String, String>();
		
		try {
			params.put("phone", "10000014");
//			params.put("type", "1");
			params.put("pwd", "123456");
			
			headers.put("sign", Md5Util.MD5(params,Constants.DEFAULT_TOKENkEY));
			System.out.println(HttpUtils.post(url, params,headers));
//			7ed4ae7ac1d5634ecfe99c9d0cdc566d
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
