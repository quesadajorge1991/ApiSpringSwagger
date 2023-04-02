package com.example.ExampleCrudApiRest.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.ExampleCrudApiRest.security.entity.MiUserDetail;
import com.example.ExampleCrudApiRest.security.entity.Users;
import com.example.ExampleCrudApiRest.security.repository.UsersRepository;

@Service
public class MiUserDetailService implements UserDetailsService {

	@Autowired
	UsersRepository usersRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = usersRepository.findByUsername(username);
		return MiUserDetail.build(user);
	}

}
