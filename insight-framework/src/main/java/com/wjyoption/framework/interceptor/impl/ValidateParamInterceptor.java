/*
 * @fileName：SysLogInterceptor.java    2013-7-22 下午12:50:53
 *
 */
package com.wjyoption.framework.interceptor.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;

import com.wjyoption.common.annotation.NotNullParam;
import com.wjyoption.common.core.domain.Response;
import com.wjyoption.common.enums.ErrorConstants;
import com.wjyoption.framework.interceptor.BaseHandlerInterceptorAdapter;


/**
 * <P>
 * Description：参数验证
 * </P>
 *
 * @author tcloud
 * @version 1.0.0
 */
@Component
public class ValidateParamInterceptor extends BaseHandlerInterceptorAdapter
{
    private static Logger logger = LoggerFactory
            .getLogger(ValidateParamInterceptor.class);

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

            NotNullParam s = hand.getMethodAnnotation(NotNullParam.class);
            if(s != null && validate(request,s.value()) != null)
            {
            	String key = validate(request,s.value());
            	if(key != null){
            		String errMsg = "参数不能为空："+key;
            		buildResult(response, errMsg,ErrorConstants.PARAMS_ERROR,key);
            		return false;
            	}
            }
        }

        return true;// 继续流程
    }

    protected String validate(HttpServletRequest request,String value)
    {
        if (null == request || value == null)
        {
            return null;
        }
        String[] vals = value.split("\\|\\|");//new String[]{value};
//        if(value.contains("||")){
//        	vals = value.split("\\|\\|");
//        }
        for(String key : vals){
        	if(StringUtils.isBlank(request.getParameter(key))){
        		return key;
        	}
        }
        return null;
    }
    
    private void buildResult(HttpServletResponse response, String errMsg,ErrorConstants error,String key)
    {
        logger.warn(errMsg);
        Response<Object> resp = ErrorConstants.error(error);
        resp.setMessage(resp.getMessage() + " | " + key);
        this.writeResp(response, resp);
    }
}
