package com.example.demo.security;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
public class Mysecurity {
	
	@Bean
	public SecurityFilterChain securityConfigration(HttpSecurity http) throws Exception {
		http
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.csrf().disable()
		.cors().configurationSource(new CorsConfigurationSource() {
			
			@Override
			public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
				
				CorsConfiguration cfg=new CorsConfiguration();
				
				cfg.setAllowedOrigins(Collections.singletonList("*"));
				cfg.setAllowedMethods(Collections.singletonList("*"));
//				cfg.setAllowCredentials(true);
				cfg.setAllowedHeaders(Collections.singletonList("*"));
				cfg.setExposedHeaders(Arrays.asList("Authorization"));
				cfg.setMaxAge(3600L);
	
				return cfg;
			}
		})
		
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
