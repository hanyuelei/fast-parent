package com.hanyl.admin.vo;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.framework.common.util.validator.IsMobile;

public class LoginVo {

	@NotNull(message="手机号不能为空")
	@IsMobile
	private String mobile;
	
	@NotNull(message="密码不能为空")
	@Length(min=5,max=10)
	private String password;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
