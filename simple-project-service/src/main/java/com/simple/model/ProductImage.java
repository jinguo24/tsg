package com.simple.model;

public class ProductImage extends AbstractBaseModel{

	private static final long serialVersionUID = -611475846919171869L;

	private int productId;
	private String image;
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
}
