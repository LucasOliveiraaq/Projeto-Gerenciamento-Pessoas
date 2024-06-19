package com.lucaspo.gerenciamentopessoas.dto.request.pessoa;

import java.util.GregorianCalendar;

import lombok.Getter;

@Getter
public class PessoaRequestDTO {

	private String id;
	private String nomeCompleto;
	private GregorianCalendar dataDeNascimento;
	
}
