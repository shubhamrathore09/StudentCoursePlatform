package com.example.demo.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginDTO {
	
	@Pattern(regexp = "[0-9]{10}",message = "invalid number")
	private String mobile;
	@NotNull(message = "password is mandatory")
	private String password;
}
