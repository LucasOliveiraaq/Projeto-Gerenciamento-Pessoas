package com.lucaspo.gerenciamentopessoas.util;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.lucaspo.gerenciamentopessoas.dto.request.endereco.EnderecoRequestDTO;
import com.lucaspo.gerenciamentopessoas.dto.response.endereco.EnderecoResponseDTO;
import com.lucaspo.gerenciamentopessoas.model.Endereco;

@Component
public class EnderecoMapper {

	public Endereco criarEndereco(EnderecoRequestDTO enderecoDTO) {
		return Endereco.builder()
					   .logradouro(enderecoDTO.getLogradouro())
					   .cep(enderecoDTO.getCep())
					   .numero(enderecoDTO.getNumero())
					   .cidade(enderecoDTO.getCidade())
					   .estado(enderecoDTO.getEstado())
					   .pessoa(enderecoDTO.getPessoa())
					   .principal(enderecoDTO.isPrincipal())
					   .build();
	}
	
	public EnderecoResponseDTO returnEnderecoDTO(Endereco endereco) {
		return new EnderecoResponseDTO(endereco);
	}
	
	public List<EnderecoResponseDTO> listEnderecoDTO(List<Endereco> list) {
		return list.stream().map(EnderecoResponseDTO::new).collect(Collectors.toList());
	}
}
