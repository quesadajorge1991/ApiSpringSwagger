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
import com.example.ExampleCrudApiRest.entity.Employe;
import com.example.ExampleCrudApiRest.repository.EmployeRepository;
import com.example.ExampleCrudApiRest.security.payload.response.Message;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/employe")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class EmployeController {

	@Autowired
	EmployeRepository employeRepositorye;

	
	@GetMapping("/employes")
	ResponseEntity<?> employes() {
		return new ResponseEntity(employeRepositorye.findAll(), HttpStatus.ACCEPTED);
	}

	@PostMapping("/addemploye")
	ResponseEntity<?> adddepartment(@Valid @RequestBody Employe employe, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<>(new Message("error", "Error", "Revice campos"), HttpStatus.BAD_REQUEST);
		} else {
			try {
				if (employe.getEid() == 0) {
					employeRepositorye
							.save(new Employe(employe.getEname(), employe.getSalary(), employe.getDepartment()));
					return new ResponseEntity<>(new Message("info", "Info", "Employe agredado correctamente"),
							HttpStatus.ACCEPTED);
				} else {
					employeRepositorye.save(new Employe(employe.getEid(), employe.getEname(), employe.getSalary(),
							new Department(employe.getDepartment().getId())));
					return new ResponseEntity<>(new Message("info", "Info", "Employe modificado correctamente"),
							HttpStatus.ACCEPTED);
				}
			} catch (Exception e) {
				return new ResponseEntity<>(new Message("error", "Error", e.getMessage()), HttpStatus.BAD_REQUEST);
			}
		}

	}

	@DeleteMapping("/deleteemploye/{id}")
	ResponseEntity<?> deletedepartment(@PathVariable("id") int id) {
		try {
			employeRepositorye.deleteById(id);
			return new ResponseEntity<>(new Message("info", "Info", "Employe eliminado correctamente"),
					HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<>(new Message("error", "Error", e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}

}
