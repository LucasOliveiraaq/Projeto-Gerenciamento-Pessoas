package com.lucaspo.gerenciamentopessoas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucaspo.gerenciamentopessoas.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, String>{
}
