package com.simple.model;

import java.io.Serializable;

public class RegisterUserForm implements Serializable{

	private static final long serialVersionUID = 6995878068108444113L;

	private String userPhone;
	private String wechatNo;
	private String password;
	private String validateCode;
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getWechatNo() {
		return wechatNo;
	}
	public void setWechatNo(String wechatNo) {
		this.wechatNo = wechatNo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getValidateCode() {
		return validateCode;
	}
	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}
}
