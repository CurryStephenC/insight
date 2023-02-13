/*
 * @fileName：SysLogInterceptor.java    2013-7-22 下午12:50:53
 *
 */
package com.wjyoption.framework.interceptor.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;

import com.wjyoption.common.annotation.Login;
import com.wjyoption.common.enums.ErrorConstants;
import com.wjyoption.common.utils.ThreadLocals;
import com.wjyoption.common.utils.db.RedisEnum;
import com.wjyoption.common.utils.spring.SpringUtils;
import com.wjyoption.common.vo.UserLogin;
import com.wjyoption.framework.interceptor.BaseHandlerInterceptorAdapter;


/**
 * <P>
 * Description：签名验证
 * </P>
 *
 * @author tcloud
 * @version 1.0.0
 */
@Component
public class ValidateLoginInterceptor extends BaseHandlerInterceptorAdapter
{
    private static Logger logger = LoggerFactory
            .getLogger(ValidateLoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception
    {

        if (null != handler && handler instanceof HandlerMethod)
        {

        	String path = request.getRequestURI();

        	//只拦截  路径中包含api 的
        	if(path.indexOf("/api/")==-1){
        		return true;
        	}

            HandlerMethod hand = (HandlerMethod) handler;

            Login login = hand.getMethodAnnotation(Login.class);

            if(login != null && login(request) == 0){
            	String errMsg = "没有登录：";
                buildResult(response, errMsg,ErrorConstants.NO_LOGIN);
                return false;
            }
        }

        return true;// 继续流程
    }

//    @SuppressWarnings("unchecked")
	protected int login(HttpServletRequest request)
    {
    	if (null == request)
    	{
    		return -1;
    	}
    	RedisTemplate<String,UserLogin> redisTemplate = SpringUtils.getBean("redisTemplate");
    	String token = request.getHeader("Authorization");
    	if(token != null){
    		String key = RedisEnum.USER_LOGIN.getKeyPrefix() + token;
    		if(redisTemplate.hasKey(key)){
    			logger.info("用户token："+token);
    			try {
					ThreadLocals.setUser(redisTemplate.boundValueOps(key).get());
				} catch (Exception e) {
					logger.error("缓存有变动，需重新登录",e);
					return 0;
				}
    			return 1;
    		}
    	}
    	logger.info("用户不存在");
    	return 0;
    }


    private void buildResult(HttpServletResponse response, String errMsg,ErrorConstants error)
    {
        logger.warn(errMsg);
        
        this.writeResp(response, ErrorConstants.error(error));
    }
}
