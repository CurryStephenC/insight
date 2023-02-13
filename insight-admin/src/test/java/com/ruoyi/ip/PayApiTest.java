package com.ruoyi.ip;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.wjyoption.common.constant.Constants;
import com.wjyoption.common.utils.http.HttpUtils;
import com.wjyoption.common.utils.security.Md5Util;

public class PayApiTest {
	
	
String domain = "http://localhost:8081/";
	
	@Test
	public void pay(){
		String url = domain + "api/pay/pay";
		Map<String, String> params = new HashMap<String, String>();
		Map<String, String> headers = new HashMap<String, String>();
		
		try {
			params.put("action", "hspay");
//			params.put("type", "1");
			params.put("money", "5000");
			
			headers.put("sign", Md5Util.MD5(params,Constants.DEFAULT_TOKENkEY));
			headers.put("Authorization", "7ed4ae7ac1d5634ecfe99c9d0cdc566d");
			System.out.println(HttpUtils.post(url, params,headers));
//			7ed4ae7ac1d5634ecfe99c9d0cdc566d
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
