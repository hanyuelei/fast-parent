package com.framework.admin.security;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import com.framework.admin.controller.FwLoginController;
import com.framework.common.util.WebUtil;
import com.framework.common.util.result.CodeMsg;
import com.framework.common.util.verifycode.ImageCode;
/**
 * 
* @ClassName: KaptchaAuthenticationFilter  
* @Description: TODO(校验验证码是否正确，
*                                                           如果正确，执行下一个filter；
*                    在UsernamePasswordAuthenticationFilter之前执行)  
* @author hanyl
* @date 2019年6月14日 下午1:19:55  
*
 */
public class KaptchaAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
	 
	    private String servletPath;
	    
	    public KaptchaAuthenticationFilter(String servletPath, String failureUrl) {
	        super(servletPath);
	        this.servletPath = servletPath;
	        setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler(failureUrl));
	    }
	    @Override
	    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	        HttpServletRequest req = (HttpServletRequest) request;
	        HttpServletResponse res = (HttpServletResponse) response;
	        if ("POST".equalsIgnoreCase(req.getMethod()) && servletPath.equals(req.getServletPath())) {
	            Object object= req.getSession().getAttribute(FwLoginController.SESSION_KEY);
	            String kaptcha = req.getParameter("kaptcha");
	            if(null == object || null == kaptcha) {
	            	WebUtil.render(res,CodeMsg.error("验证码失效"));
	            	return ;
	            }
            	ImageCode imageCode = (ImageCode)object;
            	boolean boo = compareTime(imageCode.getLocalDateTime());
            	if( ! boo ) {
            		WebUtil.render(res,CodeMsg.error("验证码失效"));
	            	return ;
            	}
            	if( !imageCode.getCode().equalsIgnoreCase(kaptcha)) {
//            		  unsuccessfulAuthentication(req, res, new InsufficientAuthenticationException("输入的验证码不正确"));
            		WebUtil.render(res,CodeMsg.error("验证码错误"));
            		return;
            	}
	        }
	        chain.doFilter(request, response);
	    }
	    @Override
	    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
	        return null;
	    }
	    

	    public boolean compareTime(LocalDateTime end) {
	        LocalDateTime now = LocalDateTime.now();
	        Long nowMilli = now.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
	        Long endMilli = end.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
			if(nowMilli<=endMilli) {
				return true;
			}
	        return false;
	    }
	

}
