package com.lucaspo.gerenciamentopessoas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucaspo.gerenciamentopessoas.dto.request.user.CreateUserDTO;
import com.lucaspo.gerenciamentopessoas.dto.request.user.LoginUserDTO;
import com.lucaspo.gerenciamentopessoas.dto.response.auth.RecuperarJwtTokenDTO;
import com.lucaspo.gerenciamentopessoas.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<RecuperarJwtTokenDTO> createUser(@RequestBody CreateUserDTO createUserDTO) {
		userService.createUser(createUserDTO);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<RecuperarJwtTokenDTO> autenticarUser(@RequestBody LoginUserDTO loginUserDTO) {
		RecuperarJwtTokenDTO token = userService.autenticarUser(loginUserDTO);
		return new ResponseEntity<>(token, HttpStatus.OK);
	}
}
