package com.foodorderingsys.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.foodorderingsys.custom.exception.ControllerException;
import com.foodorderingsys.custom.exception.ServiceException;
import com.foodorderingsys.dto.BuisnessUserOutDto;
import com.foodorderingsys.dto.CustomerDto;
import com.foodorderingsys.model.Admin;


import com.foodorderingsys.model.PlaceOrder;
import com.foodorderingsys.model.Restaurant;
import com.foodorderingsys.services.AdminServices;
import com.foodorderingsys.services.BuisnessServices;
import com.foodorderingsys.services.CustomerServices;
import com.foodorderingsys.services.PlaceOrderServices;
import com.foodorderingsys.services.RestaurantServices;

@RestController
@RequestMapping("/admin")
public class AdminController {
	static Logger logger = LogManager.getLogger(AdminController.class);
	@Autowired
	private AdminServices adminServices;
	@Autowired
	private CustomerServices customerServices;
	@Autowired
	private RestaurantServices restaurantServices;
	@Autowired
	private BuisnessServices buisnessServices;
	@Autowired
	private PlaceOrderServices placeOrderServices;

	@PostMapping("/signup")
	public ResponseEntity<?> adminSignup(@RequestBody Admin admin) {
		Admin adminobj = new Admin();
		try {
			logger.info("save the admin object to the database");
			adminobj = adminServices.addAdmin(admin);
		} catch (ServiceException e) {
			logger.error("email must be unique {}", admin.getEmail());
			logger.error("email must not be null");
			ControllerException controllerException = new ControllerException(e.getErrorcode(), e.getErrormessage());
			return new ResponseEntity<ControllerException>(controllerException, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			ControllerException ce = new ControllerException("701", "something went wrong in controller layer");
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
			
		}
		return new ResponseEntity<Admin>(adminobj, HttpStatus.CREATED);
	}

	@GetMapping("/getAllAdmin")
	public ResponseEntity<List<?>> getAllAdmin() {
		List<Admin> adminlist;
		try {
			logger.info("read all admins ");
			adminlist = adminServices.getAdmin();
		} catch (Exception e) {
			logger.error("no content");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

		return ResponseEntity.of(Optional.of(adminlist));

	}

	@GetMapping("/getAllCustomer")
	public ResponseEntity<List<CustomerDto>> getAllCustomer() {
		List<CustomerDto> listofcustomer;
		try {
			logger.info("read all customer ");
			listofcustomer = customerServices.getAllCustomerDto();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("no content");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		}
		if (listofcustomer.size() == 0) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.of(Optional.of(listofcustomer));

	}

	@DeleteMapping("/deleteCustomerById/{id}")
	public ResponseEntity<?> deleteCustomerById(@PathVariable("id") int id) {
		String msg;
		try {
			logger.info("delete customer by id ");
			msg = customerServices.deleteCustomerById(id);
		}catch (ServiceException e) {
			ControllerException controllerException = new ControllerException(e.getErrorcode(), e.getErrormessage());
			return new ResponseEntity<ControllerException>(controllerException, HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			logger.error("insert correct id");
			ControllerException ce = new ControllerException("701", "something went wrong in controller layer");
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
			
		}
		return new ResponseEntity<String>(msg,HttpStatus.OK);

	}

	@GetMapping("/readAllRestaurant")
	public ResponseEntity<List<Restaurant>> getAllRestaurants() {
		List<Restaurant> listofrest;
		try {
			logger.info("get all the restaurant");
			listofrest = restaurantServices.gellAllRestaurants();
		} catch (Exception e) {
			logger.error("no content");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		if (listofrest.size() <= 0) {
			logger.warn("list size is zero please put some data inside it");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

		} else {
			return ResponseEntity.of(Optional.of(listofrest));
		}
	}

	@GetMapping("/readAllBuisnessUserWithFullDetails")
	public ResponseEntity<List<BuisnessUserOutDto>> getBuisnessusers() {
		List<BuisnessUserOutDto> listofbuisnessusers;
		try {
			listofbuisnessusers = buisnessServices.getAllBuisnessUserOutDto();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		if (listofbuisnessusers.size() <= 0) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

		} else {
			return ResponseEntity.of(Optional.of(listofbuisnessusers));
		}

	}

	@GetMapping("/readOnlyBuisnessuser")
	public ResponseEntity<List<BuisnessUserOutDto>> getAllBuisnessUserDto() {
		List<BuisnessUserOutDto> list;
		try {
			logger.info("read only buisnessuser");
			list = buisnessServices.getAllBuisnessUserOutDto();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		if (list.size() == 0) {
			logger.warn("list size is zero please put some data inside it");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.of(Optional.of(list));
	}

	@GetMapping("/readOrderHistoryByCustomerid/{id}")
	public ResponseEntity<List<PlaceOrder>> getOrderDetailsByCustomerId(@PathVariable int id) {
		List<PlaceOrder> orderlist = null;
		try {
			logger.info("get the order history by customer_id");
			orderlist = placeOrderServices.getOrderDetailsByCustomerId(id);
		} catch (Exception e) {
			logger.error("please enter valid id ");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		if (orderlist.size() == 0) {
			logger.warn("list size is zero please put some data inside it");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.of(Optional.of(orderlist));
	}

}
