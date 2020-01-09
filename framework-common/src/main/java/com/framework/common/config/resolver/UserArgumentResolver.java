package com.framework.common.config.resolver;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.framework.common.annotation.LoginUser;
import com.framework.common.interceptor.UserContext;

/**
 *    给controller带@LoginUser注解的参数userVo注入当前登录用户信息
 *    
 * Created by hanyl on 2019年12月17日.
 */
@Service
public class UserArgumentResolver implements  HandlerMethodArgumentResolver {

	/**
	 * 只有这里返回true ，才会执行resolveArgument
	 */
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		// TODO Auto-generated method stub
		Class<?> clazz = parameter.getParameterType();
//		return clazz == UserVO.class;
		return parameter.getParameterType().isAssignableFrom(UserVO.class) && parameter.hasParameterAnnotation(LoginUser.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		// TODO Auto-generated method stub
//		HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
//		HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
//		String userId = request.getAttribute("userId").toString();
		return UserContext.getUser();
	}

}
