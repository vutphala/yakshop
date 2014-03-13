package com.vutphala.yakshop.business.dao.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.vutphala.yakshop.business.dao.HeardDao;
import com.vutphala.yakshop.modal.LabYak;
import com.vutphala.yakshop.modal.Yak;

public class XMLHeardDaoImpl implements HeardDao {

	private List<Yak> yakList = null;
	
	public XMLHeardDaoImpl(String heardData){
		init(heardData);
	}
	public void init(String heardData) {

		try {

			File fXmlFile = new File(heardData);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("labyak");
			if (nList != null && nList.getLength() > 0) {
				yakList=new ArrayList<Yak>(nList.getLength());
				

				for (int yakCounter = 0; yakCounter < nList.getLength(); yakCounter++) {

					Node nNode = nList.item(yakCounter);
					if (nNode.getNodeType() == Node.ELEMENT_NODE) {
						
						
						Element eElement = (Element) nNode;
						String name=eElement.getAttribute("name");
						String sex=eElement.getAttribute("sex");
						int age=0;
						try{
							double ageD=Double.parseDouble(eElement.getAttribute("age"));
							age=Double.valueOf(ageD*100).intValue();
						
						}catch(NumberFormatException ne){
							throw new RuntimeException("YAK-CONF-001: Invalid Age for the yak", ne);
						}
						yakList.add(new LabYak(yakCounter,name,age,sex));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Yak getYak(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Yak getYakByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Yak> getAllYaks() {
		// TODO Auto-generated method stub
		return yakList;
	}

	@Override
	public boolean consumeYakMilk(int id, int qty) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean shaveYak(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
