package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="project")
public class Project {

	@Id
	private Long id;
	@Column(name="project_name")
	private String projectName;
	@Column(name="dept_id")
	private Long deptId;
	public Project() {
		super();
	}
	public Project(Long id, String projectName, Long deptId) {
		super();
		this.id = id;
		this.projectName = projectName;
		this.deptId = deptId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public Long getDeptId() {
		return deptId;
	}
	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}
}
