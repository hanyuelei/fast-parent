package com.framework.common.interceptor;

import com.framework.common.config.resolver.UserVO;

/**
 *      线程安全的threadLocal 用户上下文保存数据
 * Created by hanyl on 2019年12月16日.
 */
public class UserContext {

	private static ThreadLocal<UserVO> userHolder = new ThreadLocal<UserVO>();

	public static void  setUser(UserVO user) {
		userHolder.set(user);
	}
	
	public static UserVO  getUser() {
		return userHolder.get();
	}
}
