package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="department")
public class Department {

	@Id
    private Long id;
	@Column(name="dept_name")
	private String deptName;
	@Column(name="dept_head")
	private Long deptHead;
	public Department() {
		super();
	}
	public Department(Long id, String deptName, Long deptHead) {
		super();
		this.id = id;
		this.deptName = deptName;
		this.deptHead = deptHead;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public Long getDeptHead() {
		return deptHead;
	}
	public void setDeptHead(Long deptHead) {
		this.deptHead = deptHead;
	}
    
}
