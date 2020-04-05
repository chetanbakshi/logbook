package com.log.book.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.log.book.entity.UserEntity;
import com.log.book.repository.UserRepository;

import io.jsonwebtoken.Jwts;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
	
	
	private UserRepository userRepository;

	public JWTAuthorizationFilter(AuthenticationManager authenticationManager, UserRepository userRepository) {
		super(authenticationManager);
		this.userRepository = userRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		String header = request.getHeader(JWTConstants.HEADER_STRING);
		if(header == null || !header.startsWith(JWTConstants.TOKEN_PREFIX)) {
			chain.doFilter(request, response);
			return;
		}
		Authentication authentication = getUsernamePasswordAuthentication(request);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(request, response);
	}
	
	private Authentication getUsernamePasswordAuthentication(HttpServletRequest request) {
		String token = request.getHeader(JWTConstants.HEADER_STRING);
		if(token != null) {
			String username = Jwts.parser().setSigningKey(JWTConstants.SECRET).parseClaimsJws(token.replace(JWTConstants.TOKEN_PREFIX, ""))
					.getBody().getSubject();
			if(username != null) {
				UserEntity userEntity = userRepository.findByEmail(username);
				UserPrincipal userPrincipal = new UserPrincipal(userEntity);
				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, null, userPrincipal.getAuthorities());
				return auth;
			}
			return null;
		}
		return null;
	}

}
