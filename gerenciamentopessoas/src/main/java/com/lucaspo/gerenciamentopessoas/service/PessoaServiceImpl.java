package com.lucaspo.gerenciamentopessoas.service;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.lucaspo.gerenciamentopessoas.dto.request.pessoa.PessoaRequestDTO;
import com.lucaspo.gerenciamentopessoas.dto.response.pessoa.PessoaResponseDTO;
import com.lucaspo.gerenciamentopessoas.exceptions.NotFoundIdException;
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

	@Override
	public PessoaResponseDTO findById(String id) {
		return pessoaMapper.returnPessoaDTO(returnPessoaId(id));
	}
	
	private Pessoa returnPessoaId(String id) {
		return pessoaRepository.findById(id)
				.orElseThrow(() -> new NotFoundIdException("Id da pessoa não existe"));
	}

	@Override
	public List<PessoaResponseDTO> findAll() {
		return pessoaMapper.listPessoaDTO(pessoaRepository.findAll());
	}

	@Override
	public PessoaResponseDTO update(String id, PessoaRequestDTO pessoaRequestDTO) {
		Pessoa pessoa = returnPessoaId(id);
		pessoaMapper.updatePessoa(pessoa, pessoaRequestDTO);
		return pessoaMapper.returnPessoaDTO(pessoaRepository.save(pessoa));
	}

}
