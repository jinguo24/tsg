package com.simple.model;

import java.util.Date;

public class User extends AbstractBaseModel implements java.io.Serializable{
	
	private static final long serialVersionUID = 749814039851956911L;
	
	private String userName="";
	private String userPhone;
	private String userTel;
	private String password;
	private String weChatNo="";
	private String IDCard;
	private String address="";
	private Date registerDate;
	private String salt;
	private double blance;
	private String userNick="";
	private String category="";
	private int allowSell;
	private double chargePrecent;
	private String headimg;
	private int status;
	
	public User() {
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserTel() {
		return userTel;
	}
	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getWeChatNo() {
		return weChatNo;
	}
	public void setWeChatNo(String weChatNo) {
		this.weChatNo = weChatNo;
	}
	public String getIDCard() {
		return IDCard;
	}
	public void setIDCard(String iDCard) {
		IDCard = iDCard;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public double getBlance() {
		return blance;
	}
	public void setBlance(double blance) {
		this.blance = blance;
	}
	public String getUserNick() {
		return userNick;
	}
	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}

	public int getAllowSell() {
		return allowSell;
	}

	public void setAllowSell(int allowSell) {
		this.allowSell = allowSell;
	}

	public double getChargePrecent() {
		return chargePrecent;
	}

	public void setChargePrecent(double chargePrecent) {
		this.chargePrecent = chargePrecent;
	}
	public boolean isAllow() {
		if (this.allowSell != 1) {
			return false;
		}
		return true;
	}

	public String getHeadimg() {
		return headimg;
	}

	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
