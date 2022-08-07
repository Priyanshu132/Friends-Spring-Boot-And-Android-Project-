package com.mindblower.friends.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder.In;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindblower.friends.Repositories.CommentLikesRepo;
import com.mindblower.friends.Repositories.CommentRepo;
import com.mindblower.friends.entities.Comment;
import com.mindblower.friends.entities.CommentLikes;
import com.mindblower.friends.entities.Post;
import com.mindblower.friends.entities.User;
import com.mindblower.friends.exception.ResourceNotFoundException;
import com.mindblower.friends.services.CommentService;
import com.mindblower.friends.services.PostService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	PostService postService;
	
	@Autowired
	CommentLikesRepo commentLikesRepo;
	
	@Autowired
	CommentRepo commentRepo;
	
	@Override
	public Comment createComment(Comment comment, Integer postIdInteger) {
		
		Post post = postService.getPotsById(postIdInteger);
		
		Comment updatedComment = commentRepo.save(comment);
		post.getComment().add(updatedComment);
		postService.updatePost(post, postIdInteger);
		return updatedComment;
	}

	@Override
	public void deleteComment(Integer commentId) {
		
		try {
			commentRepo.deleteById(commentId);
		} catch (Exception e) {
			
			throw new ResourceNotFoundException("Comment", "Id", commentId);
		}
		
	}

	@Override
	public Comment updateComment(Comment comment, Integer commentId) {
		
		commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment", "Id", commentId));
		
		Comment updatedComment = commentRepo.save(comment);
		return updatedComment;
	}

	@Override
	public int increaseCommentLikes(Integer commentId,User user) {
		
		Comment comment = commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment", "Id", commentId));
		
		int likes = comment.getCommentLikes()+1;
		comment.setCommentLikes(likes);
		commentRepo.save(comment);
		
		CommentLikes commentLikes = new CommentLikes();
		commentLikes.setComment(commentId);
		commentLikes.setUser(user.getId());
		commentLikesRepo.save(commentLikes);
		
		return likes;
	}
	
	@Override
	public int decreaseCommentLikes(Integer commentId,User user) {
		
		Comment comment = commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment", "Id", commentId));
		
		int likes = comment.getCommentLikes()-1;
		comment.setCommentLikes(likes);
		commentRepo.save(comment);
		return likes;
	}

	@Override
	public List<Integer> getCommentLikes(Integer commentIds) {
		
		List<CommentLikes> commentLikes = commentLikesRepo.findByComment(commentIds);
		List<Integer> users = commentLikes.stream().map(x->x.getUser()).collect(Collectors.toList());
		
	
		return users;
	}
}
