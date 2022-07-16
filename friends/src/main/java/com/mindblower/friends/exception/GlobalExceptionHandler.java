package com.mindblower.friends.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
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
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exception) {
		
		Map<String, String> errorRes = new HashMap<>();
		
		exception.getBindingResult().getAllErrors().forEach((error)->{
			String fieldName = ((FieldError)error).getField();
			String message = error.getDefaultMessage();
			errorRes.put(fieldName, message);
		});
		
		return new ResponseEntity<Map<String,String>>(errorRes,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UserTokenNotFoundException.class)
	public ResponseEntity<Response> UserTokenNotFoundExceptionHandler(UserTokenNotFoundException exception) {
		
		String message = exception.getMessage();
		
		Response response= new Response(message, false);
		return new ResponseEntity<Response>(response,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MissingRequestHeaderException.class)
	public ResponseEntity<Response> MissingRequestHeaderExceptionHandler(MissingRequestHeaderException exception) {
		
		String message = exception.getMessage();
		
		Response response= new Response(message, false);
		return new ResponseEntity<Response>(response,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<Response> MissingServletRequestParameterExceptionHandler(MissingServletRequestParameterException exception) {
		
		String message = exception.getMessage();
		
		Response response= new Response(message, false);
		return new ResponseEntity<Response>(response,HttpStatus.NOT_FOUND);
	}
	
}
