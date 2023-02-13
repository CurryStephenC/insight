package com.wjyoption.common.utils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wjyoption.common.config.ServerConfig;
import com.wjyoption.common.utils.http.HttpUtils;
import com.wjyoption.common.utils.security.Md5Util;
import com.wjyoption.common.vo.CashNotifyVo;
import com.wjyoption.common.vo.PayNotifyVo;
import com.wjyoption.common.vo.PayResult;
import com.wjyoption.common.vo.UserLogin;

public class PayUtil {

	private static Logger logger = LoggerFactory.getLogger(PayUtil.class);
	public static final String key = "b61a765e27385eb6";
	public static final String cashkey = "jsio3.z*lj324.sdfsdf";
	
	public static PayResult pay(Integer payType,Integer pmid,String orderNo,BigDecimal money,String platform,String webSite,ServerConfig serverConfig){
		UserLogin user = ThreadLocals.getUser();
		PayResult result = new PayResult();
//		PayType msg = PayType.getMsg(payMethod);
		if(payType == null || pmid == null){
			result.setCode(1);
			return result;
		}
		String url = "https://third.ubetterbuy.com/api/thirdpay/pay";
//		String url = "http://192.168.1.160:8080/api/thirdpay/pay";
		Map<String, String> params = new HashMap<>();
		params.put("paytype", payType.toString());
		params.put("platform", platform);
		params.put("pmid", pmid.toString());
		params.put("country", "IN");
		params.put("currency", "INR");
		params.put("order_id", orderNo);
		params.put("amount", money.toString());
		params.put("return_url", "https://"+webSite+"/");
		params.put("notification_url", serverConfig.getUrl() + "/api/pay/dlocal_notify");
		params.put("email", user.getEmail());
		params.put("phone", user.getUtel());
		params.put("description", "recharge");
		try {
			String res = HttpUtils.post(url, params);
			logger.info("pay777 result->" + res);
			JSONObject json = JSON.parseObject(res);
			if("0".equals(json.getString("retCode"))){
				result.setCode(0);
				result.setSourceData(res);
				if(json.getString("data").startsWith("{")){
					JSONObject data = JSON.parseObject(json.getString("data"));
					result.setPayUrl(data.getString("redirectUrl"));
					result.setThirdNo(data.getString("id"));
				}else{
					result.setPayUrl(json.getString("data"));
				}
			}else{
				result.setCode(1);
			}
		} catch (Exception e) {
			logger.error("pay777 error",e);
		}
		return result;
	}
	
	public static boolean compareSign(PayNotifyVo vo){
		return Md5Util.MD5(vo.getOrderNo() + vo.getPay_amount() + vo.getRtime() + key).equalsIgnoreCase(vo.getSign());
	}
	
	public static boolean compareSign(CashNotifyVo vo){
		String key = vo.getOrderid() + vo.getAmount() + cashkey;
		String md5 = Md5Util.MD5(vo.getOrderid() + vo.getAmount() + cashkey);
		System.out.println("key:::" + key);
		System.out.println("md5:::" + md5);
		
		return md5.equalsIgnoreCase(vo.getSign());
	}
}
