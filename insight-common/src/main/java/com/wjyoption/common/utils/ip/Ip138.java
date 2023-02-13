package com.wjyoption.common.utils.ip;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.wjyoption.common.utils.http.HttpUtils;

public class Ip138 {

	private static Logger logger = LoggerFactory.getLogger(Ip138.class);
	private static String url = "https://api.ip138.com/query/";
	private static String token = "c0075e930f6f40ec8659058ca5642a11";
	
	
	public static Ip138Bean getIpBelong(String ip){
		String res = HttpUtils.sendGet(url, "ip="+ip+"&datatype=jsonp&token="+token);
		Ip138Bean bean = null;
		try {
			bean = JSON.parseObject(res, Ip138Bean.class);
		} catch (Exception e) {
			logger.error("Ip138 获取归属地错误" + e.getMessage(),e);
		}
		return bean;
	}
//	public static void main(String[] args) {
//		String jsonStr = "{\"ret\":\"ok\",\"ip\":\"172.105.35.225\",\"data\":[\"印度\",\"马哈拉施特拉\",\"孟买\",\"\",\"\",\"0091\"]}";
//		Ip138Bean object = JSON.parseObject(jsonStr,Ip138Bean.class);
//		System.out.println(object.getData().get(0));
//	}
}

