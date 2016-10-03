package com.simple.model;

public class AgentSeller extends AbstractBaseModel{

	private static final long serialVersionUID = -5487532075050895286L;

	private String agentPhone;
	private String agentName;
	private String sellerPhone;
	private String sellerName;
	private double chargePercent;
	private int allowSell;
	private int watchCount;
	public String getAgentPhone() {
		return agentPhone;
	}
	public void setAgentPhone(String agentPhone) {
		this.agentPhone = agentPhone;
	}
	public String getSellerPhone() {
		return sellerPhone;
	}
	public void setSellerPhone(String sellerPhone) {
		this.sellerPhone = sellerPhone;
	}
	public double getChargePercent() {
		return chargePercent;
	}
	public void setChargePercent(double chargePercent) {
		this.chargePercent = chargePercent;
	}
	public String getAgentName() {
		return agentName;
	}
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	public String getSellerName() {
		return sellerName;
	}
	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
	public int getAllowSell() {
		return allowSell;
	}
	public void setAllowSell(int allowSell) {
		this.allowSell = allowSell;
	}
	public int getWatchCount() {
		return watchCount;
	}
	public void setWatchCount(int watchCount) {
		this.watchCount = watchCount;
	}
	public boolean isAllow() {
		if (this.allowSell != 1) {
			return false;
		}
		return true;
	}
}
