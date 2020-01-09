package com.framework.common.redis.key;


/**
 * 
   *系统配置文件key
 */
public class SysConfigKey extends BasePrefix {

	
	public SysConfigKey(int expireSeconds, String prefix) {
		super(expireSeconds, prefix);
		// TODO Auto-generated constructor stub
	}
	public SysConfigKey(String prefix) {
		super(prefix);
	}
	
	public static SysConfigKey getConfig = new SysConfigKey("config");
}
