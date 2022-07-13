package com.mindblower.friends.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindblower.friends.Repositories.PostRepo;
import com.mindblower.friends.Repositories.UserRepo;
import com.mindblower.friends.entities.Post;
import com.mindblower.friends.entities.User;
import com.mindblower.friends.exception.ResourceNotFoundException;
import com.mindblower.friends.services.PostService;
import com.mindblower.friends.services.UserService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	PostRepo postRepo;
	
	@Autowired
	UserService userService;
	
	@Override
	public Post createPost(Post post,Integer userId) {
		
		User user = userService.getUserbyId(userId);
		Post updatedPost = postRepo.save(post);
		user.getPost().add(updatedPost);
		userService.updateUser(user, userId);
		return updatedPost;
	}

	@Override
	public Post updatePost(Post post, Integer postId) {
		
		postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","id",postId));
		
		Post updatedPost = postRepo.save(post);
		return updatedPost;
	}

	@Override
	public List<Post> getAllPost() {
		
		List<Post> posts = postRepo.findAll();
		return posts;
	}

	@Override
	public void deletePost(Integer postId) {
		
		try {
			postRepo.deleteById(postId);
		} catch (Exception e) {
			throw new ResourceNotFoundException("Post", " id ", postId);
		}
	}

	@Override
	public Post getPotsById(Integer postId) {
		
		Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","id",postId));
		return post;
	}

	@Override
	public int increasePostLikes(Integer postId) {
		
		Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","id",postId));
		int likes = post.getPostLikesInteger()+1;
		post.setPostLikesInteger(likes);
		postRepo.save(post);
		return likes;
	}

	@Override
	public int decreasePostLikes(Integer postId) {
		
		Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","id",postId));
		int likes = post.getPostLikesInteger()-1;
		post.setPostLikesInteger(likes);
		postRepo.save(post);
		return likes;
	}

	@Override
	public int getPostLikes(Integer postId) {
		
		Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","id",postId));
		int likes = post.getPostLikesInteger();
		
		return likes;
	}

}
