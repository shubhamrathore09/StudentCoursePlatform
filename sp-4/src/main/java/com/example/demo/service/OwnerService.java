package com.example.demo.service;

import com.example.demo.exceptionHandler.AdminException;
import com.example.demo.exceptionHandler.LoginException;
import com.example.demo.model.Admin;
import com.example.demo.model.Owner;

public interface OwnerService {
	public Owner createOwner(Owner owner);
	public Owner updateOwner(Owner owner,String key)throws LoginException;
	public Admin createAdmin(Admin admin,String key)throws LoginException,AdminException;
	public String deleteAdmin(Integer aid,String key)throws AdminException,LoginException;
}
