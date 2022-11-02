package com.foodorderingsys.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodorderingsys.custom.exception.ControllerException;
import com.foodorderingsys.custom.exception.ServiceException;
import com.foodorderingsys.model.Customer;

import com.foodorderingsys.model.Restaurant;
import com.foodorderingsys.services.CustomerServices;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	private CustomerServices customerServices;
	static Logger logger = LogManager.getLogger(CustomerController.class);

	@PostMapping("/signup")
	public ResponseEntity<?> customerSignup(@RequestBody Customer customer) {
		String msg = null;
		try {
			logger.info("customer signup");
			msg = customerServices.customerSignup(customer);
		} catch (ServiceException e) {
			logger.error("customer entity must not be null");
			ControllerException controllerException = new ControllerException(e.getErrorcode(), e.getErrormessage());
			return new ResponseEntity<ControllerException>(controllerException, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error("pass valid arguments");
			ControllerException ce = new ControllerException("701", "something went wrong in controller layer");
			return new ResponseEntity<ControllerException>(ce, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return new ResponseEntity<>(HttpStatus.OK);

	}

	@GetMapping("/login/{name}/{password}")
	public ResponseEntity<?> customerLogin(@PathVariable String name, @PathVariable String password) {
		String msg = null;
		try {
			logger.info("customer login pass name and password");
			msg = customerServices.customerLogin(name, password);
		} catch (ServiceException e) {
			logger.error("not find anything related to that username and password");
			ControllerException controllerException = new ControllerException(e.getErrorcode(), e.getErrormessage());
			return new ResponseEntity<ControllerException>(controllerException, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			ControllerException ce = new ControllerException("703", "INVALID USERNAME OR PASSWORD");
			return new ResponseEntity<ControllerException>(ce, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

	@GetMapping("/searchRestaurant/{name}")
	public ResponseEntity<List<Restaurant>> searchRestaurant(@PathVariable String name) {
		List<Restaurant> restaurants = new ArrayList<Restaurant>();
		restaurants = customerServices.searchRestaurants(name);
		return ResponseEntity.of(Optional.of(restaurants));
	}

	@PostMapping("/placeOrder")
	public ResponseEntity<?> placeOrder(@RequestBody Customer request) {
		Customer customer = new Customer();
		try {
			logger.info("placev the order ");
			customer = customerServices.placeOrder(request);
		} catch (ServiceException e) {
			ControllerException controllerException = new ControllerException(e.getErrorcode(), e.getErrormessage());
			return new ResponseEntity<ControllerException>(controllerException, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			ControllerException ce = new ControllerException("701", "something went wrong in controller layer");
			return new ResponseEntity<ControllerException>(ce, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return new ResponseEntity<Customer>(customer, HttpStatus.CREATED);
	}

}
