package com.foodorderingsys.dao;

import org.springframework.data.jpa.repository.JpaRepository;


import com.foodorderingsys.model.Restaurant;

public interface RestaurantsRepository extends JpaRepository<Restaurant, Integer> {

}
