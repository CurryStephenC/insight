package com.wjyoption.common.utils.sms;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.wjyoption.common.utils.http.HttpUtils;
import com.wjyoption.common.utils.sms.vo.SmsResp;

public class SmsUtil {

	private static Logger logger = LoggerFactory.getLogger(SmsUtil.class);
	static String SMS_URL = "https://intapi.253.com/send/json";
	static String SMS_BATCH_URL = "https://intapi.253.com/send";
	/**营销*/
	static String[] SMS_MARK = {"IM4366453","xQ2E7kSHAT779d"};
//	static String SMS_PASSWORD = "xQ2E7kSHAT779d";
	/**验证码**/
	static String[] SMS_VERICODE = {"I2307550","MKQ5mGan4x6411"};
//	static String SMS_PASSWORD_VERICODE = "MKQ5mGan4x6411";
	
	/**验证码 互亿无线**/
	static String SMS_URL_IHUYI = "https://106.ihuyi.com/webservice/sms.php?method=Submit";
	static String[] SMS_VERICODE_IHUYI = {"cf_haoniu","3285a44fb62de0083ff7a70831d664f3"};
	/**验证码 互亿无线**/
	
	public static void main(String[] args) {
//		List<String> mobile = new ArrayList<String>();
//		mobile.add("123");
//		mobile.add("456");
//		String res = mobile.stream().collect(Collectors.joining(","));
//		String content = SmsTemplateEnum.TEMPLATE_1.getContent();
//		content = String.format(content, "https://wa.me/85264703438","$100");
//		_235Send(content, "911234567890");
//		System.out.println(String.format("'%%':输出%%。%%"));
//		System.out.println(res);
		
//		SmsResp res = sendMsg_Ali("{\"name\":\"张三\",\"money\":\"500.00\"}", TemplateCodeEnum.TOP_UP_SUCCESS, "18682082910");
//		System.out.println(JSON.toJSONString(res));
		mtSend("917355 is your verification code for this operation", "917700011417");
	}
	
	/**
	 * 发送短信
	 * @param content
	 * @param mobile
	 * @param mark 是否为营销短信
	 * @return
	 */
	public static SmsResp sendMsg(String content,String mobile,boolean mark){
		return mark ? _235Send(content, mobile, SMS_MARK) : _ihuyiSend(content, mobile, SMS_VERICODE_IHUYI);
//		return _235Send(content, mobile,mark ? SMS_MARK : SMS_VERICODE);
	}
	
	public static SmsResp sendMsg(String contentJson,String mobile,TemplateCodeEnum code){
		if(code.getSource().equals(SmsSourceEnum.ALI)){
			return sendMsg_Ali(contentJson,code,mobile);
		}
		return null;
	}
	
	/**
	 * 批量发送短信
	 * @param content
	 * @param mobile
	 * @param mark 是否为营销短信
	 * @return
	 */
	public static SmsResp sendMsgBatch(String content,List<String> mobile,boolean mark){
		return _235SendBatch(content, mobile,mark ? SMS_MARK : SMS_VERICODE);
	}
	
	private static SmsResp _ihuyiSend(String content,String mobile,String[] accout){
		SmsResp resp = new SmsResp();
		Map<String, String> params = new HashMap<>();
		try {
			params.put("account", accout[0]);
			params.put("password", accout[1]);
			params.put("mobile", mobile);
			params.put("content", content);
			params.put("time", (System.currentTimeMillis()/1000)+"");
			params.put("format", "json");
//			params.put("", "");
			String res = HttpUtils.post(SMS_URL_IHUYI, params);
			System.out.println(res);
			JSONObject jsonObj = JSON.parseObject(res);
			resp.setCode("2".equalsIgnoreCase(jsonObj.getString("code")) ? "0" : jsonObj.getString("code"));
            resp.setResultData(res);
            resp.setSmschannel("互亿无线");
            resp.setError(jsonObj.getString("msg"));
            resp.setMsgid(jsonObj.containsKey("smsid") ? jsonObj.getString("smsid") : "");
		} catch (Exception e) {
			logger.error("mobile|content|"+mobile + "|" + content,e);
			return null;
		}
		return resp;
	}
	private static SmsResp _235Send(String content,String mobile,String[] accout){
		Map<String, String> params = new HashMap<>();
		params.put("account", accout[0]);
		params.put("password", accout[1]);
		params.put("msg", content);
		params.put("mobile", mobile);
		String param = JSON.toJSONString(params);
		try {
//			logger.info("_235Send params:" +param);
			String res = HttpUtils.post(SMS_URL, param,new HashMap<>());
			logger.info("_235Send res:" + res);
			if(!StringUtils.startsWith(res, "{")){
				return null;
			}
			SmsResp ret = JSON.parseObject(res, SmsResp.class);
			ret.setSmschannel("创蓝");
			ret.setResultData(res);
			return ret;
		} catch (Exception e) {
			logger.error("_235Send error..."+e.getMessage(),e);
		}
		return null;
	}
	
	public static SmsResp mtSend(String content,String mobile){
		Map<String, String> params = new HashMap<>();
		params.put("appkey", "WNFjnsxg");
		params.put("secretkey", "hxxwk58l");
		params.put("phone", mobile);
		try {
			params.put("content", URLEncoder.encode(content,"UTF-8"));
			String res = HttpUtils.post("http://api.quanqiusms.com/api/sms/mtsend", params);
//			String res = HttpUtils.post("http://api.quanqiusms.com/api/sms/mtsend", JSON.toJSONString(params),new HashMap<>());
			logger.info("mtSend param" + JSON.toJSONString(params));
			logger.info("mtSend res:" + res);
			if(!StringUtils.startsWith(res, "{")){
				return null;
			}
			JSONObject json = JSON.parseObject(res);
			SmsResp ret = new SmsResp();
			ret.setCode(json.getString("code"));
			ret.setSmschannel("MT");
			ret.setResultData(res);
			return ret;
		} catch (Exception e) {
			logger.error("_235Send error..."+e.getMessage(),e);
		}
		return null;
	}
	private static SmsResp _235SendBatch(String content,List<String> mobile,String[] accout){
		Map<String, String> params = new HashMap<>();
		params.put("account", accout[0]);
		params.put("password", accout[1]);
		params.put("msg", content);
		params.put("mobile", mobile.stream().collect(Collectors.joining(",")));
		String param = JSON.toJSONString(params);
		try {
//			logger.info("批量处理_235SendBatch params:" +param);
			String res = HttpUtils.post(SMS_URL, param,new HashMap<>());
			logger.info("批量处理_235SendBatch res:" + res);
			if(!StringUtils.startsWith(res, "{")){
				return null;
			}
			SmsResp ret = JSON.parseObject(res, SmsResp.class);
			ret.setSmschannel("创蓝");
			ret.setResultData(res);
			return ret;
		} catch (Exception e) {
			logger.error("批量处理_235SendBatch error..."+e.getMessage(),e);
		}
		return null;
	}
	
	
	
	/**
	 * 
	 * @param templateJson
	 * @param code
	 * @param phones 多个逗号区分
	 */
	private static SmsResp sendMsg_Ali(String templateJson,TemplateCodeEnum code,String phones){
		SmsResp resp = new SmsResp();
		DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4G5JocSxbDtFTzfDeAoM", "M42N200hjT5L530fj7tK8BIzzOPb6E");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phones);
        request.putQueryParameter("SignName", "江恒");
        request.putQueryParameter("TemplateCode", code.getCode());
        request.putQueryParameter("TemplateParam", templateJson);
        try {
            CommonResponse response = client.getCommonResponse(request);
            String data = response.getData();
            JSONObject jsonObj = JSON.parseObject(data);
            resp.setCode("OK".equalsIgnoreCase(jsonObj.getString("Code")) ? "0" : jsonObj.getString("Code"));
            resp.setResultData(data);
            resp.setSmschannel("阿里云");
            resp.setError(jsonObj.getString("Message"));
            resp.setMsgid(jsonObj.containsKey("BizId") ? jsonObj.getString("BizId") : "");
            System.out.println(response.getData());
        } catch (Exception e) {
        	return null;
        }
        return resp;
	}
	
	
}
