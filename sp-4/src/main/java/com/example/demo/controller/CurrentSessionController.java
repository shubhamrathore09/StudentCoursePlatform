package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exceptionHandler.LoginException;
import com.example.demo.model.CurrentLoginSession;
import com.example.demo.model.LoginDTO;
import com.example.demo.service.CurrentSessionService;

@RestController
public class CurrentSessionController {
	
	@Autowired
	private CurrentSessionService currentSessionService;
	
	
	@PostMapping("/logIn")
	public ResponseEntity<String> LogInSystem(LoginDTO loginDTO)throws LoginException{
		String msg=currentSessionService.logIn(loginDTO);
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
	
	@PostMapping("/logOut")
	public ResponseEntity<String>Logout(String key)throws LoginException{
		String msg=currentSessionService.LogOut(key);
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
	
}
