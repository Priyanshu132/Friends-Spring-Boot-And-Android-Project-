package com.mindblower.friends.Dto;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mindblower.friends.entities.Comment;
import com.mindblower.friends.entities.User;

public class PostDto {

	private Integer postId;
	
	@NotNull(message = "User Can not be Null !!")
	private Integer userId;
	
	private int postType;
	
	@NotBlank(message = "User Name Can not be Blank !!")
	private String userName;
	
	@Size(max = 100,message = "Characters can't be more than 100 !!")
	private String postAbout;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@NotNull(message = "Provide the Date !!")
	private Date postDate;
	
	@NotBlank(message = "Provide that Who can see your Post ?")
	private String visibility;
	
	private Integer postLikesInteger;
	
	private List<Comment> comment;


	public Integer getPostId() {
		return postId;
	}


	public void setPostId(Integer postId) {
		this.postId = postId;
	}

	

	public String getPostAbout() {
		return postAbout;
	}


	public void setPostAbout(String postAbout) {
		this.postAbout = postAbout;
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


	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public int getPostType() {
		return postType;
	}


	public void setPostType(int postType) {
		this.postType = postType;
	}
	
	
}
