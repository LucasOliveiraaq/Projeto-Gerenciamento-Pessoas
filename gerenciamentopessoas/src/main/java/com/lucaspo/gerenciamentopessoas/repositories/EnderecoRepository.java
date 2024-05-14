package com.lucaspo.gerenciamentopessoas.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucaspo.gerenciamentopessoas.model.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, String>{

	List<Endereco> findByPessoa_id(String pessoa_id);
	List<Endereco> findByPessoaIdAndPrincipalIsTrue(String pessoa_id);
}
