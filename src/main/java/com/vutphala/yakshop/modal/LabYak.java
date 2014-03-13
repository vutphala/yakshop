package com.vutphala.yakshop.modal;

import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Days;


public class LabYak implements Yak {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5432866143252897526L;
	private int id;
	private String name;
	private Calendar dateOfBirth;

	private String sex;
	private int milkLastConsumedAge;
	private int lastShavedAge;

	public LabYak(int id, String name, int age, String sex) {
		this.id = id;
		this.name = name;
		dateOfBirth = Calendar.getInstance();
		dateOfBirth.add(Calendar.DATE, -age);
		lastShavedAge = age;
		milkLastConsumedAge = age;
		this.sex = sex;
	}

	@Override
	public int getId() {

		return id;
	}

	@Override
	public String getName() {

		return this.name;
	}

	@Override
	public String getSex() {

		return this.sex;
	}

	@Override
	public int getAge() {
		return Days.daysBetween(new DateTime(dateOfBirth.getTime()),
				new DateTime(new Date())).getDays();
	}

	@Override
	public Integer getStatus() {
		int currentage = getAge();
		if (currentage >= (10 * 100)) {
			return DIED;
		}
		return ALIVE;
	}

	@Override
	public int getCurrentMilkStock() {
		return 0;//getMilkAvailableForTheDay(1);
	}

	@Override
	public boolean isAvailbeForShave() {
		int currentage = getAge();

		if ((currentage > 100) && (lastShavedAge) < (currentage - 1)) {
			return true;
		}

		return false;
	}

	@Override
	public int getLastShaveAge() {
		return lastShavedAge;
	}

	@Override
	public int shaveTheYak() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Method Not Implemented Yet");
	}
	@Override
	public int getDaysForWool() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Method Not Implemented Yet");
	}

	
	@Override
	public boolean consumeMilk() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Method Not Implemented Yet");
	}

	@Override
	public int getLastMilkConsumedAge() {
		return milkLastConsumedAge;
	}


}
