package com.jwtauth.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jwtauth.model.UserCredential;
import com.jwtauth.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository urepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("Start : {}", "Loading UserDetails...");
		// UserCredential custuser = urepo.findById(username).orElse(null);

		Optional<UserCredential> userCreds = urepo.findById(username);
		UserCredential custuser = null;

		if (userCreds.isPresent()) {
			custuser = userCreds.get();
		}

		log.info("End : {}", "Loading UserDetails successful");
		if(custuser == null) {
			return null;
		}
		return new User(custuser.getUsername(), custuser.getPassword(), new ArrayList<>());

	}

	public String getRoleBasedOnUser(String username) {

		Optional<UserCredential> userCred = urepo.findById(username);
		UserCredential user = null;
		if (userCred.isPresent()) {
			user = userCred.get();
		}
		if(user != null) {
			return user.getRole(); 
		}
		return "";
	}

}
