package com.log.book.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

import com.log.book.repository.UserRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	UserPrincipalDetailsService userPrincipalDetailsService;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	UserRepository userRepository;

	/*
	 * private BasicAuthenticationEntryPoint authenticationEntryPoint;
	 * 
	 * public SecurityConfiguration(BasicAuthenticationEntryPoint
	 * authenticationEntryPoint) { this.authenticationEntryPoint =
	 * authenticationEntryPoint; }
	 */

	@Override
	protected void configure(AuthenticationManagerBuilder auth) {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
		.antMatchers(HttpMethod.POST, "/users/sign-up").permitAll()		
		.antMatchers("/manager/**").hasRole("ACCESS_MANAGER")
		.antMatchers("/admin/**").hasRole("ACCESS_ADMIN")	
		.anyRequest().authenticated().and()		
		.addFilter(new JWTAuthenticationFilter(authenticationManager()))
		.addFilter(new JWTAuthorizationFilter(authenticationManager(), userRepository))		
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);		
	}

	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder);
		daoAuthenticationProvider.setUserDetailsService(userPrincipalDetailsService);
		return daoAuthenticationProvider;

	}
	

}
