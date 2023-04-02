package com.example.ExampleCrudApiRest.entity;

import java.io.Serializable;

import org.hibernate.validator.constraints.Range;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Employe implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int eid;

	@NotBlank(message = "El nombre no debe estar vacío")
	private String ename;

	// @NotEmpty(message = "No debe estar vacio")
	@Range(min = 1, max = 1000000, message = "Límite de salario por persona no debe exceder $1000000 ")
	// @Digits(message = "El salario debe ser un número", fraction = 0, integer = 0)
	@Min(value = 2, message = "El salario debe ser mayor de 2 dígitos")
	private double salary;

	private String deg;

	@ManyToOne
	private Department department;

	public Employe(int eid, String ename, double salary, String deg) {
		super();
		this.eid = eid;
		this.ename = ename;
		this.salary = salary;
		this.deg = deg;
	}

	public Employe(int eid, String ename, double salary) {
		super();
		this.eid = eid;
		this.ename = ename;
		this.salary = salary;
	}

	public Employe(String ename, double salary, String deg, Department department) {
		super();
		this.ename = ename;
		this.salary = salary;
		this.deg = deg;
		this.department = department;
	}

	public Employe(String ename, double salary, Department department) {
		super();
		this.ename = ename;
		this.salary = salary;
		this.department = department;
	}

	public Employe(int eid, String ename, double salary, Department department) {
		super();
		this.eid = eid;
		this.ename = ename;
		this.salary = salary;

		this.department = department;
	}

	public Employe() {
		super();
	}

	public Employe(int eid) {
		super();
		this.eid = eid;
	}

	public int getEid() {
		return eid;
	}

	public void setEid(int eid) {
		this.eid = eid;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getDeg() {
		return deg;
	}

	public void setDeg(String deg) {
		this.deg = deg;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return String.valueOf(salary);
	}

}
