package com.example.ExampleCrudApiRest.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ExampleCrudApiRest.entity.Department;
import com.example.ExampleCrudApiRest.security.entity.Users;
import com.example.ExampleCrudApiRest.security.payload.response.Message;
import com.example.ExampleCrudApiRest.security.repository.UsersRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class UsersController {

	@Autowired
	UsersRepository usersRepository;

	@Autowired
	PasswordEncoder encoder;

	@GetMapping("/users")
	ResponseEntity<?> users() {
		return new ResponseEntity(usersRepository.findAll(), HttpStatus.ACCEPTED);
	}

	@PostMapping("/adduser")
	ResponseEntity<?> adduser(@Valid @RequestBody Users user, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<>(new Message("error", "Error", "Revice campos"), HttpStatus.BAD_REQUEST);
		} else {
			try {
				if (user.getId() == 0) {
					usersRepository.save(new Users(user.getUsername(), encoder.encode(user.getPassword()), user.isEnabled(), user.getDescripcion()));
					return new ResponseEntity<>(
							new Message("info", "Info", "User " + user.getUsername() + " agredado correctamente"),
							HttpStatus.ACCEPTED);
				} else {
					usersRepository.save(new Users(user.getId(), user.getUsername(), encoder.encode(user.getPassword()),
							user.isEnabled(), user.getDescripcion()));
					return new ResponseEntity<>(new Message("info", "Info", "User modificado correctamente"),
							HttpStatus.ACCEPTED);
				}

			} catch (Exception e) {
				return new ResponseEntity<>(new Message("error", "Error", e.getMessage()), HttpStatus.BAD_REQUEST);
			}
		}

	}
	
	@DeleteMapping("/deleteuser/{id}")
	ResponseEntity<?> deleteuser(@PathVariable("id") int id) {
		try {
			usersRepository.deleteById(id);
			return new ResponseEntity<>(new Message("info", "Info", "User eliminado correctamente"),
					HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<>(new Message("error", "Error", e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}

}
