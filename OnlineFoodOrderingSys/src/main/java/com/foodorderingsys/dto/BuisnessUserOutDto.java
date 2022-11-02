package com.foodorderingsys.dto;



public class BuisnessUserOutDto {
	
	private int id;
	private String name;
	private int r_id;
	
	public int getR_id() {
		return r_id;
	}
	public void setR_id(int r_id) {
		this.r_id = r_id;
	}
	public BuisnessUserOutDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BuisnessUserOutDto(int id, String name) {
		super();
		this.id = id;
		this.name = name;
		
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

}
