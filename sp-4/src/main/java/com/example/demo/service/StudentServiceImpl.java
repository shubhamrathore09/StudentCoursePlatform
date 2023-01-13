package com.example.demo.service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

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
	public String ChangePassword(String key,String oldpassword,String newPassword) throws StudentException {
		
	CurrentLoginSession currentLoginSession=currentSessionDao.findByUserkey(key);
	
	if(currentLoginSession==null) {
		throw new StudentException("your are not login");
	}
	
	Student student=studentDao.findByMobile(currentLoginSession.getMobile());

	if(student.getPassword().equals(oldpassword)) {
		student.setPassword(newPassword);
		studentDao.save(student);
		return "Password successfully changed";
	}

		return "Your old password is incurrect";
		
	}
	@Override
	public List<Course> ShowAvailbleCourse() throws CourseException {
		
		List<Course> list=courseDao.findAll();
		return list;
	}

	@Override
	public String EnrolledInCourse(String courseName, String key)throws StudentException,CourseException {
		
		CurrentLoginSession currentLoginSession=currentSessionDao.findByUserkey(key);
		
		if(currentLoginSession==null) {
			throw new StudentException("you need to login");
		}
		
		
		Student student=studentDao.findByMobile(currentLoginSession.getMobile());
		
		Course course=courseDao.findByCourseName(courseName);
		
		PaidedStudent paidedStudent1=paidStudentDao.findByMobile(student.getMobile());
		
		if(paidedStudent1!=null) {
			paidedStudent1.getCourseSet().add(course);
			course.getStudentset().add(paidedStudent1);
			paidStudentDao.save(paidedStudent1);
			return "Done";
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
