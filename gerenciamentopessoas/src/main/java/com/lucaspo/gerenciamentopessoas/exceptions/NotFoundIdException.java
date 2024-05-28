package com.lucaspo.gerenciamentopessoas.exceptions;

public class NotFoundIdException extends RuntimeException{
	
	public NotFoundIdException() {
		super("Id não foi encontrado");
	}
	
	public NotFoundIdException(String message) {
		super(message);
	}
}
