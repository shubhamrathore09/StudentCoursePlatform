package com.example.demo.exceptionHandler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.example.demo.model.Myerror;

@ControllerAdvice
public class GlobleException {
	
	
	@ExceptionHandler(LoginException.class)
	public ResponseEntity<Myerror> exceptionhand(LoginException loginException,WebRequest request){
		Myerror myerror=new Myerror(LocalDateTime.now(), loginException.getMessage(), request.getDescription(false));
		return new ResponseEntity<Myerror>(myerror,HttpStatus.BAD_GATEWAY);
	}
	
	
	@ExceptionHandler(StudentException.class)
	public ResponseEntity<Myerror> exceptionhandle(StudentException studentException,WebRequest request){
		Myerror myerror=new Myerror(LocalDateTime.now(), studentException.getMessage(), request.getDescription(false));
		return new ResponseEntity<Myerror>(myerror,HttpStatus.BAD_GATEWAY);
	}
	
	@ExceptionHandler(AdminException.class)
	public ResponseEntity<Myerror> exceptionhandle(AdminException adminException,WebRequest request){
		Myerror myerror=new Myerror(LocalDateTime.now(), adminException.getMessage(), request.getDescription(false));
		return new ResponseEntity<Myerror>(myerror,HttpStatus.BAD_GATEWAY);
	}
	
	@ExceptionHandler(CourseException.class)
	public ResponseEntity<Myerror> exceptionhandle(CourseException courseException,WebRequest request){
		Myerror myerror=new Myerror(LocalDateTime.now(), courseException.getMessage(), request.getDescription(false));
		return new ResponseEntity<Myerror>(myerror,HttpStatus.BAD_GATEWAY);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Myerror> validationerror(MethodArgumentNotValidException argumentNotValidException){
		Myerror myerror=new Myerror(LocalDateTime.now(),"Validation Error", argumentNotValidException.getBindingResult().getFieldError().getDefaultMessage());
		return new ResponseEntity<Myerror>(myerror,HttpStatus.BAD_GATEWAY);
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<Myerror>NotFounder(NoHandlerFoundException foundException,WebRequest request){
		Myerror myerror=new Myerror(LocalDateTime.now(), foundException.getMessage(), request.getDescription(false));
		return new ResponseEntity<Myerror>(myerror,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Myerror> AlltypeException(Exception ei,WebRequest request){
		Myerror myerror=new Myerror(LocalDateTime.now(), ei.getMessage(), request.getDescription(false));
		return new ResponseEntity<Myerror>(myerror,HttpStatus.BAD_GATEWAY);
	}
	
}
