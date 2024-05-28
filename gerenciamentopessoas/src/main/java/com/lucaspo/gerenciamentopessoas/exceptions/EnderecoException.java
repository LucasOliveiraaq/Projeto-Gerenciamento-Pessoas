package com.lucaspo.gerenciamentopessoas.exceptions;

public class EnderecoException extends RuntimeException{

	public EnderecoException() {
		super("Endereço não foi encontrado");
	}
	
	public EnderecoException(String message) {
		super(message);
	}
}
