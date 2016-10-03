package com.simple.model;

import java.io.Serializable;

public class Charge implements Serializable{

	private static final long serialVersionUID = 1L;

	private int id;
	private Double charge;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Double getCharge() {
		return charge;
	}
	public void setCharge(Double charge) {
		this.charge = charge;
	}
}
