package com.example.demo.service;

import com.example.demo.exceptionHandler.AdminException;
import com.example.demo.exceptionHandler.LoginException;
import com.example.demo.model.Admin;
import com.example.demo.model.Owner;

public interface OwnerService {
	public Owner createOwner(Owner owner);
	public Owner updateOwner(Owner owner)throws LoginException;
	public Admin createAdmin(Admin admin)throws LoginException,AdminException;
	public String deleteAdmin(Integer aid)throws AdminException,LoginException;
}
