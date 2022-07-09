package com.mindblower.friends.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(nullable = false,length = 30)
	private String first_name;
	
	private String middle_name;
	
	private String last_name;
	
	@Column(nullable = false)
	private String gender;
	
	@Column(name = "date_of_birth",nullable = false)
	private Date dob;
	
//	@ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "language_known")
//	private Language language_known;
	
	@Column(nullable = false,length = 100)
	private String email;
	
	@Column(nullable = false,length = 100)
	private String password;
	
	@Column(length = 300)
	private String about;
	
	private String profile_image;
	private String cover_image;
//	private Address address;
//	private Contact contact;
//	private Work work_place;
//	private Education education;
//	private String religion;
//	private String interested;
//	private int followedby;
	
	
	
	public User() {
		super();
	}
	
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
//	public Language getLanguage_known() {
//		return language_known;
//	}
//	public void setLanguage_known(Language language_known) {
//		this.language_known = language_known;
//	}
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
//	public Address getAddress() {
//		return address;
//	}
//	public void setAddress(Address address) {
//		this.address = address;
//	}
//	public Contact getContact() {
//		return contact;
//	}
//	public void setContact(Contact contact) {
//		this.contact = contact;
//	}
//	public Work getWork_place() {
//		return work_place;
//	}
//	public void setWork_place(Work work_place) {
//		this.work_place = work_place;
//	}
//	public Education getEducation() {
//		return education;
//	}
//	public void setEducation(Education education) {
//		this.education = education;
//	}
//	public String getReligion() {
//		return religion;
//	}
//	public void setReligion(String religion) {
//		this.religion = religion;
//	}
//	public String getInterested() {
//		return interested;
//	}
//	public void setInterested(String interested) {
//		this.interested = interested;
//	}
//	public int getFollowedby() {
//		return followedby;
//	}
//	public void setFollowedby(int followedby) {
//		this.followedby = followedby;
//	}
	
	
	
}
