package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.AdminDao;
import com.example.demo.dao.CurrentSessionDao;
import com.example.demo.dao.OwnerDao;
import com.example.demo.exceptionHandler.AdminException;
import com.example.demo.exceptionHandler.LoginException;
import com.example.demo.model.Admin;
import com.example.demo.model.CurrentLoginSession;
import com.example.demo.model.Owner;

@Service
public class OwnerServiceImpl implements OwnerService{

	@Autowired
	private OwnerDao ownerDao;
	
	@Autowired
	private AdminDao adminDao;
	
	@Autowired
	private CurrentSessionDao currentSessionDao;
	
	@Override
	public Owner createOwner(Owner owner) {
		return ownerDao.save(owner);
	}

	@Override
	public Owner updateOwner(Owner owner,String key)throws LoginException {
		
		CurrentLoginSession currenuser=currentSessionDao.findByUserkey(key);
		if(currenuser==null) {
			throw new LoginException("first you need to login");
		}
		
		Optional<Owner> optional=ownerDao.findById(owner.getOwnerId());
		
	if(optional.isPresent()) {
	
		return ownerDao.save(owner);
	}
		return null;
	}

	@Override
	public Admin createAdmin(Admin admin,String key) throws LoginException,AdminException{
		
		CurrentLoginSession currenuser=currentSessionDao.findByUserkey(key);
		if(currenuser==null) {
			throw new LoginException("first you need to login");
		}
		
		Admin admin2=adminDao.findByadminMobile(admin.getAdminMobile());
		if(admin2!=null) {
			throw new AdminException("this number is already ragistor");
		}
		Admin admin3=adminDao.save(admin);
		return admin3;
	}

	@Override
	public String deleteAdmin(Integer aid,String key) throws AdminException ,LoginException{
		
		CurrentLoginSession currenuser=currentSessionDao.findByUserkey(key);
		if(currenuser==null) {
			throw new LoginException("first you need to login");
		}
		
		Optional<Admin> optional=adminDao.findById(aid);
		if(optional.isEmpty()) {
			throw new AdminException("no admin available by that id :"+aid);
		}
		Admin admin=optional.get();
		adminDao.delete(admin);
		return "succesefully delete";
	}

}
