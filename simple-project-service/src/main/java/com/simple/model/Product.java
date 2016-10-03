package com.simple.model;

public class Product extends AbstractBaseModel{

	private static final long serialVersionUID = -4498554445122690179L;

	private String name;
	private int stock;
	private double price;
	private String description;
	private String tip;
	private int productStatus;
	private String owner;
	private String thumbnail;//缩略图，多张图
	private String firstImg;
	private double charge;//提成
	private String sellerPhone;//用户购买时显示的代销电话
	private String sellerWeChatNo;//用户购买时显示的代销微信
	private String sellerNickName;
	private String headimg;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
	public int getProductStatus() {
		return productStatus;
	}
	public void setProductStatus(int productStatus) {
		this.productStatus = productStatus;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public String getFirstImg() {
		return firstImg;
	}
	public void setFirstImg(String firstImg) {
		this.firstImg = firstImg;
	}
	public double getCharge() {
		return charge;
	}
	public void setCharge(double charge) {
		this.charge = charge;
	}
	public String getSellerPhone() {
		return sellerPhone;
	}
	public void setSellerPhone(String sellerPhone) {
		this.sellerPhone = sellerPhone;
	}
	public String getSellerWeChatNo() {
		return sellerWeChatNo;
	}
	public void setSellerWeChatNo(String sellerWeChatNo) {
		this.sellerWeChatNo = sellerWeChatNo;
	}
	public String getSellerNickName() {
		return sellerNickName;
	}
	public void setSellerNickName(String sellerNickName) {
		this.sellerNickName = sellerNickName;
	}
	public String getHeadimg() {
		return headimg;
	}
	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}
}
