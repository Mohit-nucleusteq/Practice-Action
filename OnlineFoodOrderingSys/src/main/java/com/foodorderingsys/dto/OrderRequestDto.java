package com.foodorderingsys.dto;

import com.foodorderingsys.model.Customer;

public class OrderRequestDto {
    private Customer customer;

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public OrderRequestDto(Customer customer) {
		super();
		this.customer = customer;
	}

	public OrderRequestDto() {
		super();
		// TODO Auto-generated constructor stub
	}
    
}
