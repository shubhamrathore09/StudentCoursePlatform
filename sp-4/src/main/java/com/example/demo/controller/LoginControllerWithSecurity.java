package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.AdminDao;
import com.example.demo.dao.OwnerDao;
import com.example.demo.dao.StudentDao;
import com.example.demo.model.Admin;
import com.example.demo.model.Owner;
import com.example.demo.model.Student;


@RestController
public class LoginControllerWithSecurity {
	
	@Autowired
	private AdminDao adminDao;
	
	@Autowired
	private StudentDao studentDao;
	
	@Autowired
	private OwnerDao ownerDao;
	
	
	@GetMapping("/singIn")
	public ResponseEntity<String> LoginHandler(Authentication authentication){
		
		Student student=studentDao.findByMobile(authentication.getName());
		if(student==null) {
			Admin admin=adminDao.findByadminMobile(authentication.getName());
			if(admin==null) {
				Owner owner=ownerDao.findByownerMobile(authentication.getName());
				if(owner==null) {
					throw new BadCredentialsException("username and password don't matched");
				}
				return new ResponseEntity<>("successfully login",HttpStatus.ACCEPTED);
			}
			return new ResponseEntity<String>("successfully login",HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<String>("successfully login",HttpStatus.ACCEPTED);	
	}
}
