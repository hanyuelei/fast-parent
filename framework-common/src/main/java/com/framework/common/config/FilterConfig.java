
package com.framework.common.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.framework.common.xss.XssFilter;

import javax.servlet.DispatcherType;

@Configuration
public class FilterConfig {

    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
    public FilterRegistrationBean xssFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setDispatcherTypes(DispatcherType.REQUEST);
        registration.setFilter(new XssFilter());//注册自定义过滤器
        registration.addUrlPatterns("/*");//拦截所有url
        registration.setName("xssFilter");//定义过滤器名称
        registration.setOrder(Integer.MAX_VALUE);//优先级，越低越优先
        return registration;
    }
}
