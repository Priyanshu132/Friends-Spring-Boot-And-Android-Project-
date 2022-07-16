package com.mindblower.friends.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(nullable = false,length = 30)
	private String first_name;
	
	private String middle_name;
	
	private String last_name;
	
	@Column(nullable = false)
	private String gender;
	
	@Column(name = "date_of_birth",nullable = false)
	private Date dob;
	
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_language",
    joinColumns = {@JoinColumn(name ="user",referencedColumnName = "id")},
    inverseJoinColumns = {@JoinColumn(name ="language",referencedColumnName = "id")})
	private List<Language> language_known;
	
	@Column(nullable = false,length = 100)
	private String email;
	
	@Column(nullable = false,length = 100)
	private String password;
	
	@Column(length = 300)
	private String about;
	
	private String profile_image;
	private String cover_image;
	
	@ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinTable(name = "user_address",
    joinColumns = {@JoinColumn(name ="user",referencedColumnName = "id")},
    inverseJoinColumns = {@JoinColumn(name ="address",referencedColumnName = "id")})
	private List<Address> address;
	
	@OneToMany(fetch = FetchType.LAZY)
	private List<Contact> contact;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_work_place",
    joinColumns = {@JoinColumn(name ="user",referencedColumnName = "id")},
    inverseJoinColumns = {@JoinColumn(name ="work_place",referencedColumnName = "id")})
	private List<Work> work_place;
	
	@OneToMany(fetch = FetchType.LAZY)
	private List<Education> education;
	
	private String religion;
	
	private String interested;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "user")
	private List<Post> post;
	
	private boolean isOnline;
	
	@Column(name = "No_Of_Followers")
	private int noOfFollewers;
	
	@Column(name = "No_Of_Friends")
	private int noOfFriends;
	
	@Column(name = "No_Of_Following")
	private int noOfFollowing;
	
	@Column(name = "No_Of_Get_Request")
	private int noOfGetRequest;
	
	@Column(name = "No_Of_Send_Request")
	private int noOfSendRequest;

	public User() {
		super();
	}
	

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

	public List<Language> getLanguage_known() {
		return language_known;
	}

	public void setLanguage_known(List<Language> language_known) {
		this.language_known = language_known;
	}

	public List<Post> getPost() {
		return post;
	}

	public void setPost(List<Post> post) {
		this.post = post;
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
	

	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}

	public List<Work> getWork_place() {
		return work_place;
	}

	public void setWork_place(List<Work> work_place) {
		this.work_place = work_place;
	}

	public List<Contact> getContact() {
		return contact;
	}

	public void setContact(List<Contact> contact) {
		this.contact = contact;
	}

	
	
	public List<Education> getEducation() {
		return education;
	}

	public void setEducation(List<Education> education) {
		this.education = education;
	}

	public String getReligion() {
		return religion;
	}
	public void setReligion(String religion) {
		this.religion = religion;
	}
	public String getInterested() {
		return interested;
	}
	public void setInterested(String interested) {
		this.interested = interested;
	}


	public boolean isOnline() {
		return isOnline;
	}


	public void setOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}


	public int getNoOfFollewers() {
		return noOfFollewers;
	}


	public void setNoOfFollewers(int noOfFollewers) {
		this.noOfFollewers = noOfFollewers;
	}


	public int getNoOfFriends() {
		return noOfFriends;
	}


	public void setNoOfFriends(int noOfFriends) {
		this.noOfFriends = noOfFriends;
	}


	public int getNoOfFollowing() {
		return noOfFollowing;
	}


	public void setNoOfFollowing(int noOfFollowing) {
		this.noOfFollowing = noOfFollowing;
	}


	public int getNoOfGetRequest() {
		return noOfGetRequest;
	}


	public void setNoOfGetRequest(int noOfGetRequest) {
		this.noOfGetRequest = noOfGetRequest;
	}


	public int getNoOfSendRequest() {
		return noOfSendRequest;
	}


	public void setNoOfSendRequest(int noOfSendRequest) {
		this.noOfSendRequest = noOfSendRequest;
	}


	
	
}
