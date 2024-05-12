package com.lucaspo.gerenciamentopessoas.dto.request;

import com.lucaspo.gerenciamentopessoas.model.Pessoa;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoRequestDTO {

	private String id;
	private String logradouro;
	private String cep;
	private int numero;
	private String cidade;
	private String estado;
	private Pessoa pessoa;
	private boolean principal;
	private String pessoa_id;
}
