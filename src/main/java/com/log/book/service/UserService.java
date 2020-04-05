package com.log.book.service;

import java.util.List;

import com.log.book.dto.UserDto;

public interface UserService {
	UserDto createUser(UserDto userDto); 
	List<UserDto> userList();
}
