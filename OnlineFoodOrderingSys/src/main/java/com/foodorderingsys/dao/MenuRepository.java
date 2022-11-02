package com.foodorderingsys.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.foodorderingsys.model.Menu;

public interface MenuRepository extends JpaRepository<Menu,Integer>{
	
	
 @Query("select u from com.foodorderingsys.model.Menu u where u.restaurant_fk=:n")
 public List<Menu> getMenu(@Param("n") int id);

	
 

}
