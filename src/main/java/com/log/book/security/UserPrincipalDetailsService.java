package com.log.book.security;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.log.book.entity.UserEntity;
import com.log.book.repository.UserRepository;

@Service
public class UserPrincipalDetailsService implements UserDetailsService {
	
	private UserRepository userRepository;
	
	public UserPrincipalDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findByEmail(username);
		UserPrincipal userPrincipal = new UserPrincipal(userEntity);
		
		return userPrincipal;
	}

}
