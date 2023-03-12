package com.example.demo.service;

import com.example.demo.exceptionHandler.CourseException;
import com.example.demo.exceptionHandler.LoginException;
import com.example.demo.exceptionHandler.StudentException;
import com.example.demo.model.Course;
import com.example.demo.model.Student;
import java.util.*;

public interface StudentService {
	public String Ragistration(Student student)throws StudentException;
	public String ChangePassword(String oldpassword,String newPassword)throws StudentException;
	public List<Course> ShowAvailbleCourse()throws CourseException;
	public String EnrolledInCourse(String courseName)throws StudentException,CourseException;
}
