package com.mindblower.friends.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Friend {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull(message = "User Id can't be Null !!")
	private Integer userId;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "friends")
	private List<User> friendList;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "follower")
	private List<User> followers;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_send_request")
	private List<User> userSendFriendRequest;
	
	@JoinColumn(name = "user_get_request")
	@OneToMany(fetch = FetchType.LAZY)
	private List<User> userGetFriendRequest;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public List<User> getFriendList() {
		return friendList;
	}

	public void setFriendList(List<User> friendList) {
		this.friendList = friendList;
	}

	public List<User> getFollowers() {
		return followers;
	}

	public void setFollowers(List<User> followers) {
		this.followers = followers;
	}

	public List<User> getUserSendFriendRequest() {
		return userSendFriendRequest;
	}

	public void setUserSendFriendRequest(List<User> userSendFriendRequest) {
		this.userSendFriendRequest = userSendFriendRequest;
	}

	public List<User> getUserGetFriendRequest() {
		return userGetFriendRequest;
	}

	public void setUserGetFriendRequest(List<User> userGetFriendRequest) {
		this.userGetFriendRequest = userGetFriendRequest;
	}

	
	
	
	
}
