package com.mindblower.friends.Dto;

import java.sql.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UpdateUserDto {

private Integer id;
	
	@NotEmpty
	@Size(max = 30,message = "First Name length should not be more than 30 characters !!")
	private String first_name;
	
	private String middle_name;
	
	private String last_name;
	
	@NotNull(message = "Date Of Birth must be provided !!")
	private Date dob;
	
	@Size(max = 100,message = "You can write only 100 characters only !!")
	private String about;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getMiddle_name() {
		return middle_name;
	}

	public void setMiddle_name(String middle_name) {
		this.middle_name = middle_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}
	
	
}
