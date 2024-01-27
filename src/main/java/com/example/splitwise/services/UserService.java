package com.example.splitwise.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.splitwise.models.User;
import com.example.splitwise.repositories.UserRepository;

@Service
public class UserService {
	private UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public User createUser(String name, String password, String phone) {
		
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
       
		User user = new User(name, phone, bCryptPasswordEncoder.encode(password));
		
		userRepository.save(user);
		
		return user;
	}

	public User login(String phone, String password) {
		// TODO Auto-generated method stub
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	       
		Optional<User> userOp = userRepository.findByPhone(phone);
		if(userOp.get()==null) {
			return null;
		}
		User user = userOp.get();
		if(!bCryptPasswordEncoder.matches(password, user.getPassword())) {
			return null;
		}
		return user;
	}
	
}
