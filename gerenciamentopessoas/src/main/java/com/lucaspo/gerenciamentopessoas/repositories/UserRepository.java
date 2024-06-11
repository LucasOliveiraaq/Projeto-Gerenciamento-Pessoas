package com.lucaspo.gerenciamentopessoas.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucaspo.gerenciamentopessoas.model.User;

public interface UserRepository extends JpaRepository<User, String>{

	Optional<User> findByEmail(String email);
}
