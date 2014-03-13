package com.vutphala.yakshop.business.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vutphala.yakshop.business.dao.HeardDao;
import com.vutphala.yakshop.business.dao.impl.XMLHeardDaoImpl;
import com.vutphala.yakshop.business.service.HeardService;
import com.vutphala.yakshop.modal.HeardInfo;
import com.vutphala.yakshop.modal.LabYakInstance;
import com.vutphala.yakshop.modal.Yak;
import com.vutphala.yakshop.modal.YakInstance;


public class HeardServiceImpl implements HeardService {

	private HeardDao dao = null;// new XMLHeardDaoImpl(heardData);

	public HeardServiceImpl(String heardData) {
		dao = new XMLHeardDaoImpl(heardData);
	}

	@Override
	public HeardInfo getHeardDetailsForTheDay(int day) {
		HeardInfo info = new HeardInfo(day);
		for (Yak yak : dao.getAllYaks()) {
			YakInstance yakInstance=new LabYakInstance(yak, day);
			if (yak.getStatus() == Yak.ALIVE) {
				info.addYak(yakInstance);

			}

		}
		return info;
	}

}
