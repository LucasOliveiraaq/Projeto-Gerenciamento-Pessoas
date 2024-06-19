package com.lucaspo.gerenciamentopessoas.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucaspo.gerenciamentopessoas.dto.request.endereco.EnderecoRequestDTO;
import com.lucaspo.gerenciamentopessoas.dto.response.endereco.EnderecoResponseDTO;
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
	
	@GetMapping("/pessoa/{pessoa_id}")
	public ResponseEntity<List<EnderecoResponseDTO>> findAllByPessoaId(@PathVariable String pessoa_id){
		List<EnderecoResponseDTO> list = enderecoService.findByPessoa_id(pessoa_id); 
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/pessoa/{pessoa_id}/principal")
	public ResponseEntity<EnderecoResponseDTO> findPrincipalByPessoaId(@PathVariable("pessoa_id") String pessoaId) {
	    EnderecoResponseDTO enderecoResponseDTO = enderecoService.findByPessoaIdAndPrincipalIsTrue(pessoaId);
	    return ResponseEntity.ok(enderecoResponseDTO);
	}
	
	@PatchMapping("/{enderecoId}/principal")
	public ResponseEntity<Void> cadastrarEnderecoPrincipal(@PathVariable String enderecoId){
		enderecoService.cadastrarEnderecoPrincipal(enderecoId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
