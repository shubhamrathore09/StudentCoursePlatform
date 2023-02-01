package com.example.demo.model;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.UniqueElements;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Admin {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer aid;
	@Size(min = 3,max = 12,message = "name length should be between 3 12")
	@NotNull
	private String adminName;
	@Email
	@Size(min=5)
	private String adminEmail;
	
	
	@Pattern(regexp = "^[6789][0-9]{9}")
	private String adminMobile;
	
	@Size(min = 6,message = "password length should be 6")
	@NotNull(message = "null value not accepted")
	private String password;
	
}
