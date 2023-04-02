/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ExampleCrudApiRest.security.entity;

import java.io.Serializable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 *
 * @author wason
 */
@Entity
@Table(name = "Authorities")
public class Authorities implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String authority;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "username")
	private Users username;

	public Authorities() {
	}

	public Authorities(Users username) {
		this.username = username;
	}

	public Authorities(String authority, Users username) {
		this.authority = authority;
		this.username = username;
	}

	public Authorities(Integer id) {
		this.id = id;
	}

	public Authorities(int id, String authority) {
		this.id = id;
		this.authority = authority;
	}

	public Authorities(String authority) {
		this.authority = authority;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Users getUsername() {
		return username;
	}

	public void setUsername(Users username) {
		this.username = username;
	}

	public Authorities(int id, Users username) {
		this.id = id;
		this.username = username;
	}

	public Authorities(int id, String authority, Users username) {
		this.id = id;
		this.authority = authority;
		this.username = username;
	}
}
