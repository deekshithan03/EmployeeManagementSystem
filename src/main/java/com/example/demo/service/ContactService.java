package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Contact;
import com.example.demo.repository.ContactRepository;

@Service
public class ContactService {

	@Autowired
	ContactRepository contactRepo;
	public Contact getContactByEmployeeId(long id) {
		// TODO Auto-generated method stub
		Contact emp = contactRepo.findByEmployeeId(id);
		return emp;
	}
	
	public Contact save(Contact contact) {
		// TODO Auto-generated method stub
		return contactRepo.save(contact);
	}


}
