package com.lucaspo.gerenciamentopessoas.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucaspo.gerenciamentopessoas.dto.request.PessoaRequestDTO;
import com.lucaspo.gerenciamentopessoas.dto.response.PessoaResponseDTO;
import com.lucaspo.gerenciamentopessoas.service.PessoaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/pessoa")
@RequiredArgsConstructor
public class PessoaController {

	private final PessoaService pessoaService;

//	@GetMapping(value = "/{id}")
//	public ResponseEntity<Pessoa> findByPessoaId(@PathVariable String id){
//		return ResponseEntity.ok().body(pessoaRepository.findByPessoaId(id));
//	}
	
//	@GetMapping
//	public ResponseEntity<List<Pessoa>> findAll() {
//		return ResponseEntity.ok().body(pessoaRepository.findAll());
//	}
	
	@PostMapping
	public ResponseEntity<PessoaResponseDTO> register(@RequestBody PessoaRequestDTO pessoaRequestDTO) {
		PessoaResponseDTO pessoaResponseDTO = pessoaService.criarPessoa(pessoaRequestDTO);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}
