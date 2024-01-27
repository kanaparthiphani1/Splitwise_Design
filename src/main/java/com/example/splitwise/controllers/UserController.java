package com.example.splitwise.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.splitwise.dtos.CreateUserRequestDTO;
import com.example.splitwise.dtos.CreateUserResponseDTO;
import com.example.splitwise.models.User;
import com.example.splitwise.services.UserService;

@Controller
public class UserController {
	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	public CreateUserResponseDTO createUser(CreateUserRequestDTO req) {
		
		User user =  userService.createUser(req.getName(), req.getPassword(),req.getPhone());
		
		return new CreateUserResponseDTO(user.getId());
	}
	
	public User login(String phone, String password) {
		User user = userService.login(phone,password);
		user.setPassword(null);
		return user;
	}
	
}
