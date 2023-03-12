package com.example.demo.security;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtTokenGenerator extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		
		if(authentication!=null) {
			SecretKey key=Keys.hmacShaKeyFor(SecurityContext.jwt_key.getBytes());
			
			String jwt=Jwts.builder()
					       .setIssuer("shubham")
					       .setSubject("Student")
					       .claim("username", authentication.getName())
					       .claim("role", roles(authentication.getAuthorities()))
					       .setIssuedAt(new Date())
					       .setExpiration(new Date(new Date().getTime()+30000000))
					       .signWith(key).compact();
			
		
			response.setHeader(SecurityContext.jwt_Header, jwt);
		}
		
		filterChain.doFilter(request, response);
		
	}
	public String roles(Collection<?extends GrantedAuthority> list) {
		String role="";
		for(GrantedAuthority i:list) {
			role=i.getAuthority();
			break;
		}
		return role;
	}
	
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		return !request.getServletPath().equals("/singIn");
	}
	
	

}
