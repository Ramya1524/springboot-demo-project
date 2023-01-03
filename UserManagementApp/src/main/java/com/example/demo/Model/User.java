package com.example.demo.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class User 
{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(unique=true)
	@NotNull(message="Username cannot be empty")
	private String username;
	
	@Column
	@NotNull(message="Password cannot be empty")
	private String password;
	
	@Column
	@NotNull(message="Address cannot be empty")
	private String address;
	
	@Column
	@NotNull(message="Email cannot be empty")
	private String email;
	
	@Column
	@NotNull(message="Phone number cannot be empty")
	private Long phone;
	
	@Column
	@NotNull(message="Name cannot be empty")
	private String name;
	
	@Column
	@NotNull(message="PAN Number cannot be empty")
	private String pan;
	
	@Column
	@NotNull(message="Account Type cannot be empty")
	private String type;
	
	@Column
	@NotNull(message="state cannot be empty")
	private String state;
	
	@Column
	@NotNull(message="Country cannot be empty")
	private String country;
	
	@Column
	@NotNull(message="Date of birth cannot be empty")
	private String dob;
	
	
	public User(int id, String username, String password, String address, String email, Long phone, String name,
			String pan, String type, String state, String country, String dob) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.address = address;
		this.email = email;
		this.phone = phone;
		this.name = name;
		this.pan = pan;
		this.type = type;
		this.state = state;
		this.country = country;
		this.dob = dob;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	
	
	public int getId() {
		return id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getPhone() {
		return phone;
	}
	public void setPhone(Long phone) {
		this.phone = phone;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
