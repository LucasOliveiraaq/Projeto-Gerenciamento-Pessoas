package com.lucaspo.gerenciamentopessoas.model;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "endereco")
@Getter
@Setter
@NoArgsConstructor
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	@Column(nullable =  false)
	private String logradouro;
	@Column(length = 8, nullable = false)
	private String cep;
	@Column(nullable =  false)
	private int numero;
	@Column(length = 58, nullable = false)
	private String cidade;
	@Column(length = 2, nullable = false) //enviar o estado com abreviação
	private String estado;
	@ManyToOne
	@JoinColumn(name = "pessoa_id", nullable = false)
	private Pessoa pessoa;
	private boolean principal;
	
	@Builder
	public Endereco(String id, String logradouro, String cep, int numero, String cidade, String estado, Pessoa pessoa, boolean principal) {
		this.id = id;
		this.logradouro = logradouro;
		this.cep = cep;
		this.numero = numero;
		this.cidade = cidade;
		this.estado = estado;
		this.pessoa = pessoa;
		this.principal = principal;
	}
	
	
}
