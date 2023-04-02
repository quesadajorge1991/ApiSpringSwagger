package com.example.ExampleCrudApiRest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ExampleCrudApiRest.entity.Employe;

public interface EmployeRepository extends JpaRepository<Employe, Integer> {

}
