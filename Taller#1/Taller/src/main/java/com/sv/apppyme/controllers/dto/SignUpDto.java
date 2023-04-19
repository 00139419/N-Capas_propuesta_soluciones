package com.sv.apppyme.controllers.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignUpDto {
	
	@NotBlank(message = "Usuario Vacio!")
	private String username;
	
	@NotBlank(message = "password Vacio!")
	private String password;
	
	@NotBlank(message = "rol Vacio!")
	private String rol;
}
