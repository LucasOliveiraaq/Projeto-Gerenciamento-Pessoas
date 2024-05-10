package com.lucaspo.gerenciamentopessoas.model;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "endereco")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	private String logradouro;
	private String cep;
	private int numero;
	private String cidade;
	private String estado;
	@ManyToOne
	private Pessoa pessoa;
}
