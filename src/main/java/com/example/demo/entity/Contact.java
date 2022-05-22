package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="contact")
public class Contact {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "email", nullable = false)
    private String email;
	
	@Column(name = "phone", nullable = false)
    private Long phone;
	
	@Column(name = "address", nullable = false)
    private String address;
	
	@Column(name = "employee_id", nullable = false)
    private Long employeeId;

	public Contact() {
		super();
	}

	public Contact(Long id, String email, Long phone, String address, Long employeeId) {
		super();
		this.id = id;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.employeeId = employeeId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}
	
}
