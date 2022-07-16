package com.mindblower.friends.controllers;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mindblower.friends.Dto.UserDto;
import com.mindblower.friends.entities.User;
import com.mindblower.friends.reponse.Response;
import com.mindblower.friends.services.AuthTokenService;
import com.mindblower.friends.services.FriendService;
import com.mindblower.friends.services.UserService;

@RestController
@RequestMapping("api")
public class FriendController {

	@Autowired
	private AuthTokenService authTokenService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private FriendService friendService;
	
	@PostMapping("/friend/add")
	public HashMap<String, String> addFriend(@RequestHeader(required = true) String Authorization,
												@RequestParam(required = true) Integer friendId){
		
		User user = authTokenService.getCustomerFromToken(Authorization);
		User friendUser = userService.getUserbyId(friendId);
		HashMap<String, String> response = friendService.addFriend(user, friendUser);
		
		return response;
	}
	
	@PostMapping("/follow")
	public HashMap<String, String> addFollower(@RequestHeader(required = true) String Authorization,
												@RequestParam(required = true) Integer friendId){
		
		User user = authTokenService.getCustomerFromToken(Authorization);
		User friendUser = userService.getUserbyId(friendId);
		HashMap<String, String> response = friendService.addFollower(user, friendUser);
		
		return response;
	}
	
	@PostMapping("/unfriend")
	public HashMap<String, String> UnFriend(@RequestHeader(required = true) String Authorization,
												@RequestParam(required = true) Integer friendId){
		
		User user = authTokenService.getCustomerFromToken(Authorization);
		User friendUser = userService.getUserbyId(friendId);
		HashMap<String, String> response = friendService.UnFriend(user, friendUser);
		
		return response;
	}
	
	@PostMapping("/unfollow")
	public HashMap<String, String> UnFollow(@RequestHeader(required = true) String Authorization,
												@RequestParam(required = true) Integer friendId){
		
		User user = authTokenService.getCustomerFromToken(Authorization);
		User friendUser = userService.getUserbyId(friendId);
		HashMap<String, String> response = friendService.UnFollow(user, friendUser);
		
		return response;
	}
	
	@PostMapping("/friend/request/add")
	public HashMap<String, String> addRequestedFriend(@RequestHeader(required = true) String Authorization,
												@RequestParam(required = true) Integer friendId){
		
		User user = authTokenService.getCustomerFromToken(Authorization);
		User friendUser = userService.getUserbyId(friendId);
		HashMap<String, String> response = friendService.addRequestedFriend(user, friendUser);
		
		return response;
	}
	
	@GetMapping("/friends")
	public ResponseEntity<Response> getAllFriends(@RequestHeader(required = true) String Authorization){
		
		User user = authTokenService.getCustomerFromToken(Authorization);
		List<User> list = friendService.getAllFriend(user);
		
		Response response = new Response("Friends Fetched Successfully", true,list.size(),list);
		
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@GetMapping("/friends/active")
	public ResponseEntity<Response> getAllActiveFriends(@RequestHeader(required = true) String Authorization){
		
		User user = authTokenService.getCustomerFromToken(Authorization);
		List<User> list = friendService.getAllActiveFriends(user);
		
		Response response = new Response("Friends Fetched Successfully", true,list.size(),list);
		
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
}
