package com.example.demo.service;

import com.example.demo.exceptionHandler.LoginException;
import com.example.demo.model.LoginDTO;

public interface CurrentSessionService {
	public String logIn(LoginDTO loginDTO)throws LoginException;
	public String LogOut(String key)throws LoginException;
}
