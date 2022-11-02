package com.foodorderingsys.dto;

import com.foodorderingsys.model.Restaurant;

public class AddMenuDto {
	private Restaurant restaurant;

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public AddMenuDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AddMenuDto(Restaurant restaurant) {
		super();
		this.restaurant = restaurant;
	}
	
}
