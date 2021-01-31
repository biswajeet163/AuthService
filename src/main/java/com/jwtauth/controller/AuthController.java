package com.jwtauth.controller;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.jwtauth.exception.InvalidExpiredTokenException;
import com.jwtauth.exception.InvalidUserCredentialsException;
import com.jwtauth.model.AuthRequest;
import com.jwtauth.model.AuthResponse;
import com.jwtauth.model.UserCredential;
import com.jwtauth.repository.UserRepository;
import com.jwtauth.service.CustomerDetailsService;
import com.jwtauth.service.JwtUtil;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin 
public class AuthController {

	@Autowired 
	private JwtUtil jwtutil;
	@Autowired
	private CustomerDetailsService custdetailservice;
	@Autowired
	private UserRepository urepo;

	@GetMapping(path = "/health")
	public ResponseEntity<String> healthCheckup() {
		log.info("AWS Health Checking");
		return new ResponseEntity<>("", HttpStatus.OK); 
	}

	@PostMapping(value = "/login")
	public ResponseEntity<?> login(@RequestBody UserCredential userlogincredentials)
			throws InvalidUserCredentialsException {
		log.info("Start: {}", "login started");
		UserDetails userdetails = null;
		try {
			userdetails = custdetailservice.loadUserByUsername(userlogincredentials.getUsername());

			if (userdetails.getPassword().equals(userlogincredentials.getPassword())) {
				String token = jwtutil.generateToken(userdetails);
				String username = userlogincredentials.getUsername();
				Date extractExpiration = jwtutil.extractExpiration(token);

				// We can Extract the role from User Repo using the Obtained Username
				// ******************************************************************************
				String role = custdetailservice.getRoleBasedOnUser(username);
				// ******************************************************************************
			
				AuthRequest authRequest = new AuthRequest(username, token, extractExpiration, role);
				log.info("End : {}", "Login Ended successfully ");
				return new ResponseEntity<AuthRequest>(authRequest, HttpStatus.OK);
			} else { 
				log.debug("Access Denied : {}", "login Error, Check User Credentials");
				return new ResponseEntity<>("Invalid Password", HttpStatus.FORBIDDEN);
			}

		} catch (Exception e) {
			throw new InvalidUserCredentialsException("Invalid User Credentials......Please check it");
		}

	}

	@GetMapping(value = "/validate")
	public ResponseEntity<AuthResponse> getValidity(@RequestHeader("Authorization") String token)
			throws InvalidExpiredTokenException {
		log.info("Start : {}", "Validity started");

		try {
			AuthResponse authResponse = null;
			if (jwtutil.validateToken(token)) {
				
				
				Optional<UserCredential> creds = urepo.findById(jwtutil.extractUsername(token));
				String username="";
				if (creds.isPresent()) {
				   username = creds.get().getUsername();
				}

				Date extractExpiration = jwtutil.extractExpiration(token);
				authResponse = new AuthResponse(username, extractExpiration, true);
			} else { 
				authResponse = new AuthResponse(true);
			}

			log.info("End : {}", "Validity successful");
			return new ResponseEntity<>(authResponse, HttpStatus.OK);
		}

		catch (Exception e) {
			throw new InvalidExpiredTokenException("Invalid Expired Token");
		}

	}

}
