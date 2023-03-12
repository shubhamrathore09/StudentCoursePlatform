package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exceptionHandler.CourseException;
import com.example.demo.exceptionHandler.StudentException;
import com.example.demo.model.Course;
import com.example.demo.model.Student;
import com.example.demo.service.StudentService;


import java.util.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/student")
public class StudentController {
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
		
	
	@PostMapping("/Ragistration")
	public ResponseEntity<String> Ragistration(@Valid @RequestBody Student student)throws StudentException{
		
		student.setPassword(passwordEncoder.encode(student.getPassword()));
		student.setRole("ROLE_STUDENT");
		String msg=studentService.Ragistration(student);
		return new ResponseEntity<String>(msg,HttpStatus.CREATED);
	}
	
	@PutMapping("/PasswordChange")
	public ResponseEntity<String>ChangePassword(@RequestParam("oldpawword")String oldpassword,@RequestParam("newpassword")String newpassword)throws StudentException{
		String msg=studentService.ChangePassword(oldpassword ,newpassword);
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	};
	
	
	@GetMapping("/getAllCourse")
	public ResponseEntity<List<Course>> getAllCourse()throws CourseException{
		List<Course> list=studentService.ShowAvailbleCourse();
		return new ResponseEntity<List<Course>>(list,HttpStatus.OK);
	}
	
	@PostMapping("/enrolled")
	public ResponseEntity<String>Enrolled(@RequestParam("course")String cname)throws StudentException,CourseException{
		String msg=studentService.EnrolledInCourse(cname);
		return new ResponseEntity<String>(msg,HttpStatus.CREATED);
	}
	
	
}
