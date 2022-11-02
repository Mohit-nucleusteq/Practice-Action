package com.foodorderingsys.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Pattern.Flag;
@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name = "name", nullable = false, length = 50)
	private String name;
	@Column(name = "age", nullable = false, length = 10)
	private int age;
	@Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", flags = Pattern.Flag.CASE_INSENSITIVE)
	private String email;
	@Column(name = "gender", length = 10, nullable = false)
	@Enumerated(EnumType.STRING)
	private Gender gender;
	@Column(name = "contactNo", length = 10)
	private long contactNo;
	@Column(name = "password", length = 20)
	private String password;
	@OneToMany(targetEntity = PlaceOrder.class, cascade = CascadeType.ALL)
	private List<PlaceOrder> orderlist;

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
  public Customer(int id,String name,Gender gender,long contactNo) {
	  super();
	  this.id=id;
	  this.name=name;
	  this.contactNo=contactNo;
	  this.gender=gender;
  }
	public Customer(int id, String name, int age,
			@Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", flags = Flag.CASE_INSENSITIVE) String email,
			Gender gender, long contactNo, String password, List<PlaceOrder> orderlist) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.email = email;
		this.gender = gender;
		this.contactNo = contactNo;
		this.password = password;
		this.orderlist = orderlist;
	}
	public Customer(int id, String name, int age,
			@Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", flags = Flag.CASE_INSENSITIVE) String email,
			Gender gender, long contactNo, String password) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.email = email;
		this.gender = gender;
		this.contactNo = contactNo;
		this.password = password;
	}
	

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", age=" + age + ", email=" + email + ", gender=" + gender
				+ ", contactNo=" + contactNo + ", password=" + password + "]";
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<PlaceOrder> getOrderlist() {
		return orderlist;
	}

	public void setOrderlist(List<PlaceOrder> orderlist) {
		this.orderlist = orderlist;
	}

}