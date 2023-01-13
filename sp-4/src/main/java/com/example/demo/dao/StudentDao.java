package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Student;

public interface StudentDao extends JpaRepository<Student, Integer>{
	public Student findByMobile(String mobile);
}
