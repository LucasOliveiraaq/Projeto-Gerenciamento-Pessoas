package com.lucaspo.gerenciamentopessoas.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucaspo.gerenciamentopessoas.dto.request.EnderecoRequestDTO;
import com.lucaspo.gerenciamentopessoas.dto.response.EnderecoResponseDTO;
import com.lucaspo.gerenciamentopessoas.service.EnderecoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/endereco")
@RequiredArgsConstructor
public class EnderecoController {

	private final EnderecoService enderecoService;
	
	@PostMapping
	public ResponseEntity<EnderecoResponseDTO> register(@RequestBody EnderecoRequestDTO enderecoRequestDTO){
		EnderecoResponseDTO enderecoResponseDTO = enderecoService.criarEndereco(enderecoRequestDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(enderecoResponseDTO);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<EnderecoResponseDTO> findByEnderecoId(@PathVariable String id){
		return ResponseEntity.ok().body(enderecoService.findById(id));
	}
	
	@GetMapping
	public ResponseEntity<List<EnderecoResponseDTO>> findAll() {
		return ResponseEntity.ok().body(enderecoService.findAll());
	}
}
