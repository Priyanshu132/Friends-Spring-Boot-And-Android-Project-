package com.mindblower.friends.controllers;

import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mindblower.friends.reponse.Response;
import com.mindblower.friends.services.PostService;
import com.mindblower.friends.Dto.PostDto;
import com.mindblower.friends.entities.Post;

@RestController
@RequestMapping("/api/post/")
public class PostController {
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private PostService postService;

	@PostMapping("save")
	public ResponseEntity<Response> createPost(@Valid @RequestBody PostDto postDto){
		
		Post post = mapper.map(postDto, Post.class);
		Post updatedPost = postService.createPost(post,postDto.getUserId());
		
		Response response = new Response("Post Created Successfully", true,1,updatedPost);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@PutMapping("update")
	public ResponseEntity<Response> updatePost(@Valid @RequestBody PostDto postDto){
		
		Post post = mapper.map(postDto, Post.class);
		Post updatedPost = postService.updatePost(post,postDto.getPostId());
		
		Response response = new Response("Post Updated Successfully", true,1,updatedPost);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@DeleteMapping("delete/{Id}")
	public ResponseEntity<Response> deletePost(@PathVariable Integer Id){
		

		postService.deletePost(Id);
		
		Response response = new Response("Post Delete Successfully", true);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@PostMapping("likes/increase/{postId}")
	public ResponseEntity<Response> increaseCommentLikes(@PathVariable Integer postId) {
		
		Integer likesInteger = postService.increasePostLikes(postId);
		Response response = new Response("Comment Added Successfully", true,1,likesInteger);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@PostMapping("likes/decrease/{commentId}")
	public ResponseEntity<Response> decreaseCommentLikes(@PathVariable Integer postId) {
		
		Integer likesInteger = postService.decreasePostLikes(postId);
		Response response = new Response("Comment Added Successfully", true,1,likesInteger);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@PostMapping("likes/{commentId}")
	public ResponseEntity<Response> getCommentLikes(@PathVariable Integer postId) {
		
		Integer likesInteger = postService.getPostLikes(postId);
		Response response = new Response("Comment Added Successfully", true,1,likesInteger);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
}
