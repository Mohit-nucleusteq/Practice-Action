package com.foodorderingsys.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.foodorderingsys.model.PlaceOrder;

public interface PlaceOrderRepository extends JpaRepository<PlaceOrder, Integer> {

	// for admin
	
	  @Query("select u from PlaceOrder u where u.customerid=:n") public
	  List<PlaceOrder> getOrderHistoryByCustomerById(@Param("n") int id);
	  
	  // for buisnessuser
	  
	  @Query("select u from PlaceOrder u where u.restaurant_id=:n") public
	  List<PlaceOrder> getOrderHistorRestaurantById(@Param("n") int id);
	 
}
