package com.example.demo.security;

import java.lang.reflect.Method;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class Mysecurity {
	
	@Bean
	public SecurityFilterChain securityConfigration(HttpSecurity http) throws Exception {
		http
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.authorizeHttpRequests()
		.antMatchers("/student/Ragistration","/owners/owner").permitAll()
		.antMatchers("/student/**").hasRole("STUDENT")
		.antMatchers("/admin/**").hasRole("ADMIN")
		.antMatchers("/owners/**").hasRole("OWNER")
		.anyRequest()
		.authenticated()
		.and()
		.addFilterAfter(new JwtTokenGenerator(), BasicAuthenticationFilter.class)
		.addFilterBefore(new JwtTokenValidation(),BasicAuthenticationFilter.class)
		.csrf().disable()
		.formLogin()
		.and()
		.httpBasic();
		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
