package com.example.demo.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.demo.dao.AdminDao;
import com.example.demo.dao.CourseDao;
import com.example.demo.dao.CurrentSessionDao;
import com.example.demo.dao.OwnerDao;
import com.example.demo.exceptionHandler.CourseException;
import com.example.demo.exceptionHandler.LoginException;
import com.example.demo.exceptionHandler.StudentException;
import com.example.demo.model.Admin;
import com.example.demo.model.Course;
import com.example.demo.model.CurrentLoginSession;
import com.example.demo.model.Owner;
import com.example.demo.model.PaidedStudent;

@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private CurrentSessionDao currentSessionDao;
	
	@Autowired 
	private AdminDao adminDao;
	
	@Autowired 
	private CourseDao courseDao;

	@Override
	public Admin updateAdmin(Admin admin) throws LoginException {
		
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		
		if(authentication!=null) {
		return adminDao.save(admin);
		}
		
		throw new LoginException("First you need to login");
	}
	

	@Override
	public Course createCourse(Course course)throws LoginException,CourseException {
		
//		CurrentLoginSession currentLoginSession=currentSessionDao.findByUserkey(key);
		
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		
		
		
		if(authentication==null) {
			throw new LoginException("you need to login first");
		}
		
		Course course2=courseDao.findByCourseName(course.getCourseName());
		
		
		if(course2!=null) {
			throw new CourseException("That course is already exist");
		}
		
		return courseDao.save(course);
	}


	@Override
	public Course UpdateCourse(Course course) throws LoginException, CourseException {
		
	Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		if(authentication==null) {
			throw new LoginException("you need to login first");
		}
		Course course2=courseDao.findByCourseName(course.getCourseName());
		if(course2==null) {
			throw new CourseException("that course not available");
		}
		
		return courseDao.save(course);
		
	}


	@Override
	public Set<PaidedStudent> studentByCourse(String courseName) throws StudentException,LoginException {
		
	Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		if(authentication==null) {
			throw new LoginException("you need to login first");
		}
		
		
		Set<PaidedStudent> set=courseDao.givenBycourseName(courseName);
		
		if(set==null) {
			throw new StudentException("no student available");
		}
		
		return set;
	}
	


}
