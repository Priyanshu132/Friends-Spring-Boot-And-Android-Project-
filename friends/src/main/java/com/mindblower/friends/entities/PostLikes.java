package com.mindblower.friends.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity(name = "POST_LIKES")
public class PostLikes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "POST_ID",nullable = false)
	private Integer post;
	
	@Column(name = "USER_ID",nullable = false)
	private Integer user;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPost() {
		return post;
	}

	public void setPost(Integer post) {
		this.post = post;
	}

	public Integer getUser() {
		return user;
	}

	public void setUser(Integer user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "PostLikes [id=" + id + ", post=" + post + ", user=" + user + "]";
	}

	
	
}
