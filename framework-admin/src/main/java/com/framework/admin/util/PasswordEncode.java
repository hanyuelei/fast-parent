package com.framework.admin.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.framework.common.util.Constants;

public class PasswordEncode {

  public static String PASSWORD_ENCODE(String pass) {
	if(null==pass) {
		pass=Constants.DEFAULT_PASSWORD;
	}
	 BCryptPasswordEncoder encoder =new BCryptPasswordEncoder();
	return encoder.encode(pass);
}
}
