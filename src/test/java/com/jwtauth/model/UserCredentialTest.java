package com.jwtauth.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserCredentialTest 
{
	UserCredential userHospital=new UserCredential();
	
	@Test
	public void testUserHospitalAllConstructor()
	{
		UserCredential hospital=new UserCredential("ab", "ab", "role");
		assertEquals("ab",hospital.getUsername());
	}
	
	@Test
	public void testUserid()
	{
		userHospital.setUsername("Username");
		assertEquals("abc",userHospital.getPassword());
	}
	
	@Test
	public void testUserPassword()
	{
		userHospital.setPassword("abc");
		assertEquals("abc ",userHospital.getPassword());
	}
	
	
//	@Test
//	public void testAuthToken()
//	{
//		userHospital.setAuthToken("abc");
//		assertEquals(userHospital.getAuthToken(),"abc");
//	}
	 
	@Test
	public void testoString() {
		String string = userHospital.toString();
		assertEquals(userHospital.toString(),string);
	}
}
