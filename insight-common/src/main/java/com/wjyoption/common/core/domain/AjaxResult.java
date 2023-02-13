package com.wjyoption.common.core.domain;

import java.util.HashMap;
import com.wjyoption.common.utils.StringUtils;

/**
 * 操作消息提醒
 * 
 * @author hs
 */
public class AjaxResult extends HashMap<String, Object>
{
    private static final long serialVersionUID = 1L;

    /** 状态码 */
    public static final String CODE_TAG = "code";

    /** 返回内容 */
    public static final String MSG_TAG = "msg";

    /** 数据对象 */
    public static final String DATA_TAG = "data";

    /**
     * 状态类型
     */
    public enum Type
    {
        /** 成功 */
        SUCCESS(0,null),
        /** 签名错误 **/
        SIGNERROR(103,"签名错误"),
        /** 警告 */
        WARN(301,"警告"),
        /** 错误 */
        ERROR(500,"错误"),
        USER_ALREADY_EXIST(2,"用户已存在"),
    	
    	VERIFY_ERROR(3,"验证码错误"),
    	
    	/**
    	 * 验证码过期
    	 */
    	VERIFY_OUTTIME(4,"验证码过期"),
    	
    	/**
    	 * 手机号码不能为空
    	 */
    	PHONE_IS_NULL(5,"手机号码不能为空"),
    	
    	NO_POWER(6,"没有权限"),
    	
    	NO_LOGIN(7,"没有登录"),
    	
        ;
        private final int value;
        private final String msg;

        Type(int value,String msg)
        {
            this.value = value;
            this.msg = msg;
        }

        public int value()
        {
            return this.value;
        }
        public String msg()
        {
        	return this.msg;
        }
    }

    /**
     * 初始化一个新创建的 AjaxResult 对象，使其表示一个空消息。
     */
    public AjaxResult()
    {
    }

    /**
     * 初始化一个新创建的 AjaxResult 对象
     * 
     * @param type 状态类型
     * @param msg 返回内容
     */
    public AjaxResult(Type type, String msg)
    {
        super.put(CODE_TAG, type.value);
        super.put(MSG_TAG, msg);
    }

    /**
     * 初始化一个新创建的 AjaxResult 对象
     * 
     * @param type 状态类型
     * @param msg 返回内容
     * @param data 数据对象
     */
    public AjaxResult(Type type, String msg, Object data)
    {
        super.put(CODE_TAG, type.value);
        super.put(MSG_TAG, msg);
        if (StringUtils.isNotNull(data))
        {
            super.put(DATA_TAG, data);
        }
    }

    /**
     * 返回成功消息
     * 
     * @return 成功消息
     */
    public static AjaxResult success()
    {
        return AjaxResult.success("操作成功");
    }

    /**
     * 返回成功数据
     * 
     * @return 成功消息
     */
    public static AjaxResult success(Object data)
    {
        return AjaxResult.success("操作成功", data);
    }

    /**
     * 返回成功消息
     * 
     * @param msg 返回内容
     * @return 成功消息
     */
    public static AjaxResult success(String msg)
    {
        return AjaxResult.success(msg, null);
    }

    /**
     * 返回成功消息
     * 
     * @param msg 返回内容
     * @param data 数据对象
     * @return 成功消息
     */
    public static AjaxResult success(String msg, Object data)
    {
        return new AjaxResult(Type.SUCCESS, msg, data);
    }

    /**
     * 返回警告消息
     * 
     * @param msg 返回内容
     * @return 警告消息
     */
    public static AjaxResult warn(String msg)
    {
        return AjaxResult.warn(msg, null);
    }

    /**
     * 返回警告消息
     * 
     * @param msg 返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static AjaxResult warn(String msg, Object data)
    {
        return new AjaxResult(Type.WARN, msg, data);
    }

    /**
     * 返回错误消息
     * 
     * @return
     */
    public static AjaxResult error()
    {
        return AjaxResult.error("操作失败");
    }

    /**
     * 返回错误消息
     * 
     * @param msg 返回内容
     * @return 警告消息
     */
    public static AjaxResult error(String msg)
    {
        return AjaxResult.error(msg, null);
    }

    /**
     * 返回错误消息
     * 
     * @param msg 返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static AjaxResult error(String msg, Object data)
    {
        return new AjaxResult(Type.ERROR, msg, data);
    }
    
    /**
     * 返回对应的结果集
     * 
     * @param type 
     * @return 
     */
    public static AjaxResult setResult(Type type)
    {
    	return new AjaxResult(type, type.msg);
    }
}
