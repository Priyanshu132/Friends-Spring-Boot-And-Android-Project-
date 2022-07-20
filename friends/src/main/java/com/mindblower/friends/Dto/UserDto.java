package com.mindblower.friends.Dto;

import java.sql.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


public class UserDto {

	private Integer id;
	
	@NotEmpty
	@Size(max = 30,message = "First Name length should not be more than 30 characters !!")
	private String first_name;
	
	private String middle_name;
	
	private String last_name;
	
	@NotEmpty(message = "Provide the gender")
	private String gender;
	
	@NotNull(message = "Date Of Birth must be provided !!")
	private Date dob;
	
	//private Language language_known;
	
	@Email(message = "Email address is not valid !!")
	private String email;
	
	@NotBlank(message = "It can't be Empty")
	@Size(min = 8,max = 15,message = "Password length should be in between 8-15 characters !!")
	@Pattern(regexp = "^(?=.{10,}$)(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*\\W).*$",
	message = "Password should have at least one Upper Case Letter,one Lower Case Letter, one Digit and one special Character")
	private String password;
	
	@Size(max = 100,message = "You can write only 100 characters only !!")
	private String about;
	
	private String profile_image;
	private String cover_image;
	
	
	
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
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public String getProfile_image() {
		return profile_image;
	}
	public void setProfile_image(String profile_image) {
		this.profile_image = profile_image;
	}
	public String getCover_image() {
		return cover_image;
	}
	public void setCover_image(String cover_image) {
		this.cover_image = cover_image;
	}
	
	
}
