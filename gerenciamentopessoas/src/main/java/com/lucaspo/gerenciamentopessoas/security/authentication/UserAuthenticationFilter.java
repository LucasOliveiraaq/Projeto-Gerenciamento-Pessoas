package com.lucaspo.gerenciamentopessoas.security.authentication;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.lucaspo.gerenciamentopessoas.model.User;
import com.lucaspo.gerenciamentopessoas.repositories.UserRepository;
import com.lucaspo.gerenciamentopessoas.security.config.SecurityConfiguration;
import com.lucaspo.gerenciamentopessoas.security.details.UserDetailsImpl;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class UserAuthenticationFilter extends OncePerRequestFilter{

	@Autowired
	private JwtTokenService jwtTokenService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		if(verificaEndpointIsNotPublic(request)) {
			String token = recuperarToken(request);
			if(token != null) {
				String subject = jwtTokenService.getSubjectFromToken(token); // obtem o assunto do token
				User user = userRepository.findByEmail(subject).get(); // busca usuario pelo email, pelo assunto do token
				UserDetailsImpl userDetails = new UserDetailsImpl(user);
				//cria um objeto de autenticação do Spring Security 
				Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());
				//define o objeto de autenticação no contexto de securança do Spring Security
				SecurityContextHolder.getContext().setAuthentication(authentication);
			} else {
				throw new RuntimeException("O token está ausente");
			}
		}
		filterChain.doFilter(request, response); //continua o processamento da requisição
	}
	
	private String recuperarToken(HttpServletRequest request) {
		String authorizationHeader = request.getHeader("Authorization");
		if(authorizationHeader != null) {
			return authorizationHeader.replace("Bearer", "");
		}
		return null;
	}

	//verifica se enfpoint querer autenticação antes de processar a requisição.
	private boolean verificaEndpointIsNotPublic(HttpServletRequest request) {
		String requestURI = request.getRequestURI();
		return !Arrays.asList(SecurityConfiguration.ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED).contains(requestURI);
	}
}
