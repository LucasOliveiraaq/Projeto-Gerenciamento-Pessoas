package com.lucaspo.gerenciamentopessoas.dto.response;

import com.lucaspo.gerenciamentopessoas.model.Endereco;
import com.lucaspo.gerenciamentopessoas.model.Pessoa;

import lombok.Getter;

@Getter
public class EnderecoResponseDTO {
	
	private String id;
	private String logradouro;
	private String cep;
	private int numero;
	private String cidade;
	private String estado;
	private Pessoa pessoa;
	private boolean principal;
	
	public EnderecoResponseDTO(Endereco endereco) {
		this.id = endereco.getId();
		this.logradouro = endereco.getLogradouro();
		this.cep = endereco.getCep();
		this.numero = endereco.getNumero();
		this.cidade = endereco.getCidade();
		this.estado = endereco.getEstado();
		this.pessoa = endereco.getPessoa();
		this.principal = endereco.isPrincipal();
	}
}
