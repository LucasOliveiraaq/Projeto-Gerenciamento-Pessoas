package com.lucaspo.gerenciamentopessoas.util;

import org.springframework.stereotype.Component;

import com.lucaspo.gerenciamentopessoas.dto.request.PessoaRequestDTO;
import com.lucaspo.gerenciamentopessoas.dto.response.PessoaResponseDTO;
import com.lucaspo.gerenciamentopessoas.model.Pessoa;

@Component
public class PessoaMapper {

	public Pessoa criarPessoa(PessoaRequestDTO pessoaDTO) {
		return Pessoa.builder()
					.nomeCompleto(pessoaDTO.getNomeCompleto())
					.dataDeNascimento(pessoaDTO.getDataDeNascimento())
					.build();
	}
	
	public PessoaResponseDTO returnPessoaDTO(Pessoa pessoa) {
		return new PessoaResponseDTO(pessoa);
	}
}
