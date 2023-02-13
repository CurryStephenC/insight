package com.wjyoption.common.constant;

/**
 * 通用常量信息
 * 
 * @author hs
 */
public class Constants
{
    /**
     * UTF-8 字符集
     */
    public static final String UTF8 = "UTF-8";

    /**
     * 通用成功标识
     */
    public static final String SUCCESS = "0";

    /**
     * 通用失败标识
     */
    public static final String FAIL = "1";

    /**
     * 登录成功
     */
    public static final String LOGIN_SUCCESS = "Success";

    /**
     * 注销
     */
    public static final String LOGOUT = "Logout";

    /**
     * 登录失败
     */
    public static final String LOGIN_FAIL = "Error";

    /**
     * 自动去除表前缀
     */
    public static final String AUTO_REOMVE_PRE = "true";

    /**
     * 当前记录起始索引
     */
    public static final String PAGE_NUM = "pageNum";

    /**
     * 每页显示记录数
     */
    public static final String PAGE_SIZE = "pageSize";

    /**
     * 排序列
     */
    public static final String ORDER_BY_COLUMN = "orderByColumn";

    /**
     * 排序的方向 "desc" 或者 "asc".
     */
    public static final String IS_ASC = "isAsc";

    /**
     * 资源映射路径 前缀
     */
    public static final String RESOURCE_PREFIX = "/download";
    
    
    public static final String signValidateSwitch = "on";
    
    /**
	 * 签名token
	 */
	public static final String DEFAULT_TOKENkEY = "";
	
	/**
	 * 设备默认密码
	 */
	public static final String DEVICE_DEFAULT_PASSWORD = "111111";
	
	
	/**
	 * 总公司ID
	 */
	public static final Long ADMIN_DEPTID = 100L;
	
	/**
	 * 短信统计总时间
	 * 当前时间内只能发送{SEND_COUNT}条
	 */
	public static long SMS_HOURSEND = 60 * 60 * 1000;
	
	/**
	 * 短信限制条数
	 * {SMS_HOURSEND}时间内只能发送{SMS_SEND_COUNT}条
	 */
	public static int SMS_SEND_COUNT = 5;

}
