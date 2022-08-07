package com.mindblower.friends.entities;

import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "comments")
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer commentId;
	
	@Column(name = "discription",length = 100)
	private String CommentDiscription;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name = "created_date",nullable = false)
	private Date commentDate;
	
	@Column(name = "likes")
	private int CommentLikes;
	
	@Column(name = "User",nullable = false)
	private Integer userId;
	
	@Column(name = "User_Name",nullable = false)
	private String userName;

	@OneToMany(mappedBy = "comment",fetch = FetchType.LAZY)
	private List<CommentLikes> commentLikes1;
	
	@Transient
	private boolean isLiked;
	
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

	public boolean isLiked() {
		return isLiked;
	}

	public void setLiked(boolean isLiked) {
		this.isLiked = isLiked;
	}

	public List<CommentLikes> getCommentLikes1() {
		return commentLikes1;
	}

	public void setCommentLikes1(List<CommentLikes> commentLikes1) {
		this.commentLikes1 = commentLikes1;
	}


	
	
}
