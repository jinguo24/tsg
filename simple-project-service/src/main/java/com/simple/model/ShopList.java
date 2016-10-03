package com.simple.model;

import java.io.Serializable;
import java.util.List;

public class ShopList implements Serializable{

	private static final long serialVersionUID = 1L;
	private String owner;
	private String ownerName;
	private String headimg;
	private String zhuying;
	private int productCount;
	private boolean join;
	private List<ShopProduct> products;

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getZhuying() {
		return zhuying;
	}

	public void setZhuying(String zhuying) {
		this.zhuying = zhuying;
	}

	public int getProductCount() {
		return productCount;
	}

	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}

	public List<ShopProduct> getProducts() {
		return products;
	}

	public void setProducts(List<ShopProduct> products) {
		this.products = products;
	}

	public boolean isJoin() {
		return join;
	}

	public void setJoin(boolean join) {
		this.join = join;
	}

	public String getHeadimg() {
		return headimg;
	}

	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}
}
