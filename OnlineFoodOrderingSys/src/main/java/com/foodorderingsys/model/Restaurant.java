package com.foodorderingsys.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "restaurant")
public class Restaurant {
	@Id
	//@GeneratedValue (strategy = GenerationType.AUTO)
	private int id;
	@Column(name = "name")
	private String name;
	@Column(name = "location")
	private String location;
	@OneToMany (targetEntity =Menu.class ,cascade=CascadeType.ALL)
	@JoinColumn(name="restaurant_fk",referencedColumnName = "id")
	private List<Menu> menu;

	public Restaurant() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<Menu> getMenu() {
		return menu;
	}

	public void setMenu(List<Menu> menu) {
		this.menu = menu;
	}

	public Restaurant(int id, String name, String location) {
		super();
		this.id = id;
		this.name = name;
		this.location = location;
		
		
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}



}
