package com.simple.model;

public class SellerMainVO implements java.io.Serializable{
	
	private static final long serialVersionUID = -5010299911543691128L;
	
	// head
	private Double totalPrice; 		//总销售额
	private Double totalCharge; 		//总提成额
	private Double balance;					//余额
	private Double withdrawAmount;			//可提现额度
	
	//middle
	private Integer trading;		//交易中订单
	private Integer rejectCount;	//退货中订单
	private Integer ownerCount;		//代销店铺数
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Double getTotalCharge() {
		return totalCharge;
	}
	public void setTotalCharge(Double totalCharge) {
		this.totalCharge = totalCharge;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public Double getWithdrawAmount() {
		return withdrawAmount;
	}
	public void setWithdrawAmount(Double withdrawAmount) {
		this.withdrawAmount = withdrawAmount;
	}
	public Integer getTrading() {
		return trading;
	}
	public void setTrading(Integer trading) {
		this.trading = trading;
	}
	public Integer getRejectCount() {
		return rejectCount;
	}
	public void setRejectCount(Integer rejectCount) {
		this.rejectCount = rejectCount;
	}
	public Integer getOwnerCount() {
		return ownerCount;
	}
	public void setOwnerCount(Integer ownerCount) {
		this.ownerCount = ownerCount;
	}
}
