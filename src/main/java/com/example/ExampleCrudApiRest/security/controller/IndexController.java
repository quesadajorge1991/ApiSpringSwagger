package com.example.ExampleCrudApiRest.security.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Hidden;

@RestController
@Hidden
public class IndexController {

	@GetMapping("/prueba")
	ResponseEntity<?> gg() {
		return new ResponseEntity<>("prueba", HttpStatus.ACCEPTED);
	}

}
