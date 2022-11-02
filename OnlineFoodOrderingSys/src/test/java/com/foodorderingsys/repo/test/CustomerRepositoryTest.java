package com.foodorderingsys.repo.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.foodorderingsys.dao.CustomerRepository;
import com.foodorderingsys.dao.RestaurantsRepository;
import com.foodorderingsys.model.Customer;
import com.foodorderingsys.model.Gender;
import com.foodorderingsys.model.Restaurant;
import com.foodorderingsys.services.RestaurantServices;

@SpringBootTest
class CustomerRepositoryTest {
   @MockBean
	RestaurantsRepository restaurantsRepository;
	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	RestaurantServices restaurantServices;
	
	@Test
	void searchRestaurantsTest(){
		ArrayList<Restaurant> list=(ArrayList<Restaurant>) customerRepository.searchRestaurants("sagar gaire");
		assertThat(list.size()).isGreaterThan(0);
	}
	@Test
	void  loginTest() {
		Customer customer=new Customer(15, "mrtn", 21, "mohit12@gmail.com", Gender.Male, 8109761681l, "mohit123A@");
		customerRepository.save(customer);
		int res=customerRepository.login("mrtn","mohit123A@");
		assertThat(res).isGreaterThan(0);
	}
}
