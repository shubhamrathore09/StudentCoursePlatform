package com.example.demo.model;


import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
//import java.util.HashSet;
import java.util.List;
import java.util.Objects;
//import java.util.Set;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.UniqueElements;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer courseId;
	@NotNull(message = "null value not be accepted")
	private String courseName;
	@NotNull
	private Double fee;
	@NotNull(message = "null value not be accepted")
	private Date expiry;
	@NotNull(message = "null value not be accepted")
	private String duration;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "courseSet",cascade = CascadeType.ALL)
	private Set<PaidedStudent> studentset=new HashSet<>();

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		return Objects.equals(courseId, other.courseId) && Objects.equals(courseName, other.courseName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(courseId, courseName);
	}


	
	
	
}
