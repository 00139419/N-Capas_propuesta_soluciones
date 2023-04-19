package com.sv.apppyme.entities;

import java.util.Date;

import lombok.Data;

@Data
public class User {
	private String username;
	private String password;
	private Date fechaContratacion;
	private boolean active;
	private String rol;

	public User() {
		super();
	}

	public User(String username, String password, Date fechaContratacion, boolean active, String rol) {
		super();
		this.username = username;
		this.password = password;
		this.fechaContratacion = fechaContratacion;
		this.active = active;
		this.rol = rol;
	}

}
