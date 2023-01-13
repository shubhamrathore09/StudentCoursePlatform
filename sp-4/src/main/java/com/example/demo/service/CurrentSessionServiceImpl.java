package com.example.demo.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.AdminDao;
import com.example.demo.dao.CurrentSessionDao;
import com.example.demo.dao.OwnerDao;
import com.example.demo.dao.StudentDao;
import com.example.demo.exceptionHandler.LoginException;
import com.example.demo.model.Admin;
import com.example.demo.model.CurrentLoginSession;
import com.example.demo.model.LoginDTO;
import com.example.demo.model.Owner;
import com.example.demo.model.Student;

import net.bytebuddy.utility.RandomString;

@Service
public class CurrentSessionServiceImpl implements CurrentSessionService{
	
	@Autowired
	private CurrentSessionDao currentSessionDao;
	
	@Autowired
	private OwnerDao ownerDao;
	
	@Autowired
	private AdminDao adminDao;
	
	@Autowired 
	private StudentDao studentDao;
	
	
	@Override
	public String logIn(LoginDTO loginDTO) throws LoginException {
		CurrentLoginSession currentLoginSession=currentSessionDao.findByMobile(loginDTO.getMobile());
		
		if(currentLoginSession!=null) {
			throw new LoginException("user Already Login");
		}
		
		Owner owner=ownerDao.findByownerMobile(loginDTO.getMobile());
		if(owner==null) {
			Admin admin=adminDao.findByadminMobile(loginDTO.getMobile());
			if(admin==null) {
				
				Student student=studentDao.findByMobile(loginDTO.getMobile());
				
				if(student==null) {
					throw new LoginException("This is number do not exist");
				}
				else {
					if(student.getPassword().equals(loginDTO.getPassword())){
						String key=RandomString.make(6);
						CurrentLoginSession currentLoginSession2=new CurrentLoginSession();
						currentLoginSession2.setMobile(student.getMobile());
						currentLoginSession2.setUserkey(key);
						currentSessionDao.save(currentLoginSession2);
						return "login succefully : "+key;
					}
					return "Wrong password";
				}	
			}
			
			else {
				
				if(admin.getPassword().equals(loginDTO.getPassword())) {
					String key=RandomString.make(6);
					
					CurrentLoginSession currentLoginSession2=new CurrentLoginSession();
					currentLoginSession2.setMobile(loginDTO.getMobile());
					currentLoginSession2.setUserkey(key);
					currentSessionDao.save(currentLoginSession2);
					return "login succefully : "+key;
				}
				
					return "wrong passrod";	
			}	
		}
		
		else {
			
			if(owner.getOwnerPassword().equals(loginDTO.getPassword())) {
				String key=RandomString.make(6);
				
				CurrentLoginSession currentLoginSession2=new CurrentLoginSession();
				currentLoginSession2.setMobile(loginDTO.getMobile());
				currentLoginSession2.setUserkey(key);
				
				currentSessionDao.save(currentLoginSession2);
				return "login succefully : "+key;
			}
				return "wrong passrod";	
		}
		
	}
	
	@Override
	public String LogOut(String key) throws LoginException {
		CurrentLoginSession currentLoginSession=currentSessionDao.findByUserkey(key);
		currentSessionDao.delete(currentLoginSession);
		return "LogOut Successfully";
	}

}
