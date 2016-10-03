package com.simple.model;

public class SellerJoinProductVO implements java.io.Serializable {

	private static final long serialVersionUID = -8352799951270509473L;
	
	private String userPhone;
	private String productName;
	private Double price;
	private Double chargePrice;
	private String thumbnail;
	
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getChargePrice() {
		return chargePrice;
	}
	public void setChargePrice(Double chargePrice) {
		this.chargePrice = chargePrice;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	
	
	
}
