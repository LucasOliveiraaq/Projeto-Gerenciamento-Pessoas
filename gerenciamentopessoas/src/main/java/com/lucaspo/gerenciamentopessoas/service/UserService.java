package com.lucaspo.gerenciamentopessoas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.lucaspo.gerenciamentopessoas.dto.request.user.CreateUserDTO;
import com.lucaspo.gerenciamentopessoas.dto.request.user.LoginUserDTO;
import com.lucaspo.gerenciamentopessoas.dto.response.auth.RecuperarJwtTokenDTO;
import com.lucaspo.gerenciamentopessoas.model.Role;
import com.lucaspo.gerenciamentopessoas.model.User;
import com.lucaspo.gerenciamentopessoas.repositories.UserRepository;
import com.lucaspo.gerenciamentopessoas.security.authentication.JwtTokenService;
import com.lucaspo.gerenciamentopessoas.security.config.SecurityConfiguration;
import com.lucaspo.gerenciamentopessoas.security.details.UserDetailsImpl;

@Service
public class UserService {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenService jwtTokenService;
	
	@Autowired
	private SecurityConfiguration securityConfiguration;
	
	@Autowired
	private UserRepository userRepository;
	
	//Responsavel pela Autenticação
	public RecuperarJwtTokenDTO autenticarUser(LoginUserDTO loginUserDTO) {
		
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginUserDTO.getEmail(), loginUserDTO.getPassword());
		
		//Autenticar o Usuario com as credencias fornecidas
		Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		
		return new RecuperarJwtTokenDTO(jwtTokenService.generateToken(userDetails));
	}
	
	public void createUser(CreateUserDTO createUserDTO) {
		User newUser = User.builder()
							.email(createUserDTO.getEmail())
							.password(securityConfiguration.passwordEncoder().encode(createUserDTO.getPassword()))
							.roles(List.of(Role.builder().name(createUserDTO.getRole()).build()))
							.build();
		userRepository.save(newUser);
	}
}
