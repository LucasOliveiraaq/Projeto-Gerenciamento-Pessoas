package com.lucaspo.gerenciamentopessoas.service;

import com.lucaspo.gerenciamentopessoas.dto.request.PessoaRequestDTO;
import com.lucaspo.gerenciamentopessoas.dto.response.PessoaResponseDTO;

public interface PessoaService {

	PessoaResponseDTO criarPessoa(PessoaRequestDTO pessoaRequestDTO);
}
