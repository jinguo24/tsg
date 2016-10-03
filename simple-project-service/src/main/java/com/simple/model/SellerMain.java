package com.simple.model;

import java.io.Serializable;
import java.util.List;

public class SellerMain implements Serializable{

	private static final long serialVersionUID = 1L;

	private int daifahuo;
	private int tuihuozhong;
	private int shopCount;
	private List<SellerListVO> shops;
	public int getDaifahuo() {
		return daifahuo;
	}
	public void setDaifahuo(int daifahuo) {
		this.daifahuo = daifahuo;
	}
	public int getTuihuozhong() {
		return tuihuozhong;
	}
	public void setTuihuozhong(int tuihuozhong) {
		this.tuihuozhong = tuihuozhong;
	}
	public int getShopCount() {
		return shopCount;
	}
	public void setShopCount(int shopCount) {
		this.shopCount = shopCount;
	}
	public List<SellerListVO> getShops() {
		return shops;
	}
	public void setShops(List<SellerListVO> shops) {
		this.shops = shops;
	}
}
