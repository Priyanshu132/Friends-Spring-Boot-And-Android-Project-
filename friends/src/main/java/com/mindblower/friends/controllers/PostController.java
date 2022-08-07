package com.mindblower.friends.controllers;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.yaml.snakeyaml.Yaml;

import com.mindblower.friends.reponse.Response;
import com.mindblower.friends.security.Auth;
import com.mindblower.friends.services.AuthTokenService;
import com.mindblower.friends.services.PostService;
import com.mindblower.friends.services.StorageStrategy;
import com.mindblower.friends.services.UserService;
import com.mindblower.friends.Dto.PostDto;
import com.mindblower.friends.entities.Post;
import com.mindblower.friends.entities.User;

@RestController
@RequestMapping("/api/post/")
public class PostController {
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	StorageStrategy storageStrategy;
	
	@Autowired
	private AuthTokenService authTokenService;
	
	@GetMapping("/")
	public ResponseEntity<Response> getAllPost(@RequestHeader(required = true) String Authorization){
		
		User user = authTokenService.getCustomerFromToken(Authorization);
		user.setOnline(true);
		userService.updateUser(user);
		List<Post> posts = postService.getAllPost();
		posts.stream().forEach(post->{
			
			post.getPostLikes().stream().forEach(postlike-> {
				
				if(postlike.getUser() == user.getId()) {
					post.setLiked(true);
				}
			});
			
			post.getComment().stream().forEach(comment->{
				
				comment.getCommentLikes1().stream().forEach(commentlike->{
					
					if(commentlike.getUser() == user.getId())
						comment.setLiked(true);
				});
			});
		});
		Response response = new Response("All Post Fetched Successfully", true,posts.size(),posts);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}

	@PostMapping("save")
	public ResponseEntity<Response> createPost(@RequestHeader(required = true) String Authorization,
												@Valid @RequestBody PostDto postDto,
												@RequestParam("file") MultipartFile file) throws IOException{
		
		User user = authTokenService.getCustomerFromToken(Authorization);
		Post post = mapper.map(postDto, Post.class);
		String fileNameString = storageStrategy.uploadFile(file);
		post.setImage(fileNameString);
		Post updatedPost = postService.createPost(post,user);
		Response response = new Response("Post Created Successfully", true,1,updatedPost);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@PutMapping("update/{id}")
	public ResponseEntity<Response> updatePost(@RequestHeader(required = true) String Authorization,
												@Valid @RequestBody PostDto postDto,
												@PathVariable(required = true) Integer id){
		
		User user = authTokenService.getCustomerFromToken(Authorization);
		Post post = mapper.map(postDto, Post.class);
		post.setPostId(id);
		Post updatedPost = postService.updatePost(post,id);
		Response response = new Response("Post Updated Successfully", true,1,updatedPost);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@DeleteMapping("delete/{Id}")
	public ResponseEntity<Response> deletePost(@RequestHeader(required = true) String Authorization,
												@PathVariable Integer Id){
	
		User user = authTokenService.getCustomerFromToken(Authorization);
		postService.deletePost(Id);
		
		Response response = new Response("Post Delete Successfully", true);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@PostMapping("likes/increase/{postId}")
	public ResponseEntity<Response> increasePostLikes(@RequestHeader(required = true) String Authorization,
															@PathVariable Integer postId) {
		
		User user = authTokenService.getCustomerFromToken(Authorization);
		Integer likesInteger = postService.increasePostLikes(postId,user);
		Response response = new Response("Post Added Successfully", true,1,likesInteger);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@PostMapping("likes/decrease/{postId}")
	public ResponseEntity<Response> decreasePostLikes(@RequestHeader(required = true) String Authorization,
															@PathVariable Integer postId) {

		User user = authTokenService.getCustomerFromToken(Authorization);
		Integer likesInteger = postService.decreasePostLikes(postId,user);
		Response response = new Response("Post Added Successfully", true,1,likesInteger);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@PostMapping("likes/{postId}")
	public ResponseEntity<Response> getPostLikes(@RequestHeader(required = true) String Authorization,
													@PathVariable Integer postId) {
	
		User user = authTokenService.getCustomerFromToken(Authorization);
		List<Integer> users = postService.getPostLikes(postId,user);
		Response response = new Response("Post Added Successfully", true,users.size(),users);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
}
