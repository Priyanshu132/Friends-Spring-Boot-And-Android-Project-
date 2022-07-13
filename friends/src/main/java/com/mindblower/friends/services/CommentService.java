package com.mindblower.friends.services;

import com.mindblower.friends.entities.Comment;

public interface CommentService {

	Comment createComment(Comment comment,Integer postIdInteger);
	
	void deleteComment(Integer commentId);
	
	Comment updateComment(Comment comment,Integer commentId);
	
	int increaseCommentLikes(Integer commentId);
	
	int decreaseCommentLikes(Integer commentId);
	
	int getCommentLikes(Integer commentId);
}
