package com.simple.model;

import java.sql.Timestamp;
import java.util.Date;

import com.simple.common.util.DateUtil;

public class Order extends AbstractBaseModel implements java.io.Serializable{
	
	private static final long serialVersionUID = 5784715568286725038L;
	
	private String order_no;
	private String user_phone;
	private int product_id; 
	private String product_name;
	private String user_name;
	private String address;
	private int product_count; 
	private double price;
	private String owner;
	private String seller;
	private Timestamp create_time;
	private String showCreateTime;
	private int order_status; 
	private String expressage;
	private String expressage_name;
	private String expressage_no;
	private Timestamp expressage_time;
	private String showExpressageTime;
	private Timestamp reject_time;
	private String showRejectTime;
	private String reject_remark;
	private Timestamp apply_reject_time;
	private String showApplyRejectTime;
	private int apply_reject_reason;
	private Timestamp pay_time;
	private String showPayTime;
	private String product_image;
	private double total_price;
	private double agent_total_charge;
	private double seller_total_charge;
	private String weixin_order_no;
	private String productToken;
	private String sellerWx;
	private String ownerWx;
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public String getUser_phone() {
		return user_phone;
	}
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getProduct_count() {
		return product_count;
	}
	public void setProduct_count(int product_count) {
		this.product_count = product_count;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getSeller() {
		return seller;
	}
	public void setSeller(String seller) {
		this.seller = seller;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
		if ( null != this.create_time) {
			this.showCreateTime = DateUtil.date2AllString(this.create_time);
		}
	}
	public int getOrder_status() {
		return order_status;
	}
	public void setOrder_status(int order_status) {
		this.order_status = order_status;
	}
	public String getExpressage() {
		return expressage;
	}
	public void setExpressage(String expressage) {
		this.expressage = expressage;
	}
	public String getExpressage_name() {
		return expressage_name;
	}
	public void setExpressage_name(String expressage_name) {
		this.expressage_name = expressage_name;
	}
	public String getExpressage_no() {
		return expressage_no;
	}
	public void setExpressage_no(String expressage_no) {
		this.expressage_no = expressage_no;
	}
	public Date getExpressage_time() {
		return expressage_time;
	}
	public void setExpressage_time(Timestamp expressage_time) {
		this.expressage_time = expressage_time;
		if ( null != this.expressage_time) {
			this.showExpressageTime = DateUtil.date2String(expressage_time);
		}
	}
	public Date getReject_time() {
		return reject_time;
	}
	public void setReject_time(Timestamp reject_time) {
		this.reject_time = reject_time;
		if ( null != this.reject_time) {
			this.showRejectTime = DateUtil.date2AllString(reject_time);
		}
	}
	public String getReject_remark() {
		return reject_remark;
	}
	public void setReject_remark(String reject_remark) {
		this.reject_remark = reject_remark;
	}
	public Date getPay_time() {
		return pay_time;
	}
	public void setPay_time(Timestamp pay_time) {
		this.pay_time = pay_time;
		if ( null != this.pay_time) {
			this.showPayTime = DateUtil.date2AllString(pay_time);
		}
	}
	public String getProduct_image() {
		return product_image;
	}
	public void setProduct_image(String product_image) {
		this.product_image = product_image;
	}
	public double getTotal_price() {
		return total_price;
	}
	public void setTotal_price(double total_price) {
		this.total_price = total_price;
	}
	public double getAgent_total_charge() {
		return agent_total_charge;
	}
	public void setAgent_total_charge(double agent_total_charge) {
		this.agent_total_charge = agent_total_charge;
	}
	public double getSeller_total_charge() {
		return seller_total_charge;
	}
	public void setSeller_total_charge(double seller_total_charge) {
		this.seller_total_charge = seller_total_charge;
	}
	public String getShowCreateTime() {
		return showCreateTime;
	}
	public void setShowCreateTime(String showCreateTime) {
		this.showCreateTime = showCreateTime;
	}
	public Date getApply_reject_time() {
		return apply_reject_time;
	}
	public void setApply_reject_time(Timestamp apply_reject_time) {
		this.apply_reject_time = apply_reject_time;
		if ( null != this.apply_reject_time) {
			this.showApplyRejectTime = DateUtil.date2AllString(apply_reject_time);
		}
	}
	public String getShowExpressageTime() {
		return showExpressageTime;
	}
	public void setShowExpressageTime(String showExpressageTime) {
		this.showExpressageTime = showExpressageTime;
	}
	public String getShowRejectTime() {
		return showRejectTime;
	}
	public void setShowRejectTime(String showRejectTime) {
		this.showRejectTime = showRejectTime;
	}
	public String getShowApplyRejectTime() {
		return showApplyRejectTime;
	}
	public void setShowApplyRejectTime(String showApplyRejectTime) {
		this.showApplyRejectTime = showApplyRejectTime;
	}
	public String getShowPayTime() {
		return showPayTime;
	}
	public void setShowPayTime(String showPayTime) {
		this.showPayTime = showPayTime;
	}
	public String getWeixin_order_no() {
		return weixin_order_no;
	}
	public void setWeixin_order_no(String weixin_order_no) {
		this.weixin_order_no = weixin_order_no;
	}
	public String getProductToken() {
		return productToken;
	}
	public void setProductToken(String productToken) {
		this.productToken = productToken;
	}
	public String getSellerWx() {
		return sellerWx;
	}
	public void setSellerWx(String sellerWx) {
		this.sellerWx = sellerWx;
	}
	public int getApply_reject_reason() {
		return apply_reject_reason;
	}
	public void setApply_reject_reason(int apply_reject_reason) {
		this.apply_reject_reason = apply_reject_reason;
	}
	public String getOwnerWx() {
		return ownerWx;
	}
	public void setOwnerWx(String ownerWx) {
		this.ownerWx = ownerWx;
	}
}
