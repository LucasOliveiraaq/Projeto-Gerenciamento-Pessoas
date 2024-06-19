package com.lucaspo.gerenciamentopessoas.dto.response.pessoa;

import java.util.GregorianCalendar;

import com.lucaspo.gerenciamentopessoas.model.Pessoa;

import lombok.Getter;

@Getter
public class PessoaResponseDTO {

	private String id;
	private String nomeCompleto;
	private GregorianCalendar dataDeNascimento;
	
	public PessoaResponseDTO(Pessoa pessoa) {
		this.id = pessoa.getId();
		this.nomeCompleto = pessoa.getNomeCompleto();
		this.dataDeNascimento = pessoa.getDataDeNascimento();
	}
	
	
}
