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
	
	int increasePostLikes(Integer postId);
	
	int decreasePostLikes(Integer postId);
	
	int getPostLikes(Integer postId);
}
