package com.wjyoption.common.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wjyoption.common.vo.UserLogin;


/**
 * 
 * <P>
 * Description：线程变量集合操作类
 * </P>
 * 
 * @author tcloud
 * @version 1.0
 */
public class ThreadLocals
{

    public static final ThreadLocal<UserLogin> USER_VO = new ThreadLocal<UserLogin>();

    public static final ThreadLocal<HttpServletResponse> RESPONSE = new ThreadLocal<HttpServletResponse>();

    public static final ThreadLocal<HttpServletRequest> REQUEST = new ThreadLocal<HttpServletRequest>();

    public static final ThreadLocal<Long> HANDLE_ID = new ThreadLocal<Long>();
    
    public static void setUser(UserLogin user)
    {
    	USER_VO.set(user);
    }

    public static UserLogin getUser()
    {
        return USER_VO.get();
    }

    /**
     * @return the response
     */
    public static HttpServletResponse getResponse()
    {
        return RESPONSE.get();
    }

    public static void setResponse(HttpServletResponse response)
    {
        RESPONSE.set(response);
    }

    /**
     * @return the response
     */
    public static HttpServletRequest getRequest()
    {
        return REQUEST.get();
    }

    public static void setRequest(HttpServletRequest request)
    {
        REQUEST.set(request);
    }
    
    public static Long getHandleId()
    {
        return HANDLE_ID.get();
    }

    public static void setHandleId(Long handleId)
    {
        HANDLE_ID.set(handleId);
    }

}
