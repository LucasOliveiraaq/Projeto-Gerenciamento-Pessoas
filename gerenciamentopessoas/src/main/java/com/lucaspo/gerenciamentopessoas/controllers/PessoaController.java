package com.lucaspo.gerenciamentopessoas.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucaspo.gerenciamentopessoas.dto.request.pessoa.PessoaRequestDTO;
import com.lucaspo.gerenciamentopessoas.dto.response.pessoa.PessoaResponseDTO;
import com.lucaspo.gerenciamentopessoas.service.PessoaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/pessoa")
@RequiredArgsConstructor
public class PessoaController {

	private final PessoaService pessoaService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<PessoaResponseDTO> findByPessoaId(@PathVariable String id){
		return ResponseEntity.ok().body(pessoaService.findById(id));
	}
	
	@GetMapping
	public ResponseEntity<List<PessoaResponseDTO>> findAll() {
		return ResponseEntity.ok().body(pessoaService.findAll());
	}
	
	@PostMapping
	public ResponseEntity<PessoaResponseDTO> register(@RequestBody PessoaRequestDTO pessoaRequestDTO) {
		PessoaResponseDTO pessoaResponseDTO = pessoaService.criarPessoa(pessoaRequestDTO);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<PessoaResponseDTO> update(@RequestBody PessoaRequestDTO pessoaRequestDTO, @PathVariable String id) {
		return ResponseEntity.ok().body(pessoaService.update(id, pessoaRequestDTO));
	}
}
