package com.lucaspo.gerenciamentopessoas.model;

import java.util.GregorianCalendar;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pessoa")
@Getter
@Setter
@NoArgsConstructor
public class Pessoa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	@Column(nullable = false)
	private String nomeCompleto;
	private GregorianCalendar dataDeNascimento;
	
	@Builder
	public Pessoa(String id, String nomeCompleto, GregorianCalendar dataDeNascimento) {
		this.id = id;
		this.nomeCompleto = nomeCompleto;
		this.dataDeNascimento = dataDeNascimento;
	}
	
	
}
