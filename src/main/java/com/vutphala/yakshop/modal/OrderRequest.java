package com.vutphala.yakshop.modal;

import java.io.Serializable;

/**
 * @author srinath
 * 
 */
public class OrderRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1246922702283144748L;
	private String customer;

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	private Order order;
}
