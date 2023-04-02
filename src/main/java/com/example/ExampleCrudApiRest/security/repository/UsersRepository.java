package com.example.ExampleCrudApiRest.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ExampleCrudApiRest.security.entity.Users;

import jakarta.transaction.Transactional;


@Transactional
public interface UsersRepository extends JpaRepository<Users, Integer> {

	Users findByUsername(String username);
	
	int deleteByUsername(String username);
}
