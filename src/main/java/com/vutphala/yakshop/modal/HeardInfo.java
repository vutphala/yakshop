package com.vutphala.yakshop.modal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class HeardInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1883215349541156909L;
	private double milkStock;
	private int woolStock;
	private List<YakInstance> yaks;
	private int instanceDay=0;
	public HeardInfo(int instanceDay) {
		yaks = new ArrayList<YakInstance>();
		milkStock = 0;
		woolStock = 0;
		this.instanceDay=instanceDay;
	}

	public double getMilkStock() {
		return milkStock;
	}
	
	public void setMilkStock(int milkStock) {
		this.milkStock = milkStock;
	}

	public int getWoolStock() {
		return woolStock;
	}

	public void setWoolStock(int woolStock) {
		this.woolStock = woolStock;
	}

	public List<YakInstance> getYaks() {
		return yaks;
	}

	public void setYaks(List<YakInstance> yaks) {
		this.yaks = yaks;
	}

	public void addYak(YakInstance yak) {
		yaks.add(yak);
		milkStock = milkStock + yak.getMilk();
		woolStock = woolStock + yak.getWool();
	}

}
