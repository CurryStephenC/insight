/*
 * @fileName：SysLogInterceptor.java    2013-7-22 下午12:50:53
 *
 */
package com.wjyoption.framework.interceptor.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;

import com.wjyoption.common.annotation.Sign;
import com.wjyoption.common.constant.Constants;
import com.wjyoption.common.enums.ErrorConstants;
import com.wjyoption.common.utils.security.Md5Util;
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
public class ValidateSignInterceptor extends BaseHandlerInterceptorAdapter
{
    private static Logger logger = LoggerFactory
            .getLogger(ValidateSignInterceptor.class);

    @Value("${wjyoption.signValidateSwitch}")
    private String signValidateSwitch;
    
    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception
    {

        if (null != handler && handler instanceof HandlerMethod)
        {

        	String path = request.getRequestURI();

        	//只拦截  路径中包含api 的
        	try {
				if(path.indexOf("/api/")==-1){
					return true;
				}
			} catch (Exception e) {
				return true;
			}

            HandlerMethod hand = (HandlerMethod) handler;

            Sign s = hand.getMethodAnnotation(Sign.class);

            if ("1".equalsIgnoreCase(signValidateSwitch) && null != s && validate(request) == 0)
            {
                String errMsg = "签名不对：";
                buildResult(response, errMsg,ErrorConstants.SIGN_ERROR);
                return false;
            }
        }

        return true;// 继续流程
    }

    protected int validate(HttpServletRequest request)
    {
        if (null == request)
        {
            return -1;
        }
        String sign = request.getHeader("sign");
        String signs = Md5Util.MD5(request, Constants.DEFAULT_TOKENkEY);
        if (signs.equals(sign))
        {
            return 1;
        }
        logger.info("sign error |requesSigns:"+signs+"|realSign:"+sign);
        return 0;
    }
    
    private void buildResult(HttpServletResponse response, String errMsg,ErrorConstants error)
    {
        logger.warn(errMsg);
        
        this.writeResp(response, ErrorConstants.error(error));
    }
}
