package com.example.demo.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaidedStudent {
	
	@Id
	private Integer sid;
	private String name;
	private String address;
	private String mobile;
	private String staudentEmail;
	private String password;
	
	@JsonIgnore
	@ManyToMany( cascade=CascadeType.ALL)
	private Set<Course> courseSet=new HashSet<>();

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PaidedStudent other = (PaidedStudent) obj;
		return Objects.equals(mobile, other.mobile) && Objects.equals(sid, other.sid);
	}

	@Override
	public int hashCode() {
		return Objects.hash(mobile, sid);
	}


	
	


	
	
	
}
