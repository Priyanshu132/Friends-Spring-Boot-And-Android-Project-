package com.mindblower.friends.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import java.util.Date;
import java.util.List;

@Entity
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int postId;
	
	@Column(name = "about",length = 100)
	private String PostAbout;
	
	@Column(name = "created_date",nullable = false)
	private Date postDate;
	
	@Column(nullable = false)
	private String visibility;
	
	@Column(name = "likes")
	private Integer postLikesInteger;
	
	@ManyToOne
	private User user;
	
	@OneToMany(fetch = FetchType.LAZY)
	private List<Comment> comment;

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public String getPostAbout() {
		return PostAbout;
	}

	public void setPostAbout(String postAbout) {
		PostAbout = postAbout;
	}

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Comment> getComment() {
		return comment;
	}

	public void setComment(List<Comment> comment) {
		this.comment = comment;
	}

	public Integer getPostLikesInteger() {
		return postLikesInteger;
	}

	public void setPostLikesInteger(Integer postLikesInteger) {
		this.postLikesInteger = postLikesInteger;
	}
	
	
}
