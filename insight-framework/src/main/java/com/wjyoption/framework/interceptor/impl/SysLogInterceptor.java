package com.wjyoption.framework.interceptor.impl;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.NamedThreadLocal;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wjyoption.framework.interceptor.BaseHandlerInterceptorAdapter;

@Component
public class SysLogInterceptor extends BaseHandlerInterceptorAdapter
{
    private static Logger logger = LoggerFactory.getLogger(SysLogInterceptor.class);
    
    @Value("${logging.interception.switch}")
    private boolean logInterceptionSwitch = false;
    @Value("${logging.interception.length}")
    private Integer logInterceptionLength = 300;

    private NamedThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<Long>(
            "StopWatch-StartTime");

    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception
    {
        long beginTime = System.currentTimeMillis();// 1、开始时间
        startTimeThreadLocal.set(beginTime);// 线程绑定变量（该数据只有当前请求的线程可见）
        String path = request.getServletPath();
        if (StringUtils.isBlank(path))
        {
            path = request.getRequestURI();
        }
        logger.info("Start: " + path);
        return true;// 继续流程
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
            HttpServletResponse response, Object handler, Exception ex)
            throws Exception
    {
        long endTime = System.currentTimeMillis();// 2、结束时间
        long beginTime = startTimeThreadLocal.get();// 得到线程绑定的局部变量（开始时间）
        long consumeTime = endTime - beginTime;// 3、消耗的时间

        String path = request.getServletPath();
        if (StringUtils.isBlank(path))
        {
            path = request.getRequestURI();
        }
        logger.info("End: " + path + ". Time: " + consumeTime + "ms.");
        Map<String, String[]> params = new HashMap<String, String[]>(request.getParameterMap());
        try {
        	if(logInterceptionSwitch){
        		Set<String> keySet = params.keySet();
        		keySet.forEach(key -> {
        			String[] ps = params.get(key);
        			for(int i = 0;i < ps.length;i++){
        				if(ps[i].length() > logInterceptionLength){
        					ps[i] = ps[i].substring(0,logInterceptionLength) + "...";
        				}
        			}
        			params.put(key, ps);
        		});
        	}
		} catch (Exception e) {
		}
        ObjectMapper objectMapper = new ObjectMapper();
        logger.info("Params:" + objectMapper.writeValueAsString(params));

        if (consumeTime > 3000)
        {// 此处认为处理时间超过3000毫秒的请求为慢请求
            logger.info(String
                    .format("%s consume %d millis", path, consumeTime));
        }
    }
}