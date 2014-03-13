package com.vutphala.yakshop.modal;

import java.io.Serializable;

public class Order implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1234073574018937063L;
	public double getMilk() {
		return milk;
	}

	public void setMilk(double milk) {
		this.milk = milk;
	}

	public int getSkins() {
		return skins;
	}

	public void setSkins(int skins) {
		this.skins = skins;
	}

	private double milk;
	private int skins;
}
