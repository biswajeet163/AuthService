package com.jwtauth;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.jwtauth.model.UserCredential;
import com.jwtauth.repository.UserRepository;

@SpringBootApplication
public class AuthApplications {
	 
//	@Autowired
//	private UserRepository userRepository;
//	
//	@PostConstruct
//	public void fun() {
//		UserCredential user1= new UserCredential("biswajeet", "123", "admin");
//		UserCredential user2= new UserCredential("deepak", "123", "admin");
//		UserCredential user3= new UserCredential("tanmay", "123", "user");
//		UserCredential user4= new UserCredential("rohit", "123", "user");
//		
//		userRepository.save(user1);
//		userRepository.save(user2);
//		userRepository.save(user3);
//		userRepository.save(user4); 
//	}

	public static void main(String[] args) {
		SpringApplication.run(AuthApplications.class, args);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		MyFilter myFilter = new MyFilter();
		filterRegistrationBean.setFilter(myFilter);
		return filterRegistrationBean;
	}

}
