package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.dao.AdminDao;
import com.example.demo.dao.OwnerDao;
import com.example.demo.dao.StudentDao;
import com.example.demo.model.Admin;
import com.example.demo.model.Owner;
import com.example.demo.model.Student;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
	
	@Autowired
	private StudentDao studentDao;
	
	@Autowired
	private AdminDao adminDao;
	
	@Autowired
	private OwnerDao ownerDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println(username);
		Student student=studentDao.findByMobile(username);
		if(student==null) {
			Admin admin=adminDao.findByadminMobile(username);
			if(admin==null) {
				Owner owner=ownerDao.findByownerMobile(username);
				if(owner==null) {
					throw new BadCredentialsException("username and password do not match");
				}
				List<GrantedAuthority> list=new ArrayList<>();
				list.add(new SimpleGrantedAuthority(owner.getRole()));
				return new User(username,owner.getOwnerPassword(),list);
			}
			List<GrantedAuthority> list=new ArrayList<>();
			list.add(new SimpleGrantedAuthority(admin.getRole()));
			return new User(username,admin.getPassword(),list);
		}
		List<GrantedAuthority> list=new ArrayList<>();
		list.add(new SimpleGrantedAuthority(student.getRole()));
		return new User(username,student.getPassword(),list);
	}

}
