package com.vutphala.yakshop.client;

import com.vutphala.yakshop.business.service.HeardService;
import com.vutphala.yakshop.business.service.impl.HeardServiceImpl;
import com.vutphala.yakshop.modal.HeardInfo;
import com.vutphala.yakshop.modal.YakInstance;

public class YakShop {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HeardService service = new HeardServiceImpl(args[0]);
		
		HeardInfo info=service.getHeardDetailsForTheDay(Integer.parseInt(args[1]));
		System.out.println("In Stock:");
		System.out.println("\t"+info.getMilkStock()+" liters of milk");
		System.out.println("\t"+info.getWoolStock()+" skins of wool");
		System.out.println("Herd :");
		for(YakInstance yak:info.getYaks()){
			System.out.println("\t"+yak.getName()+" "+yak.getAge()+" years old");	
		}
		
	}

}
