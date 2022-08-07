package com.mindblower.friends.services;

import java.util.List;

import com.mindblower.friends.entities.Comment;
import com.mindblower.friends.entities.User;

public interface CommentService {

	Comment createComment(Comment comment,Integer postIdInteger);
	
	void deleteComment(Integer commentId);
	
	Comment updateComment(Comment comment,Integer commentId);
	
	int increaseCommentLikes(Integer commentId,User user);
	
	int decreaseCommentLikes(Integer commentId,User user);
	
	List<Integer> getCommentLikes(Integer commentId);
}
