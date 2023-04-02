package com.example.ExampleCrudApiRest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.ExampleCrudApiRest.security.entity.Authorities;
import com.example.ExampleCrudApiRest.security.entity.Users;
import com.example.ExampleCrudApiRest.security.repository.AuthorityRepository;
import com.example.ExampleCrudApiRest.security.repository.UsersRepository;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class ExampleCrudApiRestApplication {

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info().title("API Rest Crud  example with Spring boot 3, Spring Security 6  and Swagger").version("1.0.0")
						.description("This is a example application crud with Spring boot 3 and Spring Security "));
	}

	@Autowired
	UsersRepository usersRepository;

	@Autowired
	AuthorityRepository authorityRepository;

	@Autowired
	PasswordEncoder encoder;

	public static void main(String[] args) {
		SpringApplication.run(ExampleCrudApiRestApplication.class, args);
	}

	@PostConstruct /*
					 * add the user 'admin' with password 'admin' (using BCryptPasswordEncoder) and
					 * authorities by default
					 */
	public void addUser_Authorities() {

		if (usersRepository.findByUsername("admin") == null) {
			Users user = new Users("admin", encoder.encode("admin"), true, "FULL ADMIN");
			usersRepository.save(user);
			authorityRepository.save(new Authorities("ROLE_ADMIN", user));
			authorityRepository.save(new Authorities("ROLE_EMPLOYE", user));
			authorityRepository.save(new Authorities("ROLE_DEPARTMENT", user));

		} else {
			System.out.println("Ya existe el usuario admin en la bd");
		}

	}

}
