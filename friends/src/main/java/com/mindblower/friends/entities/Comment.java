package com.mindblower.friends.entities;

import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "comments")
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer commentId;
	
	@Column(name = "discription",length = 100)
	private String CommentDiscription;
	
	@Column(name = "created_date",nullable = false)
	private Date commentDate;
	
	@Column(name = "likes")
	private int CommentLikes;

	
	public Integer getCommentId() {
		return commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
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
