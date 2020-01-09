package com.framework.common.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(METHOD)
public @interface AccessLimit {

	 int seconds();//多少秒
	 int maxCount();//最多访问多少次
	 boolean needLogin() default true;//是否需要登录
	
}
