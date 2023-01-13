package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.PaidedStudent;

public interface PaidStudentDao extends JpaRepository<PaidedStudent, Integer>{
	
	public PaidedStudent findByMobile(String mobile);

}
