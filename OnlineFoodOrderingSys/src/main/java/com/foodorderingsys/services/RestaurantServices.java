package com.foodorderingsys.services;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import com.foodorderingsys.model.Restaurant;
import com.foodorderingsys.custom.exception.ServiceException;
import com.foodorderingsys.dao.RestaurantsRepository;
import com.foodorderingsys.dto.AddMenuDto;

@Service
public class RestaurantServices {
	@Autowired
	private RestaurantsRepository restaurantRepository;

	public List<Restaurant> gellAllRestaurants() {
		List<Restaurant> listofrest = new ArrayList<Restaurant>();
		try {
			listofrest = restaurantRepository.findAll();
			if (listofrest.isEmpty() == true || listofrest.size() == 0) {
				throw new ServiceException("628", "List of restaurant is null,first you need to insert the restaurant");
			}
		} catch (Exception e) {

			throw new ServiceException("603", "something went wrong in service layer");
		}
		return listofrest;

	}

	public Restaurant addRestaurantWithMenu(AddMenuDto menuitems) {
		Restaurant restaurant = null;
		restaurant = restaurantRepository.save(menuitems.getRestaurant());
		return restaurant;
	}

}
