package com.framework.common.redis.key;

import com.framework.common.redis.RedisKeys;

public abstract class BasePrefix implements KeyPrefix {
    
	private int expireSeconds;
	
	private String prefix;
	
	public BasePrefix(String prefix) {//默认0永不过期
		this(0,prefix);
	}
	
	
	public BasePrefix(int expireSeconds,String prefix) {
		this.expireSeconds = expireSeconds;
		this.prefix = prefix;
	}
	@Override
	public int expireSeconds() {
		// TODO Auto-generated method stub
		return expireSeconds;
	}
	/**
	 * key = 类名 + 前缀 + 缓存key(作用是修改key，所有缓存失效，分测试、生产key ，在配置文件有配置)
	 */
	@Override
	public String getPrefix() {
	
		// TODO Auto-generated method stub
		String sk = RedisKeys.getSysConfigKey();
		String clazzString = getClass().getSimpleName();
		return clazzString + ":" + prefix+ ":" +sk;
	}

}
