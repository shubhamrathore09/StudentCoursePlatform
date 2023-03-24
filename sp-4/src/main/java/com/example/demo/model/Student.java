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

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	@Pattern(regexp = "^[6789][0-9]{9}")
	private String mobile;
	@Email
	@Size(min=5)
	private String staudentEmail;
	
	@NotNull(message = "null value not accepted")
	private String password;
	
	@JsonIgnore
	private String role;
	
}
