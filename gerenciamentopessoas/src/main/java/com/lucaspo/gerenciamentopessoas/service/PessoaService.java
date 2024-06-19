package com.lucaspo.gerenciamentopessoas.service;

import java.util.List;

import com.lucaspo.gerenciamentopessoas.dto.request.pessoa.PessoaRequestDTO;
import com.lucaspo.gerenciamentopessoas.dto.response.pessoa.PessoaResponseDTO;

public interface PessoaService {

	PessoaResponseDTO criarPessoa(PessoaRequestDTO pessoaRequestDTO);
	PessoaResponseDTO findById(String id);
	List<PessoaResponseDTO> findAll();
	PessoaResponseDTO update(String id, PessoaRequestDTO pessoaRequestDTO);
}
