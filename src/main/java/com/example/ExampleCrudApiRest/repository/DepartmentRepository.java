package com.example.ExampleCrudApiRest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ExampleCrudApiRest.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

}
