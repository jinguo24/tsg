package com.simple.model;

import java.io.Serializable;

public class AgentHome implements Serializable{

	private static final long serialVersionUID = 6980691915784332851L;

	public AgentHome(Integer daifahuo, Integer tuihuozhong, Integer sellproduct,
			Integer nostock, Integer totalSellers, Double chargepercent,String zhuying) {
		this.daifahuo = (daifahuo==null)?0:daifahuo;
		this.tuihuozhong = (tuihuozhong==null)?0:tuihuozhong;
		this.sellproduct = (sellproduct==null)?0:sellproduct;
		this.nostock = (nostock==null)?0:nostock;
		this.totalSellers = (totalSellers==null)?0:totalSellers;
		this.chargepercent = (chargepercent==null)?0d:chargepercent;
		this.zhuying = zhuying;
	}
	private int daifahuo;
	private int tuihuozhong;
	private int sellproduct;
	private int nostock;
	private int totalSellers;
	private double chargepercent;
	private String zhuying;
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
	public int getSellproduct() {
		return sellproduct;
	}
	public void setSellproduct(int sellproduct) {
		this.sellproduct = sellproduct;
	}
	public int getNostock() {
		return nostock;
	}
	public void setNostock(int nostock) {
		this.nostock = nostock;
	}
	public int getTotalSellers() {
		return totalSellers;
	}
	public void setTotalSellers(int totalSellers) {
		this.totalSellers = totalSellers;
	}
	public double getChargepercent() {
		return chargepercent;
	}
	public void setChargepercent(double chargepercent) {
		this.chargepercent = chargepercent;
	}
	public String getZhuying() {
		return zhuying;
	}
	public void setZhuying(String zhuying) {
		this.zhuying = zhuying;
	}
}
