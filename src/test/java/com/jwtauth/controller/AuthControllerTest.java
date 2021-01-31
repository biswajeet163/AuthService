package com.jwtauth.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;

import com.jwtauth.exception.InvalidExpiredTokenException;
import com.jwtauth.exception.InvalidUserCredentialsException;
import com.jwtauth.model.AuthResponse;
import com.jwtauth.model.UserCredential;
import com.jwtauth.repository.UserRepository;
import com.jwtauth.service.CustomerDetailsService;
import com.jwtauth.service.JwtUtil;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthControllerTest {
	@InjectMocks
	AuthController authController;

	AuthResponse authResponse;

	UserDetails userdetails;

	@Mock
	JwtUtil jwtutil;

	@Mock
	CustomerDetailsService custdetailservice;

	@Mock
	UserRepository userservice;
 
	@Test
	public void loginTest() throws InvalidUserCredentialsException {

		UserCredential user = new UserCredential("admin", "admin", "role");
		UserDetails loadUserByUsername = custdetailservice.loadUserByUsername("admin");
		UserDetails value = new User(user.getUsername(), user.getPassword(), new ArrayList<>());
		
		when(custdetailservice.loadUserByUsername("admin")).thenReturn(value);
		when(jwtutil.generateToken(loadUserByUsername)).thenReturn("token");
		
		ResponseEntity<?> login = authController.login(user);
		assertEquals(200, login.getStatusCodeValue());
	}

	@Test
	public void loginTestFailed() throws InvalidUserCredentialsException {

		UserCredential user = new UserCredential("admin", "admin", "role");
		UserDetails loadUserByUsername = custdetailservice.loadUserByUsername("admin");
		UserDetails value = new User(user.getUsername(), "admin11", new ArrayList<>());
		
		when(custdetailservice.loadUserByUsername("admin")).thenReturn(value);
		when(jwtutil.generateToken(loadUserByUsername)).thenReturn("token");
		
		ResponseEntity<?> login = authController.login(user);
		assertEquals(403, login.getStatusCodeValue());
	}

	@Test
	public void validateTestValidtoken() throws InvalidExpiredTokenException {
		when(jwtutil.validateToken("token")).thenReturn(true);
		when(jwtutil.extractUsername("token")).thenReturn("admin");
		
		UserCredential user1 = new UserCredential("admin", "admin", "role");
		Optional<UserCredential> data = Optional.of(user1);
		when(userservice.findById("admin")).thenReturn(data);
		
		ResponseEntity<?> validity = authController.getValidity("token");
		assertEquals(false, validity.getBody().toString().contains("true"));

	} 

	@Test
	public void validateTestInValidtoken() throws InvalidExpiredTokenException {
		when(jwtutil.validateToken("token")).thenReturn(false);
		ResponseEntity<?> validity = authController.getValidity("bearer token");
		assertEquals(false, validity.getBody().toString().contains("false"));
	}

}
