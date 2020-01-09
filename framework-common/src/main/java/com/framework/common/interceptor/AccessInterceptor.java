package com.framework.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.framework.common.annotation.AccessLimit;
import com.framework.common.annotation.Login;
import com.framework.common.config.resolver.UserVO;
import com.framework.common.redis.RedisService;
import com.framework.common.redis.key.AccessKey;
import com.framework.common.util.WebUtil;
import com.framework.common.util.result.CodeMsg;
/**
 * Created by hanyl on 2019年12月17日.
 */
@Service
public class AccessInterceptor extends HandlerInterceptorAdapter {
	

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if(handler instanceof HandlerMethod) {
			UserVO user = getUser(request);
			UserContext.setUser(user);
			
			HandlerMethod hm = (HandlerMethod) handler;
			//拦截有@Login注解的方法，验证是否有用户登录
			Login login = hm.getMethodAnnotation(Login.class);
			if(null != login) {
				if(user == null) {
					WebUtil.render(response,CodeMsg.SESSION_EXPIRE);
					return false;
				}
			}
			//拦截有@AccessLimit注解的方法，实现接口限流
			AccessLimit accessLimit = hm.getMethodAnnotation(AccessLimit.class);
			if(null != accessLimit) {
				int seconds = accessLimit.seconds();
				int maxCount = accessLimit.maxCount();
				boolean needLogin = accessLimit.needLogin();
				String key = request.getRequestURI();
				if(needLogin) {
					if(user == null) {
						WebUtil.render(response,CodeMsg.SESSION_EXPIRE);
						return false;
					}
					key += "_" + user.getUserId();
				}else {
					//
				}
				AccessKey accessKey = AccessKey.withExpire(seconds);
				Integer count = RedisService.get(accessKey, key, Integer.class);
				if(count == null) {
					RedisService.set(accessKey, key,1);
				}else if(count < maxCount) {
					RedisService.incr(accessKey, key);
				}else {
					WebUtil.render(response,CodeMsg.LIMIT_ERROR);
					return false;
				}
			}

			
		}
		return true;
	}
	

	private UserVO getUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Object object = session.getAttribute("userId");
		if(null != object) {
			UserVO user = new UserVO();
			user.setUserId(Long.parseLong(object.toString()));
			return user;
		}
		return null;
	}
}
