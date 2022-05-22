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
@Table(name="employee")
public class Employee {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;
	
	@Column(name = "name", nullable = false)
    private String name;

    @Column(name = "salary", nullable = false)
    private Long salary;

    @Column(name = "designation", nullable = false)
    private String designation;
    
    @Column(name = "dept_id", nullable = false)
    private Long deptId;
    
    @Column(name = "project_id")
    private Long projectId;
	
	public Employee() {
		super();
	}

	public Employee(Long employeeId, String name, Long salary, String designation, Long deptId, Long projectId) {
		super();
		this.employeeId = employeeId;
		this.name = name;
		this.salary = salary;
		this.designation = designation;
		this.deptId = deptId;
		this.projectId = projectId;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getSalary() {
		return salary;
	}

	public void setSalary(Long salary) {
		this.salary = salary;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	
}
