package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;
import com.example.demo.exception.NoDataFoundException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepository empRepo;

	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		List<Employee> employees = (List<Employee>) empRepo.findAll();
//	    if(employees.size()==0)
//	    	throw new NoDataFoundException("No employees available");
	    return employees;
	}

	public Employee getEmployeeById(long employeeId) {
		// TODO Auto-generated method stub
		Employee emp = empRepo.findById(employeeId).get();
//		if(emp==null)
//	    	throw new ResourceNotFoundException("Employee not found for the id -> " + employeeId);
//	    else
	        return emp;
	}
	
	public void deleteEmployee(long id) {
		// TODO Auto-generated method stub
		empRepo.deleteById(id);
	}

	public Employee save(Employee employee) {
		// TODO Auto-generated method stub
		return empRepo.save(employee);
	}
	
	public Page<Employee> listAll(int pageNum, String sortField, String sortDir) {
	    int pageSize = 3;
	    Pageable pageable = PageRequest.of(pageNum - 1, pageSize, 
	    		sortDir.equals("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending()
	    );	     
	    return empRepo.findAll(pageable);
	}
	
}