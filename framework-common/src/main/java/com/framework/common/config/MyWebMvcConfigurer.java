package com.framework.common.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.framework.common.config.resolver.UserArgumentResolver;
import com.framework.common.interceptor.AccessInterceptor;

/**
 * 
* @ClassName: MyWebMvcConfigurer  
* @Description: TODO(WebMvcConfigurer)  
* @author hanyl
* @date 2019年6月26日 下午4:40:09  
*
 */
@SpringBootConfiguration
public class MyWebMvcConfigurer implements WebMvcConfigurer {
	
	@Autowired
	UserArgumentResolver userArgumentResolver;
	
	@Value("${custom.imageResourceHandler}")
    private String imageResourceHandler;

	@Value("${custom.imageResourceLocations}")
    private String imageResourceLocations;
	
	@Autowired
	private AccessInterceptor accessInterceptor;
	
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        //设置图片虚拟目录
        registry.addResourceHandler(imageResourceHandler).addResourceLocations(imageResourceLocations);
    }
    
    /**
               * 注册接口限流的拦截器
     */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(accessInterceptor);
	}
	/**
	 * 注册给controller赋值的拦截器
	 */
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
	
		argumentResolvers.add(userArgumentResolver);
	}
}
