package com.foodorderingsys.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodorderingsys.model.Admin;

public interface AdminRepository extends JpaRepository<Admin,Integer> {

}
