package com.vutphala.yakshop.modal;

import java.io.Serializable;


public interface YakInstance extends Serializable{
	public double getMilk();
	public double getAge();
	public int getWool();
	public String getName();
	public double getAgeLastShaved();
	public int getStatus();

}
