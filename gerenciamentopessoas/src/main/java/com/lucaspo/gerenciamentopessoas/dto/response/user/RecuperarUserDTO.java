package com.lucaspo.gerenciamentopessoas.dto.response.user;

import java.util.List;

import com.lucaspo.gerenciamentopessoas.dto.response.auth.RecuperarJwtTokenDTO;

public class RecuperarUserDTO {

	private String id;
	private String email;
	private List<RecuperarJwtTokenDTO> roles;
	
//	public RecuperarUserDTO(User user) {
//		this.id = user.getId();
//		this.email = user.getEmail();
//	}
}
