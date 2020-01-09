package com.framework.common.util.codec;


import org.apache.commons.codec.digest.DigestUtils;

import com.framework.common.util.num.RandomUtil;
/**
 * 
 * Created by hanyl on 2019年12月13日.
 */
public class MD5Util {

	/**
	 * MD5编码 
	 */
	public static String md5(String src) {
		
		return DigestUtils.md5Hex(src);
	}
	
	/**
	 * 前台用户输入的密码转换为数据库密码
	 */
	public static String formToPass(String pass,String salt) {
		
		return md5(pass+salt);
		
	}
	
	
	
	public static void main(String[] args) {
		System.out.println(RandomUtil.getRandomString(6));
	}
	
}
