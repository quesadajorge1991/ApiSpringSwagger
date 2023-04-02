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

import com.example.ExampleCrudApiRest.security.entity.Authorities;
import com.example.ExampleCrudApiRest.security.entity.Users;
import com.example.ExampleCrudApiRest.security.payload.response.Message;
import com.example.ExampleCrudApiRest.security.repository.AuthorityRepository;
import com.example.ExampleCrudApiRest.security.repository.UsersRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/authority")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class AuthorityController {

	@Autowired
	AuthorityRepository authorityRepository;

	@Autowired
	PasswordEncoder encoder;

	@GetMapping("/authorities")
	ResponseEntity<?> authorities() {
		return new ResponseEntity(authorityRepository.findAll(), HttpStatus.ACCEPTED);
	}

	@PostMapping("/addauthority")
	ResponseEntity<?> addauthority(@Valid @RequestBody Authorities authorities, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<>(new Message("error", "Error", "Revice campos"), HttpStatus.BAD_REQUEST);
		} else {
			System.err.println(authorities.getAuthority());
			try {
				if (authorities.getId() == 0) {
					authorityRepository.save(
							new Authorities(authorities.getAuthority(), new Users(authorities.getUsername().getId())));
					return new ResponseEntity<>(new Message("info", "Info", "User  agredado correctamente"),
							HttpStatus.ACCEPTED);
				} else {
					authorityRepository.save(new Authorities(authorities.getId(), authorities.getAuthority(),
							new Users(authorities.getUsername().getId())));

					return new ResponseEntity<>(new Message("info", "Info", "User modificado correctamente"),
							HttpStatus.ACCEPTED);
				}

			} catch (Exception e) {
				return new ResponseEntity<>(new Message("error", "Error", e.getMessage()), HttpStatus.BAD_REQUEST);
			}
		}

	}

	@DeleteMapping("/deleteauthority/{id}")
	ResponseEntity<?> deleteauthority(@PathVariable("id") int id) {
		try {
			
			authorityRepository.deleteById(id);
			return new ResponseEntity<>(new Message("info", "Info", "Permiso eliminado correctamente"),
					HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<>(new Message("error", "Error", e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}

}
