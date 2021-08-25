package com.shopping.model;

public class Cart {
	private int orderid;
	private String tracker;
	
	private Customer customer;
	private Product product;
	@Override
	public String toString() {
		return "Cart [orderid=" + orderid + ", tracker=" + tracker + ", customer=" + customer + ", product=" + product
				+ "]";
	}
	public Cart(int orderid, String tracker, Customer customer, Product product) {
		super();
		this.orderid = orderid;
		this.tracker = tracker;
		this.customer = customer;
		this.product = product;
	}
	
	public Cart() {
		// TODO Auto-generated constructor stub
	}
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	public String getTracker() {
		return tracker;
	}
	public void setTracker(String tracker) {
		this.tracker = tracker;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	
	
}
