package com.log.book.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.log.book.dto.UserDto;
import com.log.book.entity.UserEntity;
import com.log.book.repository.UserRepository;
import com.log.book.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public UserDto createUser(UserDto userDto) {
		userDto.setActive(1);
		userDto.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
		userDto.setRoles("MANAGER");
		userDto.setPermissions("ACESS_MANAGER,ACCESS_USER");		
		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(userDto, userEntity);
		UserEntity createdUser = userRepository.save(userEntity);
		UserDto response = new UserDto();
		BeanUtils.copyProperties(createdUser, response);
		return response;
		
	}

	@Override
	public List<UserDto> userList() {
		List<UserEntity> userList = userRepository.findAll();
		List<UserDto> response = new ArrayList<UserDto>();
		for(UserEntity user: userList) {
			UserDto userDto = new UserDto();
			BeanUtils.copyProperties(user, userDto);
			response.add(userDto);
		}
		return response;
	}

}
