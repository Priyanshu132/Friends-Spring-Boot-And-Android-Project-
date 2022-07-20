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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindblower.friends.Dto.CommentDto;
import com.mindblower.friends.entities.Comment;
import com.mindblower.friends.entities.User;
import com.mindblower.friends.exception.ResourceNotFoundException;
import com.mindblower.friends.reponse.Response;
import com.mindblower.friends.security.Auth;
import com.mindblower.friends.services.AuthTokenService;
import com.mindblower.friends.services.CommentService;

@RestController
@RequestMapping("api/comment/")
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private AuthTokenService authTokenService;
	
	@PutMapping("update/{id}")
	public ResponseEntity<Response> updateComment(@RequestHeader(required = true) String Authorization,
													@Valid @RequestBody CommentDto commentDto,
													@PathVariable(required = true) Integer id) {
		
		User user = authTokenService.getCustomerFromToken(Authorization);
		Comment comment = mapper.map(commentDto, Comment.class);
		comment.setCommentId(id);
		Comment updatedComment = commentService.updateComment(comment, id);
		Response response = new Response("Commented Successfully", true,1,updatedComment);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@PostMapping("add")
	public ResponseEntity<Response> addComment(@RequestHeader(required = true) String Authorization,
												@Valid @RequestBody CommentDto commentDto) {
		
		User user = authTokenService.getCustomerFromToken(Authorization);
		Comment comment = mapper.map(commentDto, Comment.class);
		Comment updatedComment = commentService.createComment(comment, commentDto.getPostIdInteger());
		Response response = new Response("Comment Added Successfully", true,1,updatedComment);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@DeleteMapping("delete/{Id}")
	public ResponseEntity<Response> deleteComment(@RequestHeader(required = true) String Authorization,
													@PathVariable Integer Id) {
		
		User user = authTokenService.getCustomerFromToken(Authorization);
		try {
			commentService.deleteComment(Id);
		} catch (Exception e) {
			throw new ResourceNotFoundException("Comment","Id", Id);
		}
		Response response = new Response("Comment Deleted Successfully", true);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@PostMapping("likes/increase/{commentId}")
	public ResponseEntity<Response> increaseCommentLikes(@RequestHeader(required = true) String Authorization,
														@PathVariable Integer commentId) {
		
		User user = authTokenService.getCustomerFromToken(Authorization);
		Integer likesInteger = commentService.increaseCommentLikes(commentId);
		Response response = new Response("Comment Added Successfully", true,1,likesInteger);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@PostMapping("likes/decrease/{commentId}")
	public ResponseEntity<Response> decreaseCommentLikes(@RequestHeader(required = true) String Authorization,
															@PathVariable Integer commentId) {
		
		User user = authTokenService.getCustomerFromToken(Authorization);
		Integer likesInteger = commentService.decreaseCommentLikes(commentId);
		Response response = new Response("Comment Added Successfully", true,1,likesInteger);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@PostMapping("likes/{commentId}")
	public ResponseEntity<Response> getCommentLikes(@RequestHeader(required = true) String Authorization,
													@PathVariable Integer commentId) {
		
		User user = authTokenService.getCustomerFromToken(Authorization);
		Integer likesInteger = commentService.getCommentLikes(commentId);
		Response response = new Response("Comment Added Successfully", true,1,likesInteger);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	
}
