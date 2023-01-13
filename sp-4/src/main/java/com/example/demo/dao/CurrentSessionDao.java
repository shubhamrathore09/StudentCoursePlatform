package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.CurrentLoginSession;

public interface CurrentSessionDao extends JpaRepository<CurrentLoginSession, Integer> {
	public CurrentLoginSession findByUserkey(String userkey);
	public CurrentLoginSession findByMobile(String mobile);
}
