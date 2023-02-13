package com.wjyoption.common.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 不能为空的参数名，||分割
 * @author yqh
 *
 */
@Target({ java.lang.annotation.ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotNullParam {
	
	public String value() ;
}
