package com.framework.common.config.resolver;

public class UserVO {

	private Long userId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "UserVO [userId=" + userId + "]";
	}
	
	
}
