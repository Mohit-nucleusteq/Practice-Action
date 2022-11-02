package com.foodorderingsys.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.foodorderingsys.model.Customer;
import com.foodorderingsys.model.Restaurant;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	@Query("Select u.id from Customer u where u.name=:n and u.password=:p")
	public int login(@Param("n") String name, @Param("p") String password);
    
	@Query("Select u from Restaurant u where u.name=:n")
	public List<Restaurant> searchRestaurants(@Param("n") String name);

}
