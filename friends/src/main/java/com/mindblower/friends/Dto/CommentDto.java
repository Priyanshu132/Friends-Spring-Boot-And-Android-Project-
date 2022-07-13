package com.mindblower.friends.Dto;

import java.util.Date;
import javax.validation.constraints.NotNull;

public class CommentDto {

	private Integer commentId;
	
	@NotNull(message = "Post Id can't be Null !!")
	private Integer postIdInteger;
	
	private String CommentDiscription;
	
	@NotNull(message = "Provide Date and Time of comment !!")
	private Date commentDate;
	
	
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
	
	

}
