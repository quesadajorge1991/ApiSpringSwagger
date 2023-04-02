package com.example.ExampleCrudApiRest.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.ExampleCrudApiRest.security.entity.Authorities;

import jakarta.transaction.Transactional;

@Transactional
public interface AuthorityRepository extends JpaRepository<Authorities, Integer> {

	@Modifying
	int deleteByAuthority(String name);

	
	@Modifying
	@Query(value = "delete from Authorities where id=?1")
	void deleteById(Integer id);

}
