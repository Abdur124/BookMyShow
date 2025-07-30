package com.lld.bms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lld.bms.exceptions.UserNotFoundException;
import com.lld.bms.models.User;
import com.lld.bms.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User saveUser(User User) {
		return userRepository.save(User);
	}
	
	public List<User> getUsers() {
		return userRepository.findAll();
	}
	
	public User getUserById(int UserId) {
		
		return userRepository.findById(UserId).orElseThrow(() -> new UserNotFoundException("User with id " +UserId+ " not found"));
	}
	
	public void deleteUserById(int UserId) {
		userRepository.deleteById(UserId);
	}

}
