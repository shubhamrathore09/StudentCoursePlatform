package com.example.demo.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exceptionHandler.CourseException;
import com.example.demo.exceptionHandler.LoginException;
import com.example.demo.exceptionHandler.StudentException;
import com.example.demo.model.Admin;
import com.example.demo.model.Course;
import com.example.demo.model.PaidedStudent;
import com.example.demo.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	
	@Autowired
	private AdminService adminService;
	
	@PutMapping("/UpdateAdmin")
	public ResponseEntity<Admin> updateAdmin(Admin admin)throws LoginException{
		
		Admin admin2=adminService.updateAdmin(admin);
		return new ResponseEntity<Admin>(admin2,HttpStatus.OK);
	}
	
	@PostMapping("/createCourse")
	public ResponseEntity<Course> createNewCourse(@RequestBody Course course)throws LoginException,CourseException{
		
		Course course2=adminService.createCourse(course);
		return new ResponseEntity<Course>(course2,HttpStatus.CREATED);
	}
	
	@PutMapping("/updateCourse")
	public ResponseEntity<Course>updateCourse(@RequestBody Course course)throws LoginException,CourseException{
		Course course2=adminService.UpdateCourse(course);
		return new ResponseEntity<Course>(course2,HttpStatus.CREATED);
	}
	
	@GetMapping("/getstudent")
	public ResponseEntity<Set<PaidedStudent>> getCourseBy(@RequestParam("courseName")String name)throws StudentException,LoginException{
		Set<PaidedStudent> set=adminService.studentByCourse(name);
		return new ResponseEntity<Set<PaidedStudent>>(set,HttpStatus.OK);
	}
	
}
