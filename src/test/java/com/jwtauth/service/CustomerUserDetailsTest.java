package com.jwtauth.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import com.jwtauth.model.UserCredential;
import com.jwtauth.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerUserDetailsTest 
{
	UserDetails userdetails; 
	
	@InjectMocks
	CustomerDetailsService custdetailservice;

	@Mock 
	UserRepository userservice; 

	@Test
	public void loadUserByUsernameTest() {
		
		UserCredential user1=new UserCredential("admin","admin", "role");
		Optional<UserCredential> data =Optional.of(user1) ;
		when(userservice.findById("admin")).thenReturn(data);
		UserDetails loadUserByUsername2 = custdetailservice.loadUserByUsername("admin");
		assertEquals(user1.getUsername(),loadUserByUsername2.getUsername());
	}
}
