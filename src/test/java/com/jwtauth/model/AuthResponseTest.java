package com.jwtauth.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;



class AuthResponseTest {
	AuthResponse authres = new AuthResponse();

	@Test
	void getsetTest() {
		
		DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
		Date d1 = null;
		try {
			d1 = df.parse("11-11-2020");
		} catch (ParseException e) {

		}
		authres.setDateofExpiry(d1);
		authres.setValid(true);
		AuthResponse authres = new AuthResponse("admin",d1,true);
		

	
		assertEquals("admin", authres.getUsername());
		assertEquals(d1, authres.getDateofExpiry());
		assertEquals(true, authres.isValid());

	}

}
