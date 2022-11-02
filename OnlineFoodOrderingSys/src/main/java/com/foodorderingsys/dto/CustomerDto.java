package com.foodorderingsys.dto;



import com.foodorderingsys.model.Gender;


public class CustomerDto {
	
	private int id;
	private Gender gender;

    private long contactNo;
    private String name;

    
    public CustomerDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CustomerDto(int id, String name, Gender gender, long contactNo) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.contactNo = contactNo;
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

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public long getContactNo() {
		return contactNo;
	}

	public void setContactNo(long contactNo) {
		this.contactNo = contactNo;
	}


    
	
}