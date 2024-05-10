package com.lucaspo.gerenciamentopessoas.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.lucaspo.gerenciamentopessoas.dto.request.PessoaRequestDTO;
import com.lucaspo.gerenciamentopessoas.dto.response.PessoaResponseDTO;
import com.lucaspo.gerenciamentopessoas.model.Pessoa;
import com.lucaspo.gerenciamentopessoas.repositories.PessoaRepository;
import com.lucaspo.gerenciamentopessoas.util.PessoaMapper;

import lombok.RequiredArgsConstructor;

@Service
@Primary
@RequiredArgsConstructor
public class PessoaServiceImpl implements PessoaService{
	
	private final PessoaRepository pessoaRepository;
	private final PessoaMapper pessoaMapper;

	@Override
	public PessoaResponseDTO criarPessoa(PessoaRequestDTO pessoaRequestDTO) {
		Pessoa pessoa = pessoaMapper.criarPessoa(pessoaRequestDTO);
		return pessoaMapper.returnPessoaDTO(pessoaRepository.save(pessoa));
	}

}
