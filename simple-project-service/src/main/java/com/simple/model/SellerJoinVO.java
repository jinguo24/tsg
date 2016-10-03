package com.simple.model;

import java.util.ArrayList;
import java.util.List;

public class SellerJoinVO implements java.io.Serializable{
	
	private static final long serialVersionUID = -5721772385851062618L;
	
	private String userPhone;		//用户电话
	private String wechatNo;		//店铺名称
	private String category;		//店铺主营
	private Integer countSelling;	//在售商品总数
	private Double chargePst;		//店铺提点
	private Integer isJoin;			//是否加入代销 1:加入 0：未加入
	
	private List<SellerJoinProductVO> lists = new ArrayList<SellerJoinProductVO>();
	
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
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Integer getCountSelling() {
		return countSelling;
	}
	public void setCountSelling(Integer countSelling) {
		this.countSelling = countSelling;
	}
	public Double getChargePst() {
		return chargePst;
	}
	public void setChargePst(Double chargePst) {
		this.chargePst = chargePst;
	}
	public Integer getIsJoin() {
		return isJoin;
	}
	public void setIsJoin(Integer isJoin) {
		this.isJoin = isJoin;
	}
	public List<SellerJoinProductVO> getLists() {
		return lists;
	}
	public void setLists(List<SellerJoinProductVO> lists) {
		this.lists = lists;
	}
}
