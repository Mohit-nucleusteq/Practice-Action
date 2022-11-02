package com.foodorderingsys.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "buisnessuser")
public class BuisnessUser {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "b_id")
	private int id;
	@Column(name = "name")
	private String name;
	@Column(name = "password")
	private String password;
	@Column(name="email",nullable =false,unique = true)
	 @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
     flags = Pattern.Flag.CASE_INSENSITIVE)
	private String email;
	private long contactno;
	public long getContactno() {
		return contactno;
	}

	public void setContactno(long contactno) {
		this.contactno = contactno;
	}

	// @Column(name="restaurants_id")
	@OneToOne(cascade = CascadeType.ALL)
	private Restaurant restaurants;

	public BuisnessUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Buisness [id=" + id + ", name=" + name + ", password=" + password + ", restaurants=" + restaurants
				+ "]";
	}

	public BuisnessUser(int id, String name, String password, Restaurant restaurants) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.restaurants = restaurants;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Restaurant getRestaurants() {
		return restaurants;
	}

	public void setRestaurants(Restaurant restaurants) {
		this.restaurants = restaurants;
	}

}
