package com.javaweb.controllerAdvice;


import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.javaweb.Model.ErrorResponseDTO;

import customexception.FiledRequiredException;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {
 
	//check kiem tra loi phep tinh toan hoc
	@ExceptionHandler(ArithmeticException.class)
	public ResponseEntity<Object> handleArithmeticException(
		ArithmeticException ex,WebRequest request){
		
		ErrorResponseDTO dto=new ErrorResponseDTO();
		dto.setError(ex.getMessage());
		List<String> detail=new ArrayList<>();
		detail.add("Loi o mau do bang 0");
		dto.setDetai(detail);
		return new ResponseEntity<Object>(dto,HttpStatus.INTERNAL_SERVER_ERROR);	
	}
	
	
	@ExceptionHandler(FiledRequiredException.class)
	public ResponseEntity<Object> handleArithmeticException(
		FiledRequiredException ex,WebRequest request){
		
		ErrorResponseDTO dto=new ErrorResponseDTO();
		dto.setError(ex.getMessage());
		List<String> detail=new ArrayList<>();
		detail.add("Loi dang o nam hoac numberBasement day check lai di");
		dto.setDetai(detail);
		return new ResponseEntity<Object>(dto,HttpStatus.BAD_GATEWAY);	
	}
}
