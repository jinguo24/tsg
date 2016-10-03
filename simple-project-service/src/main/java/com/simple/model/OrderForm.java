package com.simple.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import com.simple.common.util.DoubleUtil;
import com.simple.common.util.PrimaryKeyUtil;
import com.simple.constant.Constant;

public class OrderForm implements Serializable{

	private static final long serialVersionUID = 1L;
	private String userPhone;
	private int productId;
	private String productName;
	private String userName;
	private String address;
	private int productCount;
	private String token;
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getProductCount() {
		return productCount;
	}
	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Order castToOrder(Product product,String seller,double percent,double syscharge) {
		Order order = new Order();
		order.setOrder_no(PrimaryKeyUtil.getId());
		order.setUser_phone(this.userPhone);
		order.setUser_name(this.userName);
		order.setProduct_id(product.getId());
		order.setProduct_name(product.getName());
		order.setAddress(this.address);
		order.setProduct_count(this.productCount);
		order.setPrice(product.getPrice());
		order.setOwner(product.getOwner());
		order.setSeller(seller);
		order.setCreate_time(new Timestamp(new Date().getTime()));
		order.setOrder_status(Constant.ORDER_STATUS_UNPAY);
		order.setProduct_image(product.getFirstImg());
		order.setTotal_price(product.getPrice()*this.productCount);
		double sellercharge = DoubleUtil.formatPrice(order.getTotal_price()*(percent-syscharge)/100.00);
		if (sellercharge<0) {
			sellercharge = 0d;
		}
		order.setSeller_total_charge(sellercharge);
		order.setAgent_total_charge(DoubleUtil.formatPrice(order.getTotal_price()*(100-percent)/100.00));
		return order;
	}
}
