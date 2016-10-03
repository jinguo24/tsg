package com.simple.model;

/**
 * 银行表
 * base_bank
 * @author kaixiang
 *
 */
public class Bank extends AbstractBaseModel{

	private static final long serialVersionUID = 3087624468698518327L;
	
	private String name;
	private String code;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Bank() {
		super();
	}
	
}
