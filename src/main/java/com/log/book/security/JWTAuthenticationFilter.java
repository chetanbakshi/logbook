package com.log.book.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.log.book.ui.model.request.LoginRequestModel;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		LoginRequestModel credentials = null;
		try {
			credentials = new ObjectMapper().readValue(request.getInputStream(), LoginRequestModel.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				credentials.getEmail(), credentials.getPassword(), new ArrayList<>());

		Authentication auth = authenticationManager.authenticate(authenticationToken);

		return auth;
	}
	
	@Override 
	public void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
		UserPrincipal userPrincipal = (UserPrincipal) authResult.getPrincipal();
		String token = Jwts.builder().setSubject(userPrincipal.getUsername())
				.setExpiration(new Date(System.currentTimeMillis() + JWTConstants.EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, JWTConstants.SECRET).compact();
		response.addHeader(JWTConstants.HEADER_STRING, JWTConstants.TOKEN_PREFIX+token);
	}

}
