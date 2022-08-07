package com.mindblower.friends.services;

import java.util.List;

import com.mindblower.friends.entities.Post;
import com.mindblower.friends.entities.User;

public interface PostService {

	Post createPost(Post post,User user);
	
	Post updatePost(Post post,Integer postId);
	
	List<Post> getAllPost();
	
	void deletePost(Integer postId);
	
	Post getPotsById(Integer postId);
	
	int increasePostLikes(Integer postId,User user);
	
	int decreasePostLikes(Integer postId,User user);
	
	List<Integer> getPostLikes(Integer postId,User user);
}
