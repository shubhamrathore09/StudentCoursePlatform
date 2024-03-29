package com.example.demo.service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dao.CourseDao;
import com.example.demo.dao.CurrentSessionDao;
import com.example.demo.dao.PaidStudentDao;
import com.example.demo.dao.StudentDao;
import com.example.demo.exceptionHandler.CourseException;
import com.example.demo.exceptionHandler.LoginException;
import com.example.demo.exceptionHandler.StudentException;
import com.example.demo.model.Course;
import com.example.demo.model.CurrentLoginSession;
import com.example.demo.model.PaidedStudent;
import com.example.demo.model.Student;

@Service
public class StudentServiceImpl implements StudentService{
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private StudentDao studentDao;

	@Autowired
	private CourseDao courseDao;
	
	@Autowired
	private CurrentSessionDao currentSessionDao;
	
	@Autowired
	private PaidStudentDao paidStudentDao;
	
		
	@Override
	public String Ragistration(Student student)throws StudentException {
		
		Student student2=studentDao.findByMobile(student.getMobile());
		
		if(student2!=null) {
			throw new StudentException("student already ragistor with this number");
		}
		 studentDao.save(student);
		 return "Ragistration done successfully";
	}

	@Override
	public String ChangePassword(String oldpassword,String newPassword) throws StudentException {
		
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		Student student=studentDao.findByMobile(authentication.getName());
		
		if(encoder.matches(oldpassword, student.getPassword())) {
			student.setPassword(encoder.encode(newPassword));
			studentDao.save(student);
			return "password save succesfully";
		}
		
		throw new StudentException("wrong password");
		
		 
		
	}
	@Override
	public List<Course> ShowAvailbleCourse() throws CourseException {
		
		List<Course> list=courseDao.findAll();
		return list;
	}

	@Override
	public String EnrolledInCourse(String courseName)throws StudentException,CourseException {
		
		
		
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		
		Student student=studentDao.findByMobile(authentication.getName());
		
		Course course=courseDao.findByCourseName(courseName);
		
		PaidedStudent paidedStudent1=paidStudentDao.findByMobile(student.getMobile());
		
		if(paidedStudent1!=null) {
			paidedStudent1.getCourseSet().add(course);
			course.getStudentset().add(paidedStudent1);
			paidStudentDao.save(paidedStudent1);
			return "you have succefully enrrolled in course";
		}
		
		
		PaidedStudent paidedStudent=new PaidedStudent();
		paidedStudent.setAddress(student.getAddress());
		paidedStudent.setMobile(student.getMobile());
		paidedStudent.setName(student.getName());
		paidedStudent.setPassword(student.getPassword());
		paidedStudent.setSid(student.getSid());
		paidedStudent.setStaudentEmail(student.getStaudentEmail());
		
		paidedStudent.getCourseSet().add(course);
		
		course.getStudentset().add(paidedStudent);
		
		paidStudentDao.save(paidedStudent);
		
		return "you have succefully enrrolled in course";
		
	}

}
