package com.example.demo.service;

import java.util.List;
import java.util.Set;

import com.example.demo.exceptionHandler.CourseException;
import com.example.demo.exceptionHandler.LoginException;
import com.example.demo.exceptionHandler.StudentException;
import com.example.demo.model.Admin;
import com.example.demo.model.Course;
import com.example.demo.model.CurrentLoginSession;
import com.example.demo.model.PaidedStudent;

public interface AdminService {
	public Admin updateAdmin(Admin admin)throws LoginException;
	public Course createCourse(Course course)throws LoginException,CourseException;
	public Course UpdateCourse(Course course)throws LoginException,CourseException;
	public Set<PaidedStudent> studentByCourse(String courseName)throws StudentException,LoginException;
}
