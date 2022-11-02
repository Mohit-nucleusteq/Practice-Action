package com.foodorderingsys.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.validation.constraints.Email;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Pattern.Flag;



@Entity
public class Admin {
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	 @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
	            flags = Pattern.Flag.CASE_INSENSITIVE)
	private String email;
	private String name;
	private String password;
	@Column(name="contactno",unique=true,nullable = true)
	private long contactno;
	public long getContactno() {
		return contactno;
	}

	public void setContactno(long contactno) {
		this.contactno = contactno;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	@Override
	public String toString() {
		return "Admin [id=" + id + ", email=" + email + ", name=" + name + ", password=" + password + ", contactno="
				+ contactno + "]";
	}



	public Admin(int id,
			@Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", flags = Flag.CASE_INSENSITIVE) String email,
			String name, String password, long contactno) {
		super();
		this.id = id;
		this.email = email;
		this.name = name;
		this.password = password;
		this.contactno = contactno;
	}

	public Admin(int id,String name, String password) {
		super();
		this.id=id;
		this.name=name;
		this.password=password;
		
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
}
