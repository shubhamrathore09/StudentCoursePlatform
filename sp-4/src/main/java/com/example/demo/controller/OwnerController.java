package com.example.demo.controller;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.AdminDao;
import com.example.demo.exceptionHandler.AdminException;
import com.example.demo.exceptionHandler.LoginException;
import com.example.demo.model.Admin;
import com.example.demo.model.Owner;
import com.example.demo.service.CurrentSessionService;
import com.example.demo.service.OwnerService;

@RestController
public class OwnerController {
	
	@Autowired 
	private OwnerService ownerService;
	
	@PostConstruct
	public void AddOwner() {
		Owner owner=new Owner();
		
	}

	
	@PostMapping("/owner")
	public ResponseEntity<Owner> uploadOwner(@Valid @RequestBody Owner owner){
		Owner owner2=ownerService.createOwner(owner);
		return new ResponseEntity<Owner>(owner2,HttpStatus.CREATED);
	}
	
	@PutMapping("/modify")
	public ResponseEntity<Owner> modify(@Valid @RequestBody Owner owner,@RequestParam("key")String key)throws LoginException{
		Owner owner2=ownerService.updateOwner(owner,key);
		return new ResponseEntity<Owner>(owner2,HttpStatus.CREATED);
	}
	
	@PostMapping("/Admin")
	public ResponseEntity<Admin> CreateAdmin(@Valid @RequestBody Admin admin,@RequestParam String key)throws LoginException,AdminException{
		Admin admin2=ownerService.createAdmin(admin,key);
		return new ResponseEntity<Admin>(admin,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/adminDelete/{aid}")
	public ResponseEntity<String> deleteById(@PathVariable("aid")Integer aid,@RequestParam String key) throws AdminException,LoginException{
		String msg=ownerService.deleteAdmin(aid,key);
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}
	
}
