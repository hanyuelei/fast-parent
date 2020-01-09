package com.framework.common.redis.key;

public class UserKey extends BasePrefix {

	public UserKey(int expireSeconds, String prefix) {
		super(expireSeconds, prefix);
		// TODO Auto-generated constructor stub
	}
	public UserKey(String prefix) {
		super(prefix);
	}
	
	private static UserKey getById = new UserKey("id");
	
	public static UserKey base_key = new UserKey("user_vo");
}
