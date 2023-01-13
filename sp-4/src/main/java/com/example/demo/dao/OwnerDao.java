package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Owner;

public interface OwnerDao extends JpaRepository<Owner, Integer>{
	public Owner findByownerMobile(String mobile);

}
