package com.vutphala.yakshop.web.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.http.HttpServletResponse;
import javax.xml.rpc.Call;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vutphala.yakshop.business.service.HeardService;
import com.vutphala.yakshop.business.service.impl.HeardServiceImpl;
import com.vutphala.yakshop.modal.HeardInfo;
import com.vutphala.yakshop.modal.OrderRequest;
import com.vutphala.yakshop.modal.YakInstance;

@Controller
@RequestMapping("/")
public class YakShopController {

	@Autowired
	private HeardService heardService;

	@RequestMapping(value = "/heard/{day}", method = RequestMethod.GET)
	@ResponseBody
	public void getHeard(@PathVariable Integer day, HttpServletResponse response) {

		HeardInfo info = heardService.getHeardDetailsForTheDay(day);
		JSONArray yaks = new JSONArray();
		try {
			for (YakInstance yak : info.getYaks()) {
				JSONObject json = new JSONObject();
				json.put("name", yak.getName());
				json.put("age", yak.getAge());
				json.put("age-last-shaved", yak.getAgeLastShaved());
				yaks.put(json);
			}
			JSONObject heard = new JSONObject();
			heard.put("heard", yaks);
			response.setStatus(HttpServletResponse.SC_OK);
			response.setContentType("");
			response.getOutputStream().write(heard.toString().getBytes());
			response.getOutputStream().flush();

		} catch (IOException e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				response.getOutputStream().close();
			} catch (IOException e) {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	@RequestMapping(value = "/heard", method = RequestMethod.GET)
	@ResponseBody
	public void getHeard(HttpServletResponse response) {

		getHeard(0, response);

	}

	@RequestMapping(value = "/stock/{day}", method = RequestMethod.GET)
	@ResponseBody
	public void getStock(@PathVariable Integer day, HttpServletResponse response) {
		HeardInfo info = heardService.getHeardDetailsForTheDay(day);

		JSONArray yaks = new JSONArray();
		try {
			JSONObject stock = new JSONObject();
			stock.put("milk", info.getMilkStock());
			stock.put("skins", info.getWoolStock());

			response.setStatus(HttpServletResponse.SC_OK);
			response.setContentType("application/json");
			response.getOutputStream().write(stock.toString().getBytes());
			response.getOutputStream().flush();

		} catch (IOException e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				response.getOutputStream().close();
			} catch (IOException e) {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	@RequestMapping(value = "/stock", method = RequestMethod.GET)
	@ResponseBody
	public void getStock(HttpServletResponse response) {
		getStock(0, response);

	}

	@RequestMapping(value = "/order/{day}", method = RequestMethod.POST)
	@ResponseBody
	public String checkOrder(@PathVariable Integer day,
			@RequestBody OrderRequest orderRequest, HttpServletResponse response) {

		HeardInfo info = heardService.getHeardDetailsForTheDay(day);
		JSONArray yaks = new JSONArray();
		try {
			
			boolean milkAvailable=false;
			boolean skinAvailable=false;
			if(orderRequest!=null && orderRequest.getOrder()!=null){
				if(orderRequest.getOrder().getMilk()<=info.getMilkStock()){
					milkAvailable=true;
				}
				if(orderRequest.getOrder().getSkins()<=info.getWoolStock()){
					skinAvailable=true;
				}
			}
			if(!milkAvailable && !skinAvailable){
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
				response.setContentType("");
				response.getOutputStream().write(" STOCK NOT AVAILABLE".getBytes());
				response.getOutputStream().flush();
			}else if(milkAvailable && !skinAvailable){
				response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
				response.setContentType("application/json");
				JSONObject stock = new JSONObject();
				stock.put("milk", orderRequest.getOrder().getMilk());
				response.getOutputStream().write(stock.toString().getBytes());
				response.getOutputStream().flush();
			}else if(!milkAvailable && skinAvailable){
				response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
				response.setContentType("application/json");
				JSONObject stock = new JSONObject();
				stock.put("skins", orderRequest.getOrder().getSkins());
				response.getOutputStream().write(stock.toString().getBytes());
				response.getOutputStream().flush();
			}else{
				response.setStatus(HttpServletResponse.SC_CREATED);
				response.setContentType("application/json");
				JSONObject stock = new JSONObject();
				stock.put("milk", orderRequest.getOrder().getMilk());
				stock.put("skins", orderRequest.getOrder().getSkins());
				response.getOutputStream().write(stock.toString().getBytes());
				response.getOutputStream().flush();
			}
		} catch (IOException e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				response.getOutputStream().close();
			} catch (IOException e) {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return "";
	}

	@RequestMapping(value = "/stock/calendar", method = RequestMethod.GET)
	@ResponseBody
	public String stockCalendar(@RequestParam("start") String paramStartDate,
			@RequestParam("end") String paramEndDateValue,
			HttpServletResponse response) {

		Calendar startDate = Calendar.getInstance();
		Calendar endDate = Calendar.getInstance();
		try {
			startDate.setTimeInMillis(Long.parseLong(paramStartDate) * 1000);
			endDate.setTimeInMillis(Long.parseLong(paramEndDateValue) * 1000);
		} catch (Exception e) {
			// will be sending only today data
		}

		int startDays = Days.daysBetween(new DateTime(new Date()),
				new DateTime(startDate.getTimeInMillis())).getDays();
		int endDays = Days.daysBetween(new DateTime(new Date()),
				new DateTime(endDate.getTimeInMillis())).getDays();
		int noOfDays = 0;
		DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd");

		try {
			JSONArray events = new JSONArray();
			if (startDays <=0 && endDays > 0) {
				
				for (int calCounter = 0; calCounter <= endDays; calCounter++) {
					HeardInfo info = heardService
							.getHeardDetailsForTheDay(calCounter);
					JSONObject evendata = new JSONObject();

					evendata.put("title", "Milk :" + info.getMilkStock()
							+ "  Skin : " + info.getWoolStock());
					evendata.put("start", (fmt.print(new DateTime(new Date())
							.plusDays(calCounter))));
					events.put(evendata);
				}

				
			} else if (startDays > 0 && endDays > 0) {

				// noOfDays=noOfDays+startDays;
				for (int calCounter = startDays; calCounter <= endDays; calCounter++) {
					HeardInfo info = heardService
							.getHeardDetailsForTheDay(calCounter);
					JSONObject evendata = new JSONObject();

					evendata.put("title", "Milk -" + info.getMilkStock()
							+ " - Skin - " + info.getWoolStock());
					evendata.put("start", (fmt.print(new DateTime(new Date())
							.plusDays(calCounter))));
					events.put(evendata);
				}
			}
			System.out.println(" start " + startDate.getTime());
			System.out.println(" end " + endDate.getTime());

			response.setStatus(HttpServletResponse.SC_OK);
			response.setContentType("application/json");
			
			response.getOutputStream().write(events.toString().getBytes());
			response.getOutputStream().flush();
		} catch (IOException e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				response.getOutputStream().close();
			} catch (IOException e) {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return "";
	}
}
