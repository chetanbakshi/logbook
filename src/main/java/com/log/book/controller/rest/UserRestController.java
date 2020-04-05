package com.log.book.controller.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.log.book.dto.UserDto;
import com.log.book.service.UserService;
import com.log.book.shared.message.SuccessMessage;
import com.log.book.ui.model.request.SignUpRequestModel;
import com.log.book.ui.model.response.SuccessResponseModel;

@RestController
public class UserRestController {
	
	@Autowired
	UserService userService;

	@PostMapping(value="/users/sign-up")
	private ResponseEntity<Object> signUp(@RequestBody SignUpRequestModel user) {
		
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(user, userDto);
		userService.createUser(userDto);
		SuccessResponseModel body = new SuccessResponseModel(SuccessMessage.CREATE.getMessage(), 201, HttpStatus.CREATED);
		return new ResponseEntity<Object>(body, HttpStatus.CREATED);		
	}
	
	@GetMapping
	private List<UserDto> getUsers() {
		return userService.userList();	
	}
	
	@GetMapping(value="/manager/users")
	private String managerUsers() {
		return "User for manager";
	}
	
	@GetMapping(value="/admin/users")
	private String adminUsers() {
		return "User for admin";
	}
	
}
