package com.vutphala.yakshop.business.dao;

import java.util.List;

import com.vutphala.yakshop.modal.Yak;

public interface HeardDao {

	public Yak getYak(int id);
	public Yak getYakByName(String name);
	public List<Yak> getAllYaks();
	//public List<Yak> getYaksForday(int day);
	public boolean consumeYakMilk(int id,int qty);
	public boolean shaveYak(int id);
}
