package com.wjyoption.framework.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.wjyoption.common.config.Global;
import com.wjyoption.common.constant.Constants;
import com.wjyoption.framework.interceptor.RepeatSubmitInterceptor;
import com.wjyoption.framework.interceptor.impl.SysLogInterceptor;
import com.wjyoption.framework.interceptor.impl.ValidateLoginInterceptor;
import com.wjyoption.framework.interceptor.impl.ValidateParamInterceptor;
import com.wjyoption.framework.interceptor.impl.ValidateSignInterceptor;

/**
 * 通用配置
 * 
 * @author hs
 */
@Configuration
public class ResourcesConfig implements WebMvcConfigurer
{
    /**
     * 首页地址
     */
    @Value("${shiro.user.indexUrl}")
    private String indexUrl;

    @Autowired
    private RepeatSubmitInterceptor repeatSubmitInterceptor;
    
    @Autowired ValidateSignInterceptor validateSignInterceptor;
    @Autowired ValidateLoginInterceptor validateLoginInterceptor;
    @Autowired ValidateParamInterceptor validateParamInterceptor;
    
    @Autowired SysLogInterceptor sysLogInterceptor;

    /**
     * 默认首页的设置，当输入域名是可以自动跳转到默认指定的网页
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry)
    {
        registry.addViewController("/").setViewName("forward:" + indexUrl);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry)
    {
        /** 本地文件上传路径 */
        registry.addResourceHandler(Constants.RESOURCE_PREFIX + "/**").addResourceLocations("file:" + Global.getProfile() + "/");

        /** swagger配置 */
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    /**
     * 自定义拦截规则
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(repeatSubmitInterceptor).addPathPatterns("/**");
//        registry.addInterceptor(sysLogInterceptor).addPathPatterns("/**").excludePathPatterns("/","/css/**","/js/**","/img/**","/ruoyi/**","/ajax/**");
        registry.addInterceptor(sysLogInterceptor).addPathPatterns("/**").excludePathPatterns("/","/css/**","/js/**","/img/**","/wjyoption/**","/ajax/**");
        registry.addInterceptor(validateSignInterceptor).addPathPatterns("/api/**");
        registry.addInterceptor(validateLoginInterceptor).addPathPatterns("/api/**");
        registry.addInterceptor(validateParamInterceptor).addPathPatterns("/api/**");
    }
}