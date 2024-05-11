package com.lucaspo.gerenciamentopessoas.util;

import java.util.List;
import java.util.stream.Collectors;

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
	
	public List<PessoaResponseDTO> listPessoaDTO(List<Pessoa> list){
		return list.stream().map(PessoaResponseDTO::new).collect(Collectors.toList());
	}
	
	public void updatePessoa(Pessoa pessoa, PessoaRequestDTO pessoaRequestDTO) {
		pessoa.setNomeCompleto(pessoaRequestDTO.getNomeCompleto());
		pessoa.setDataDeNascimento(pessoaRequestDTO.getDataDeNascimento());
	}
	
}
