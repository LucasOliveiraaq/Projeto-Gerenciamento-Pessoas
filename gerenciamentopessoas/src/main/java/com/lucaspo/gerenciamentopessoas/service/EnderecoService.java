package com.lucaspo.gerenciamentopessoas.service;

import java.util.List;

import com.lucaspo.gerenciamentopessoas.dto.request.EnderecoRequestDTO;
import com.lucaspo.gerenciamentopessoas.dto.response.EnderecoResponseDTO;

public interface EnderecoService {

	EnderecoResponseDTO criarEndereco(EnderecoRequestDTO enderecoRequestDTO);
	EnderecoResponseDTO findById(String id);
	List<EnderecoResponseDTO> findAll();
}
