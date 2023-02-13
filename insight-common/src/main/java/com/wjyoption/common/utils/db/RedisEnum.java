package com.wjyoption.common.utils.db;

public enum RedisEnum {

	/**
	 * 短信验证码
	 * 缓存key为 PREFIX+phone
	 */
	PREFIX_SMS_VERI(""),
	/**
	 * 短信模板前缀
	 * 缓存key为 PREFIX+templatecode
	 */
	PREFIX_SMS_TEMPLATE("redis_smstemplate:"),
	/**
	 * 短信发送数量
	 * 缓存key为 PREFIX+phone
	 */
	PREFIX_SMS_SEND_TOTAL("redis_sms_sendtotal:"),
	
	/**
	 * 省份redis键
	 */
	REGION_ALL_PROVINCE("redis_region_province"),
	
	/**
	 * 城市redis键
	 */
	REGION_CITY("redis_region_city"),
	
	/**
	 * 区redis键
	 */
	REGION_AREA("redis_region_area"),
	
	/**
	 * 用户登陆key
	 */
	USER_LOGIN("redis_user_loginkey"),
	
	/**
	 * 登录用户id对应token
	 */
	USERID_LOGIN("redis_user_loginkey"),
	
	/**
	 * 系统配置缓存key
	 */
	WP_CONFG_KEY("redis_wpconfig_key"),
	
	/**
	 * 用户中心缓存key
	 */
	WP_USERCENTER_MSG_KEY("redis_usercenter_key"),
	
	/**
	 * 首页key
	 */
	HOME_MSG_KEY("redis_homemsg_key"),
	
	/**
	 * 每日签到奖励KEY
	 */
	CHECKIN_REWARD_DAILY("redis_reward_daily_key"),
	
	/**
	 * 累计签到奖项KEY
	 */
	CHECKIN_REWARD_TOTAL("redis_checkinreward_key"),
	
	/**
	 * 签到信息前缀
	 */
	SIGNIN_MSG_RECORD("redis_signin_key"),
	
	/**
	 * 关于我们信息缓存key
	 */
	ABOUT_REDIS_KEY("redis_aboutus_key"),
	
	/**
	 * 行情数据
	 */
	KLINE_TOKEN("redis_kline_token"),
	
	/**
	 * 产品数据列表缓存
	 */
	PRODUCT_ALL_DATA("redis_product_apidata"),
	
	/**
	 * 产品k线图前缀
	 */
	PRODUCT_KLINE("redis_product_kline"),
	/**
	 * 产品当前价格
	 */
	PRODUCT_NOWDATA("redis_product_nowdata"),
	/**
	 * 产品下单限制 10s一单
	 */
	PRODUCT_PLACEORDER_LIMIT("redis_product_placeorder_limit"),
	
	/**
	 * 下单数量每日限制
	 */
	PRODUCT_DAYLI_LIMIT("redis_product_placeorder_dailylimit"),
	;
	private String keyPrefix;
	private  RedisEnum(String keyPrefix) {
		this.keyPrefix = keyPrefix;
	}
	public String getKeyPrefix() {
		return keyPrefix;
	}
	public void setKeyPrefix(String keyPrefix) {
		this.keyPrefix = keyPrefix;
	}
	
}
