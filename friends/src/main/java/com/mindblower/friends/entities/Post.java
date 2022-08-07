package com.mindblower.friends.entities;

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
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

@Entity
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer postId;
	
	@Column(name = "about",length = 100)
	private String PostAbout;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name = "created_date",nullable = false)
	private Date postDate;
	
	@Column(nullable = false)
	private String visibility;
	
	@Column(name = "likes")
	private Integer postLikesInteger;
	
	@Column(name = "User",nullable = false)
	private Integer userId;
	
	@Column(name = "User_Name",nullable = false)
	private String userName;
	
	@OneToMany(fetch = FetchType.EAGER)
	private List<Comment> comment;

	@Column(name = "Post_Type")
	private int postType;	
	
	@OneToMany(mappedBy = "post",fetch = FetchType.LAZY)
	private List<PostLikes> postLikes;
	
	@Transient
	private boolean isLiked;
	
	@Column(name = "IMAGE_NAME")
	private String image;

	public Integer getPostId() {
		return postId;
	}

	public void setPostId(Integer postId) {
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

	

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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

	public int getPostType() {
		return postType;
	}

	public void setPostType(int postType) {
		this.postType = postType;
	}

	public List<PostLikes> getPostLikes() {
		return postLikes;
	}

	public void setPostLikes(List<PostLikes> postLikes) {
		this.postLikes = postLikes;
	}

	public boolean isLiked() {
		return isLiked;
	}

	public void setLiked(boolean isLiked) {
		this.isLiked = isLiked;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}



	
	
}
