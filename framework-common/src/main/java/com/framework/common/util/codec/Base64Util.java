package com.framework.common.util.codec;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;

public class Base64Util {

	/**
	 * Base64编码
	 */
	public static String encode(String str) {
		Base64 base64 = new Base64();
		try {
			str = base64.encodeToString(str.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * 
	 *Base64解码
	 */
	public static String decode(String str) {
		
		return new String(Base64.decodeBase64(str));
		
	}
	
	
	public static void main(String[] args) {
		String encode = encode("123456");
		
		System.out.println("encode:"+encode);
		
		System.out.println("decode:"+decode(encode));
	}
	

}
