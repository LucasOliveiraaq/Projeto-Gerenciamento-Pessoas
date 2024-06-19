package com.lucaspo.gerenciamentopessoas.dto.request.user;

import com.lucaspo.gerenciamentopessoas.model.RoleName;

import lombok.Getter;

@Getter
public class CreateUserDTO {

	private String email;
	private String password;
	private RoleName role;
	
}
