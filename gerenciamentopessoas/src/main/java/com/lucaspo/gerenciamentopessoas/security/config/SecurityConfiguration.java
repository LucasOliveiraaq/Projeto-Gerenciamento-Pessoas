package com.lucaspo.gerenciamentopessoas.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.lucaspo.gerenciamentopessoas.security.authentication.UserAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
	@Autowired
	private UserAuthenticationFilter userAuthenticationFilter;

	public static final String [] ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED = {
			"/users/login",
			"/users"
	};
	
	//Endpoints que requerem autenticação para serem acessados.
	public static final String [] ENDPOINTS_WITH_AUTHENTICATION_REQUIRED = {
			//verificar dps
	};
	
	//Endpoints que podem ser acessado por usuario com permisão de clientes.
	public static final String[] ENDPOINTS_CUSTOMER = {
	};
	
	//Endpoints que podem ser acessado por usuario com permisão de adm.
	public static final String [] ENDPOINTS_ADMIN = {
	};
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity.csrf().disable() //desativa a proteção contra CSRF
					.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //criação de sessão como STATELESS
					.and().authorizeHttpRequests() // habilita a autorização para requisições http
					.requestMatchers(ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED).permitAll()
					.requestMatchers(ENDPOINTS_WITH_AUTHENTICATION_REQUIRED).authenticated()
					.requestMatchers(ENDPOINTS_ADMIN).hasRole("ADMINISTRATOR") //somente adm
					.requestMatchers(ENDPOINTS_CUSTOMER).hasRole("CUSTOMER") //somente usuario
					.anyRequest().denyAll()
					.and().addFilterBefore(userAuthenticationFilter, UserAuthenticationFilter.class) // filtro de autenticação do usuario
					.build();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
 }
