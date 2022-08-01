package com.mindblower.friends.Dto;

import java.util.Date;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CommentDto {

	private Integer commentId;
	
	@NotNull(message = "Post Id can't be Null !!")
	private Integer postIdInteger;
	
	private String CommentDiscription;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@NotNull(message = "Provide Date and Time of comment !!")
	private Date commentDate;
	
	@NotNull(message = "User Can not be Null !!")
	private Integer userId;
	
	@NotBlank(message = "User Name Can not be Blank !!")
	private String userName;
	
	private int CommentLikes;


	public Integer getCommentId() {
		return commentId;
	}


	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}


	public Integer getPostIdInteger() {
		return postIdInteger;
	}


	public void setPostIdInteger(Integer postIdInteger) {
		this.postIdInteger = postIdInteger;
	}


	public String getCommentDiscription() {
		return CommentDiscription;
	}


	public void setCommentDiscription(String commentDiscription) {
		CommentDiscription = commentDiscription;
	}


	public Date getCommentDate() {
		return commentDate;
	}


	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}


	public int getCommentLikes() {
		return CommentLikes;
	}


	public void setCommentLikes(int commentLikes) {
		CommentLikes = commentLikes;
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
	
	

}
