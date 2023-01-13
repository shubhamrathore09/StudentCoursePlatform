package com.example.demo.dao;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Course;
import com.example.demo.model.PaidedStudent;

public interface CourseDao extends JpaRepository<Course,Integer>{
	public Course findByCourseName(String courseName);
	
	@Query("select c.studentset from Course c where c.courseName=?1")
	public Set<PaidedStudent> givenBycourseName(String courseName);
}
