/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ExampleCrudApiRest.security.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author Jorge
 */
@Entity
@Table(name = "users")
public class Users implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String username;

	private String password;

	private boolean enabled;

	private String descripcion;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "username", updatable = false)
	@JsonIgnore
	private List<Authorities> authoritiesList;

//
	public Users() {
	}

	public Users(int id) {
		this.id = id;
	}

	public Users(String username) {
		super();
		this.username = username;
	}

	public Users(String username, String password, boolean enabled, String descripcion) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.descripcion = descripcion;
	}

	public Users(int id, String username, String password, boolean enabled, String descripcion) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.descripcion = descripcion;
	}

	public Users(int id, String username, String password, boolean enabled, String descripcion,
			List<Authorities> authoritiesList) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.descripcion = descripcion;
		this.authoritiesList = authoritiesList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Authorities> getAuthoritiesList() {
		return authoritiesList;
	}

	public void setAuthoritiesList(List<Authorities> authoritiesList) {
		this.authoritiesList = authoritiesList;
	}

}
