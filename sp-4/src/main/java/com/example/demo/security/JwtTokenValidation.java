package com.example.demo.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtTokenValidation extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String jwt=request.getHeader(SecurityContext.jwt_Header);
		
		if(jwt != null) {
			try {
			
			jwt=jwt.substring(7);
			
			SecretKey key=Keys.hmacShaKeyFor(SecurityContext.jwt_key.getBytes());
			
			
			Claims claims=Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
			
				String username=String.valueOf(claims.get("username"));
				
				
				
				String role=(String)claims.get("role");
				
				
				
				
				List<GrantedAuthority> list=new  ArrayList<>();
				list.add(new SimpleGrantedAuthority(role));
				
				Authentication authentication=new UsernamePasswordAuthenticationToken(username,null,list);
				
				SecurityContextHolder.getContext().setAuthentication(authentication);
				
			}catch (Exception e) {
				// TODO: handle exception
				throw new BadCredentialsException("invalid username and password");
			}
		}
		
		filterChain.doFilter(request, response);
		
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		// TODO Auto-generated method stub
		return request.getServletPath().equals("/singIn");
	}
	
	

}
