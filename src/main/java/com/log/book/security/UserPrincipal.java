package com.log.book.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.log.book.entity.UserEntity;

public class UserPrincipal implements UserDetails {

	private static final long serialVersionUID = -1652560906005780378L;
	private UserEntity userEntity;
	
	public UserPrincipal(UserEntity userEntity) {
		this.userEntity = userEntity;
	}
	
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		this.userEntity.getRoleList().forEach(o -> {
			GrantedAuthority authority = new SimpleGrantedAuthority(o);
			authorities.add(authority);			
		});
		
		this.userEntity.getPermissionList().forEach(o -> {
			GrantedAuthority authority = new SimpleGrantedAuthority(o);
			authorities.add(authority);			
		});
		
		return authorities;
	}

	@Override
	public String getPassword() {
		return userEntity.getEncryptedPassword();
	}

	@Override
	public String getUsername() {
		return userEntity.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return userEntity.getActive() == 1;
	}

}
