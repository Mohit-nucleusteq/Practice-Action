package com.foodorderingsys.controller.test;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodorderingsys.model.Customer;
import com.foodorderingsys.services.CustomerServices;

@WebMvcTest
public class CustomerControllerTest {
	@MockBean
	CustomerServices customerService;
	@Autowired
	private MockMvc mockMvc;

	ObjectMapper objectMapper = new ObjectMapper();

	@Test
	public void addEmployeeTest() throws Exception {
		Customer customer = new Customer();
		customer.setName("Basant");
		customer.setEmail("IT@gmail.com");
		customer.setAge(21);
		customer.setContactNo(75092055721l);
		customer.setId(121);
		Mockito.when(customerService.customerSignup(ArgumentMatchers.any()))
				.thenReturn("Added SuccessFully ! Welcome to Food Order System");
		String json = objectMapper.writeValueAsString(customer);
		mockMvc.perform(post("/customer/signup").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
				.content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}
}
