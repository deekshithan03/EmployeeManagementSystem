package com.example.demo.repository;

import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    
    Page<Employee> findByName(String name, Pageable pageable);
    
    Page<Employee> findByDesignation(String designation, Pageable pageable);
    
    Page<Employee> findBySalary(Long salary, Pageable pageable);
    
    Page<Employee> findByNameAndDesignation(String name, String Designation, Pageable pageable);
    
    Page<Employee> findByDeptId(Long deptId, Pageable pageable);
    
}