package com.mindblower.friends.payloads;

import java.sql.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


public class UserDto {

	private int id;
	
	@NotEmpty
	@Max(value = 30,message = "First Name length should not be more than 30 characters !!")
	private String first_name;
	
	private String middle_name;
	
	private String last_name;
	
	@NotEmpty(message = "Provide the ")
	private String gender;
	
	@NotNull(message = "Date Of Birth must be provided !!")
	private Date dob;
	
	//private Language language_known;
	
	@Email(message = "Email address is not valid !!")
	@Max(value = 100,message = "Email should not contains more than 100 characters !!")
	private String email;
	
	@NotNull
	@Min(value = 8,message = "Password can't be less than 8 characters !!")
	@Max(value = 15,message = "Password can't be more than 15 characters !!")
	@Pattern(regexp = "(?=.*?[A-Z])",message = "Password should have at least one Upper Case Letter")
	@Pattern(regexp = "(?=.*?[a-z])",message = "Password should have at least one Lower Case Letter")
	@Pattern(regexp = "(?=.*?[0-9])",message = "Password should have at least one Digit")
	@Pattern(regexp = "(?=.*?[#?!@$%^&*-])",message = "Password should have at least one Special Character")
	private String password;
	
	@Max(value = 100,message = "You can write only 100 characters only !!")
	private String about;
	
	private String profile_image;
	private String cover_image;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
