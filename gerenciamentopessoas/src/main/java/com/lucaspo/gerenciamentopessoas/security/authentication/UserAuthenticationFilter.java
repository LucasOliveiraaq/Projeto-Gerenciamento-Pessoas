package com.lucaspo.gerenciamentopessoas.security.authentication;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

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
public class UserAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (verificaEndpointIsNotPublic(request)) {
            String token = recuperarToken(request);
            if (token != null) {
                String subject = jwtTokenService.getSubjectFromToken(token); // obtem o assunto do token
                Optional<User> optionalUser = userRepository.findByEmail(subject); // busca usuario pelo email, pelo assunto do token

                if (optionalUser.isPresent()) {
                    User user = optionalUser.get();
                    UserDetailsImpl userDetails = new UserDetailsImpl(user);
                    // cria um objeto de autenticação do Spring Security 
                    Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    // define o objeto de autenticação no contexto de segurança do Spring Security
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    System.out.println("Authentication successful for user: " + user.getEmail());
                } else {
                    System.out.println("User not found for subject: " + subject);
                }
            } else {
                System.out.println("Token is missing in the request.");
            }
        }
        filterChain.doFilter(request, response); // continua o processamento da requisição
    }

    private String recuperarToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.replace("Bearer ", "").trim();
        }
        return null;
    }

    // verifica se endpoint requer autenticação antes de processar a requisição.
    private boolean verificaEndpointIsNotPublic(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return !Arrays.asList(SecurityConfiguration.ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED).contains(requestURI);
    }
}
