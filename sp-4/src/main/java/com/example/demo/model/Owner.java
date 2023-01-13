package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
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
public class Owner {
	@Id
	private Integer ownerId;
	private String ownerName;
	
	@Pattern(regexp = "[0-9]{10}",message = "invalid number")
	private String ownerMobile;
	
	@Size(min = 6,message = "password length should be 6")
	@NotNull(message = "null value not accepted")
	private String ownerPassword;
	
}
