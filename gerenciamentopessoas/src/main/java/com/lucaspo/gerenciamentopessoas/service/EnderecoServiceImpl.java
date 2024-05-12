package com.lucaspo.gerenciamentopessoas.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.lucaspo.gerenciamentopessoas.dto.request.EnderecoRequestDTO;
import com.lucaspo.gerenciamentopessoas.dto.response.EnderecoResponseDTO;
import com.lucaspo.gerenciamentopessoas.model.Endereco;
import com.lucaspo.gerenciamentopessoas.model.Pessoa;
import com.lucaspo.gerenciamentopessoas.repositories.EnderecoRepository;
import com.lucaspo.gerenciamentopessoas.repositories.PessoaRepository;
import com.lucaspo.gerenciamentopessoas.util.EnderecoMapper;

import lombok.RequiredArgsConstructor;

@Service
@Primary
@RequiredArgsConstructor
public class EnderecoServiceImpl implements EnderecoService{
	
	private final EnderecoRepository enderecoRepository;
	private final EnderecoMapper enderecoMapper;
	private final PessoaRepository pessoaRepository;

	@Override
	public EnderecoResponseDTO criarEndereco(EnderecoRequestDTO enderecoRequestDTO) {
		enderecoRequestDTO.setPessoa(verificarPessoaParaEndereco(enderecoRequestDTO.getPessoa_id()));
		Endereco endereco = enderecoMapper.criarEndereco(enderecoRequestDTO);
		return enderecoMapper.returnEnderecoDTO(enderecoRepository.save(endereco));
	}
	
	public Pessoa verificarPessoaParaEndereco(String id) {
		Optional<Pessoa> pessoaOptional = pessoaRepository.findById(id);
		return pessoaOptional.orElseThrow(() -> new NoSuchElementException("Não existe esse"+ id + " na tabela pessoa"));
	}

	@Override
	public EnderecoResponseDTO findById(String id) {
		return enderecoMapper.returnEnderecoDTO(returnEnderecoId(id));
	}
	
	private Endereco returnEnderecoId(String id) {
		return enderecoRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Id do endereco não existe"));
	}

	@Override
	public List<EnderecoResponseDTO> findAll() {
		return enderecoMapper.listEnderecoDTO(enderecoRepository.findAll());
	}
}
