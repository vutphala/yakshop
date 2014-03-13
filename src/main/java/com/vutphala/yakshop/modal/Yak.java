package com.vutphala.yakshop.modal;

import java.io.Serializable;



public interface Yak extends Serializable{
	public static final Integer NOT_AVAILABLE=0;
	public static final int ALIVE = 1;
	public static final int DIED = 2;
	
	public int getId();
	public int getAge();
	public String getName();
	public String getSex();
	public Integer getStatus();

	
	public boolean isAvailbeForShave();
	public int getDaysForWool();
	public int getLastShaveAge();
	public int shaveTheYak();
	
	
	public int getCurrentMilkStock();
	public boolean consumeMilk();
	public int getLastMilkConsumedAge();
	
	
	
	
}
