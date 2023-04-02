package com.example.ExampleCrudApiRest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ExampleCrudApiRest.entity.Department;
import com.example.ExampleCrudApiRest.repository.DepartmentRepository;
import com.example.ExampleCrudApiRest.security.payload.response.Message;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/department")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class DepartmentController {

	@Autowired
	DepartmentRepository departmentRepository;

	@GetMapping("/departments")
	ResponseEntity<?> departments() {
		return new ResponseEntity(departmentRepository.findAll(), HttpStatus.ACCEPTED);
	}

	@PostMapping("/adddepartment")
	ResponseEntity<?> adddepartment(@Valid @RequestBody Department department, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<>(new Message("error", "Error", "Revice campos"), HttpStatus.BAD_REQUEST);
		} else {
			try {
				if (department.getId()==0) {
					departmentRepository.save(department);
					return new ResponseEntity<>(new Message("info", "Info", "Department agredado correctamente"),
							HttpStatus.ACCEPTED);
				}else {
					departmentRepository.save(new Department(department.getId(), department.getName()));
					return new ResponseEntity<>(new Message("info", "Info", "Department modificado correctamente"),
							HttpStatus.ACCEPTED);
				}
				
			} catch (Exception e) {
				return new ResponseEntity<>(new Message("error", "Error", e.getMessage()), HttpStatus.BAD_REQUEST);
			}
		}

	}

	@DeleteMapping("/deletedepartment/{id}")
	ResponseEntity<?> deletedepartment(@PathVariable("id") int id) {
		try {
			departmentRepository.deleteById(id);
			return new ResponseEntity<>(new Message("info", "Info", "Department eliminado correctamente"),
					HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<>(new Message("error", "Error", e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
	
	

}
