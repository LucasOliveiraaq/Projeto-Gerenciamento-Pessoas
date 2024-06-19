package com.lucaspo.gerenciamentopessoas.dto.response.auth;

import lombok.Getter;

@Getter
public class RecuperarJwtTokenDTO {

	private String token;
	
	public RecuperarJwtTokenDTO(String token) {
		this.token = token;
	}
}
