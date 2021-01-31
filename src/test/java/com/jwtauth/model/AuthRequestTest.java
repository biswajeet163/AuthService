package com.jwtauth.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;

class AuthRequestTest {

	AuthRequest authres = new AuthRequest();

	@Test
	void getsetTest() { 
		
		authres.setUsername("admin");
		authres.setToken("ABCD");
		DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
		Date d1 = null;
		try { 
			d1 = df.parse("11-11-2020");
		} catch (ParseException e) {

		} 
		authres.setValidUpTo(d1);

		AuthRequest authres = new AuthRequest("admin", "ABCD", d1, "admin");

		assertEquals("admin", authres.getUsername());
		assertEquals("ABCD", authres.getToken());
		assertEquals(d1, authres.getValidUpTo());

	}

}
