package com.sv.apppyme.controllers.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginDto {
	
	@NotEmpty(message = "Usuario Vacio!")
	private String username;
	
	@NotEmpty(message = "password Vacio!")
	private String password;
	
}
