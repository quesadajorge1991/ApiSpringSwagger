package com.example.ExampleCrudApiRest.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ExampleCrudApiRest.security.entity.MiUserDetail;
import com.example.ExampleCrudApiRest.security.entity.Users;
import com.example.ExampleCrudApiRest.security.jwt.JwtProvider;
import com.example.ExampleCrudApiRest.security.payload.request.LoginRequest;
import com.example.ExampleCrudApiRest.security.payload.response.JwtResponse;
import com.example.ExampleCrudApiRest.security.payload.response.Message;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtProvider jwtProvider;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping("/login")
	public ResponseEntity<JwtResponse> login(@Valid @RequestBody LoginRequest loginUsuario, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return new ResponseEntity(new Message("error", "Error", "Revise campos"), HttpStatus.BAD_REQUEST);
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginUsuario.getUsername(), loginUsuario.getPassword()));

		System.err.println(authentication);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtProvider.generateToken(authentication);

		MiUserDetail userDetails = (MiUserDetail) authentication.getPrincipal();

		System.err.println(jwt);

		JwtResponse jwtDto = new JwtResponse(jwt, "Bearer", userDetails.getId(), userDetails.getUsername(),
				userDetails.getAuthorities());
		return new ResponseEntity(jwtDto, HttpStatus.OK);
	}
}
