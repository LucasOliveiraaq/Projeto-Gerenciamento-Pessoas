package com.lucaspo.gerenciamentopessoas.security.authentication;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.lucaspo.gerenciamentopessoas.security.details.UserDetailsImpl;

@Service
public class JwtTokenService {

	 private static final String SECRET_KEY = "Luc4sP0^@A";
	 private static final String ISSUER = "gerenciamentopessoas-api"; //Emissor do token
	 
	 public String generateToken(UserDetailsImpl user) {
		 try {
			Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
			return JWT.create()
					.withIssuer(ISSUER)
					.withIssuedAt(creationDate())
					.withExpiresAt(expirationDate())
					.withSubject(user.getUsername())
					.sign(algorithm); //assina o token 
		} catch (JWTCreationException exception) {
			throw new JWTCreationException("Erro ao gerar token.", exception);
		}
	 }
	 
	 public String getSubjectFromToken(String token) {
		 try {
			 Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
			 return JWT.require(algorithm)
					 .withIssuer(ISSUER)
					 .build()
					 .verify(token) //verifica a validade do token
					 .getSubject();
		} catch (JWTVerificationException exception) {
			throw new JWTVerificationException("Token inválido ou expirado.");
		}
	 }
	 
	 private Instant creationDate() {
		 return ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")).toInstant();
	 }
	 
	 private Instant expirationDate() {
		 return ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")).plusHours(4).toInstant();
	 }
}
