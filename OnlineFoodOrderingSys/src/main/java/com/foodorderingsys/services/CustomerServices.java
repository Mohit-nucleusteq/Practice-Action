package com.foodorderingsys.services;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.foodorderingsys.custom.exception.ServiceException;
import com.foodorderingsys.dao.CustomerRepository;
import com.foodorderingsys.dto.CustomerDto;
import com.foodorderingsys.model.Customer;
import com.foodorderingsys.model.Restaurant;

@Service
public class CustomerServices {
	@Autowired
	private CustomerRepository customerRepo;

	public CustomerServices(CustomerRepository customerRepo) {
		super();
		this.customerRepo = customerRepo;
	}

	public List<CustomerDto> getAllCustomerDto() {
		List<CustomerDto> customerlist = this.customerRepo.findAll().stream().map(this::converCustomerEntityToDto)
				.collect(Collectors.toList());
		return customerlist;
	}

	public CustomerDto converCustomerEntityToDto(Customer customer) {
		CustomerDto customerDto = new CustomerDto();
		customerDto.setId(customer.getId());
		customerDto.setName(customer.getName());
		customerDto.setGender(customer.getGender());
		customerDto.setContactNo(customer.getContactNo());
		return customerDto;
	}

	public String customerSignup(Customer customer) {

		if (!Pattern.matches("^[a-z\\sA-Z]{4,16}", customer.getName())) {
			return "name is below 4 character or you have entered digit into it";
		}

		else if (!Pattern.matches("^[0-9]{10}", Long.toString(customer.getContactNo()))) {
			return "Mobile number should be of 10 digit";
		} else
		// (?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[*.!@$%^&(){}[]:;<>,.?/~_+-=|\\]).{8,32}$
		if (!Pattern.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()]).{8,20}$", customer.getPassword())) {
			return "password is weak";
		}
		try {
			customerRepo.save(customer);
		} catch (IllegalArgumentException e) {
			throw new ServiceException("608", "customer info must not be null");

		} catch (Exception e) {
			throw new ServiceException("603", "Something went wrong in service layer");
		}

		return "Added SuccessFully ! Welcome to Food Order System  " + customer.getName();
	}

	public String deleteCustomerById(int id) {
		try {
			customerRepo.deleteById(id);

		} catch (IllegalArgumentException e) {
			throw new ServiceException("609", "customer id is null please send some id");

		} catch (Exception e) {
			throw new ServiceException("610",
					"your id is not in the database put your correct id or Something went wrong in service layer");
		}

		return "Deleted data Successfully";
	}

	public String customerLogin(String name, String password) {
		int result;
		try {
			result = customerRepo.login(name, password);
			if (result > 0) {
				return "Successfully login";
			} else {
				return "first you need to sign up";
			}
		} catch (IllegalArgumentException e) {
			throw new ServiceException("615", "argument is null please send name and password");
		} catch (Exception e) {
			throw new ServiceException("614",
					"name and password not exist in the database first you need to signup");
		}

	}

	public List<Restaurant> searchRestaurants(String name) {
		List<Restaurant> restaurants = new ArrayList<Restaurant>();
		try {
			restaurants = customerRepo.searchRestaurants(name);
			if (restaurants.size() == 0) {
				throw new ServiceException("611", "did not have these restaurant in database");
			}
		} catch (Exception e) {
			throw new ServiceException("603", "Something went wrong in service layer");
		}
		return restaurants;

	}

	public Customer placeOrder(Customer request) {
		Customer customer = null;
		try {
			customer = customerRepo.save(request);
		} catch (IllegalArgumentException e) {
			throw new ServiceException("613", "Entity Must Not Be Null");

		} catch (Exception e) {
			throw new ServiceException("603", "Something went wrong in service layer");

		}
		return customer;
	}

}
