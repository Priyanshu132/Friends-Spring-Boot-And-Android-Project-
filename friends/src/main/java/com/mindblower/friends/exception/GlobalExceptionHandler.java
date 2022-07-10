package com.mindblower.friends.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindblower.friends.reponse.Response;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Response> ResourceNotFoundExceptionHandler(ResourceNotFoundException exception) {
		
		String message = exception.getMessage();
		
		Response response= new Response(message, false);
		return new ResponseEntity<Response>(response,HttpStatus.NOT_FOUND);
	}
}
