package com.example.demo.Exceptions;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.context.request.WebRequest;

import com.example.demo.Model.UserExceptionCustom;

import org.hibernate.PropertyValueException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ControllerAdvice
public class UserException{
	
	  
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<?> handleConflict(DataIntegrityViolationException e, WebRequest request) {
		Map<String, String> result = new HashMap<>();
			  
	    result.put("message", e.getRootCause().getMessage());
	    	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
	}
	
	
	@ExceptionHandler(ServletException.class)
	public ResponseEntity<?> handleServletException(ServletException e, WebRequest request){
		Map<String, String> result = new HashMap<>();
		
		result.put("message", e.getMessage());
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, WebRequest request) {
		Map<String, String> result = new HashMap<>();
		
			 e.getBindingResult().getAllErrors().forEach((error) -> {
			        String fieldName = ((FieldError) error).getField();
			        String errorMessage;
			        
			        if(fieldName == "username") {
			        	errorMessage = "Username already exists";
			        }else{
			        	
			        errorMessage = error.getDefaultMessage();
			        }
			        result.put(fieldName, errorMessage);
			    });
			 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
			   
	}
	
	@ExceptionHandler(PropertyValueException.class)
	public ResponseEntity<?> handlePropertyValueException(PropertyValueException e, WebRequest request){
		
		Map<String, String> result = new HashMap<>();
		String fieldName = e.getPropertyName();
		String errorMessage = e.getMessage();
		result.put(fieldName, errorMessage);
		
		 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleException(Exception e, WebRequest request){
		Map<String, String> result = new HashMap<>();
		
		result.put("message", e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
	}
	
	@ExceptionHandler(UserExceptionCustom.class)
	public ResponseEntity<?> handleUserExceptionCustom(UserExceptionCustom e){
		Map<String, String> result = new HashMap<>();
		
		result.put("message", e.getMessage());
		return ResponseEntity.status(e.getStatus()).body(result);
	}
}
	
	



	