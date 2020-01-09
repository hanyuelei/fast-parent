package com.framework.common.util.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;


public class ValidatorUtil {

	private static final Pattern mobile_pattern =Pattern.compile("1\\d{10}");
	/**
	 * 校验是否为手机号
	 */
	public static boolean isMobile(String mobile) {
		
		if(StringUtils.isEmpty(mobile)) {
			return false;
		}
		Matcher m = mobile_pattern.matcher(mobile);
		return m.matches();
	}
}
