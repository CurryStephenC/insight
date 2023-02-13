package com.wjyoption.framework.interceptor;


import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.wjyoption.common.core.domain.AjaxResult;
import com.wjyoption.common.core.domain.Response;
import com.wjyoption.common.json.JSON;
import com.wjyoption.common.utils.ServletUtils;

public class BaseHandlerInterceptorAdapter
  extends HandlerInterceptorAdapter
{
  private static Logger logger = LoggerFactory.getLogger(BaseHandlerInterceptorAdapter.class);
  
  public void writeResp(HttpServletResponse response, AjaxResult resp)
  {
    try {
		ServletUtils.renderString(response, JSON.marshal(resp));
	} catch (Exception e) {
		logger.error(e.getMessage(),e);
	}
  }
  
  public void writeResp(HttpServletResponse response, Response<?> resp)
  {
	  try {
		  response.setHeader("Access-Control-Allow-Origin", "*");
          response.setHeader("Access-Control-Allow-Methods", "*");
		  ServletUtils.renderString(response, JSON.marshal(resp));
	  } catch (Exception e) {
		  logger.error(e.getMessage(),e);
	  }
  }
  
  
}
