package com.foodorderingsys.services.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.foodorderingsys.dao.CustomerRepository;
import com.foodorderingsys.model.Admin;
import com.foodorderingsys.model.Customer;
import com.foodorderingsys.model.Gender;
import com.foodorderingsys.model.Restaurant;
import com.foodorderingsys.services.CustomerServices;

@RunWith(SpringRunner.class)
@SpringBootTest
class CustomerServicesTest {

	@MockBean
	CustomerRepository customerRepository;
	@Autowired
	CustomerServices customerServices;

	@Test
	void getAllCustomerTest() {

		when(customerRepository.findAll()).thenReturn(
				Stream.of(new Customer(11, "arun sharma", Gender.Male, 760920111)).collect(Collectors.toList()));
		assertEquals(1, customerServices.getAllCustomerDto().size());
	}

	@Test
	void customerSignupTest() {
		Customer customer = new Customer(15, "mrtn", 21, "mohit12@gmail.com", Gender.Male, 8109761681l, "mohit123A@");
		when(customerRepository.save(customer)).thenReturn(customer);
		assertEquals("Added SuccessFully ! Welcome to Food Order System  " + customer.getName(),
				customerServices.customerSignup(customer));
	}

	@Test
	void searchRestaurantsTest() {
		when(customerRepository.searchRestaurants("sagar gaire"))
				.thenReturn(Stream.of(new Restaurant(112, "sagar gaire", "Bhopal")).collect(Collectors.toList()));
		assertEquals(1, customerServices.searchRestaurants("sagar gaire").size());
	}

	@Test
	void customerLoginTest() {
		when(customerRepository.login("Mrtn", "mohit123A@")).thenReturn(1);
		assertEquals("Successfully login", customerServices.customerLogin("Mrtn", "mohit123A@"));
	}

	@Test
	void deleteCustomerByIdTest() {
		Customer customer=new Customer(15, "mrtn", 21, "mohit12@gmail.com", Gender.Male, 8109761681l, "mohit123A@");
		customerRepository.save(customer);
		customerRepository.deleteById(15);
		verify(customerRepository,times(1)).deleteById(15);
		
	}


	

}
