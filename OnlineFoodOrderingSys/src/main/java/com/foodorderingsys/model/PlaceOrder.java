package com.foodorderingsys.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PlaceOrder {
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	@Column(name="order_id")
	private int order_id;
	private  String items;
	private int quantitiy;
	@Column(name="restaurant_id")
	private int restaurant_id;
	private Date date;
	private String status;
	private int customerid;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	public int getCustomerid() {
		return customerid;
	}
	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public String getItems() {
		return items;
	}
	public void setItems(String items) {
		this.items = items;
	}
	public int getQuantitiy() {
		return quantitiy;
	}
	public void setQuantitiy(int quantitiy) {
		this.quantitiy = quantitiy;
	}
	public int getRestaurant_id() {
		return restaurant_id;
	}
	public void setRestaurant_id(int restaurant_id) {
		this.restaurant_id = restaurant_id;
	}
	public PlaceOrder(int order_id, String items, int quantitiy, int restaurant_id) {
		super();
		this.order_id = order_id;
		this.items = items;
		this.quantitiy = quantitiy;
		this.restaurant_id = restaurant_id;
	}
	public PlaceOrder() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}