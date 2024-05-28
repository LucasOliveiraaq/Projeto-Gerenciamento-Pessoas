package com.lucaspo.gerenciamentopessoas.infra;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.lucaspo.gerenciamentopessoas.exceptions.EnderecoException;
import com.lucaspo.gerenciamentopessoas.exceptions.NotFoundIdException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler{

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(DataIntegrityViolationException.class)
	private ApllicationErrorMessage dataIntegrityViolationException(DataIntegrityViolationException e) {
		return new ApllicationErrorMessage(HttpStatus.BAD_REQUEST, "Campo Obrigatório não informado");
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(NotFoundIdException.class)
	private ApllicationErrorMessage notFoundIdException(NotFoundIdException e) {
		return new ApllicationErrorMessage(HttpStatus.BAD_REQUEST, e.getMessage());
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(EnderecoException.class)
	private ApllicationErrorMessage enderecoException(EnderecoException e) {
		return new ApllicationErrorMessage(HttpStatus.BAD_REQUEST, e.getMessage());
	}
}
