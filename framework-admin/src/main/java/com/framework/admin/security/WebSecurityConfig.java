package com.framework.admin.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.DefaultWebInvocationPrivilegeEvaluator;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
/**
*
* @EnableWebSecurity: 禁用Boot的默认Security配置，配合@Configuration启用自定义配置（需要扩展WebSecurityConfigurerAdapter）
* @EnableGlobalMethodSecurity(prePostEnabled = true): 启用Security注解，例如最常用的@PreAuthorize
* configure(HttpSecurity): Request层面的配置，对应XML Configuration中的<http>元素
* configure(WebSecurity): Web层面的配置，一般用来配置无需安全检查的路径
* configure(AuthenticationManagerBuilder): 身份验证配置，用于注入自定义身份验证Bean和密码校验规则
*/
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	 	@Autowired
	    private MyUserDetailService myUserDetailService;
	    @Autowired
	    private MyFilterSecurityInterceptor myFilterSecurityInterceptor;

	   @Bean
	    @Primary
	    public DefaultWebInvocationPrivilegeEvaluator customWebInvocationPrivilegeEvaluator() {
	        return new DefaultWebInvocationPrivilegeEvaluator(myFilterSecurityInterceptor);
	    }
	    @Bean
	    public BCryptPasswordEncoder bCryptPasswordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

	    @Override
	    public void configure(WebSecurity web) throws Exception {
	        // javaconfig 配置是这样 set 进去的.
	        web.securityInterceptor(myFilterSecurityInterceptor);
	        web.privilegeEvaluator(customWebInvocationPrivilegeEvaluator());
	        web.
	                ignoring()
	                .antMatchers("/static/**","/image/*");
	    }

	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	    	//在认证用户名之前认证验证码，如果验证码错误，将不执行用户名和密码的认证
	    	  http.addFilterBefore(new KaptchaAuthenticationFilter("/admin/login", "/admin/login?error=true"), UsernamePasswordAuthenticationFilter.class) ;
	        http.csrf().disable()
	                .authorizeRequests()
	                .antMatchers("/admin/login").permitAll()//访问：登录 页 无需登录认证权限
	                .antMatchers("/admin/code/image").permitAll()//访问：获取验证码 无需登录认证权限
//	                .antMatchers("/admin/image").permitAll()//访问：这些路径 无需登录认证权限
	                .antMatchers("/admin/**").authenticated()//访问：admin开头的请求路径都要认证
//	                .anyRequest().permitAll()
	               
	         .and()
	                .formLogin()
	                .loginPage("/admin/login")//指定登录页是”/admin/login”
	                 //授权失败页
//	                .failureUrl("/login?error")
//	                .successForwardUrl("/index")
	                .defaultSuccessUrl("/admin/index",true)
	                .successHandler(loginSuccessHandler()) //登录成功后可使用loginSuccessHandler()存储用户信息，可选。
	                .failureHandler(failureHandler())
	                .and()
	                .logout()
	                .logoutUrl("/admin/logout")
	                .logoutSuccessUrl("/admin/login?logout") //退出登录后的默认网址是”/home”
	                .permitAll()
	                .invalidateHttpSession(true)
	                .and()
	                .exceptionHandling().accessDeniedPage("/admin/403"); // 权限不足自动跳转403
	               // .and()
	                //.rememberMe()//登录后记住用户，下次自动登录,数据库中必须存在名为persistent_logins的表
	                //.tokenValiditySeconds(1209600);
	        http.headers().frameOptions().disable();//允许使用ifame
	        
//	        http.addFilterBefore(myFilterSecurityInterceptor, FilterSecurityInterceptor.class);

	    }

	    @Autowired
	    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	        //指定密码加密所使用的加密器为passwordEncoder()
	        //需要将密码加密后写入数据库
	        auth.userDetailsService(myUserDetailService).passwordEncoder(bCryptPasswordEncoder());
	    }

	    @Bean
	    public BCryptPasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder(4);
	    }

	    @Bean
	    public MyLoginSuccessHandler loginSuccessHandler() {
	        return new MyLoginSuccessHandler();
	    }
	    @Bean
	    public MyAuthenticationFailureHandler failureHandler() {
	    	return new MyAuthenticationFailureHandler();
	    }
	    public static void main(String[] args) {
	    	  BCryptPasswordEncoder encoder =new BCryptPasswordEncoder();
	    	  System.out.println("encoder:"+encoder.encode("123456"));
		}
}
