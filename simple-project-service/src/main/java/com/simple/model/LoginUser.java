package com.simple.model;

import java.io.Serializable;

public class LoginUser implements Serializable{

	private static final long serialVersionUID = 1L;

	private String code;//工号
	private String name;//名称
	private Integer isSuperUser;//是否是超级用户
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getIsSuperUser() {
		return isSuperUser;
	}
	public void setIsSuperUser(Integer isSuperUser) {
		this.isSuperUser = isSuperUser;
	}
}
