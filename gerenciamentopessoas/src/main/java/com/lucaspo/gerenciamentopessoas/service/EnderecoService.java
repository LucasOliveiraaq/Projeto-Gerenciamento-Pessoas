package com.lucaspo.gerenciamentopessoas.service;

import java.util.List;

import com.lucaspo.gerenciamentopessoas.dto.request.endereco.EnderecoRequestDTO;
import com.lucaspo.gerenciamentopessoas.dto.response.endereco.EnderecoResponseDTO;

public interface EnderecoService {

	EnderecoResponseDTO criarEndereco(EnderecoRequestDTO enderecoRequestDTO);
	EnderecoResponseDTO findById(String id);
	List<EnderecoResponseDTO> findAll();
	List<EnderecoResponseDTO> findByPessoa_id(String pessoa_id);
	EnderecoResponseDTO findByPessoaIdAndPrincipalIsTrue(String pessoa_id);
	void cadastrarEnderecoPrincipal(String enderecoId);
	
}
