package com.foodorderingsys.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.foodorderingsys.dao.PlaceOrderRepository;
import com.foodorderingsys.model.PlaceOrder;

@Service
public class PlaceOrderServices {

	@Autowired
	PlaceOrderRepository plcaeOrderRepository;

	public List<PlaceOrder> getOrderDetailsByCustomerId(int id) {
		List<PlaceOrder> orderlist = plcaeOrderRepository.getOrderHistoryByCustomerById(id);
		return orderlist;
	}
	
	public List<PlaceOrder> getOrderDetailsByRestaurantId(int id) {
		List<PlaceOrder> orderlist = plcaeOrderRepository.getOrderHistorRestaurantById(id);
		return orderlist;
	}
	
	public String update(int id,PlaceOrder placeorder) {
		placeorder.setOrder_id(id);
		plcaeOrderRepository.save(placeorder);
		return "Order Placed ";
	}
	
}
