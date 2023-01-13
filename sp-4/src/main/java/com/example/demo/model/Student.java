package com.example.demo.model;

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
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer sid;
	@Size(max=20 ,message = "too big name allowed")
	@NotNull
	private String name;
	@NotNull
	private String address;
	@Pattern(regexp = "[0-9]{10}")
	private String mobile;
	@Email
	@Size(min=5)
	private String staudentEmail;
	@Size(min = 6,message = "password length should be 6")
	@NotNull(message = "null value not accepted")
	private String password;
	
}
