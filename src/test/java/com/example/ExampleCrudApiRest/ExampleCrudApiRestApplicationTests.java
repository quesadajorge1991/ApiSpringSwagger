package com.example.ExampleCrudApiRest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.ExampleCrudApiRest.security.repository.AuthorityRepository;
import com.example.ExampleCrudApiRest.security.repository.UsersRepository;

@SpringBootTest
class ExampleCrudApiRestApplicationTests {
	
	@Autowired
	UsersRepository usersRepository;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	AuthorityRepository authorityRepository;
	

	@Test
	void contextLoads() {
		//encode=new BCryptPasswordEncoder();
		
	System.out.println(encoder.encode("admin"));
		
	}

}
