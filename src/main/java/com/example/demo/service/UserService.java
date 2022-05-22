package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepo;

	public void save(User user) {
		// TODO Auto-generated method stub
		userRepo.save(user);
	}

	public User validateUser(String username, String password) {
		// TODO Auto-generated method stub
		User user = userRepo.findByUsernameAndPassword(username, password);
		return user;
	}

	public User validateUsername(String username) {
		// TODO Auto-generated method stub
		User user = userRepo.findByUsername(username);
		return user;
	}
}
