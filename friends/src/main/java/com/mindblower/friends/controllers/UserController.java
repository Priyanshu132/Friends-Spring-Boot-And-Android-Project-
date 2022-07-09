package com.mindblower.friends.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mindblower.friends.exception.ResourceNotFoundException;
import com.mindblower.friends.payloads.UserDto;
import com.mindblower.friends.reponse.Response;
import com.mindblower.friends.services.UserService;

@Controller
@RequestMapping("/api/users")
public class UserController {

	@Autowired(required=true)
	private UserService userService;
	
	
	@PostMapping("/saveUser")
	public ResponseEntity<Response> createUser(@RequestBody UserDto userDto){
		
		UserDto creUserDto = userService.createUser(userDto);
		
		Response response = new Response("User created Successfully", true,1,creUserDto);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@PutMapping("/updateUser")
	public ResponseEntity<Response> updateUser(@RequestBody UserDto userDto){
		
		Response response;
		try {
			Integer userId = userDto.getId();
			UserDto updatedUser = userService.updateUser(userDto,userId);
			 response = new Response("User Updated Successfully", true,1,updatedUser);
		}
		catch (ResourceNotFoundException e) {
			 response = new Response(e.getMessage(), false);
			
		}
		
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<Response> getUserById(@PathVariable Integer userId){
		
		UserDto updatedUser = userService.getUserbyId(userId);
		Response response = new Response("User Fetched Successfully", true,1,updatedUser);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<Response> getAllUsers(){
		
		List<UserDto> users = userService.getAllUsers();
		Response response = new Response("User Fetched Successfully", true,users.size(),users);
		
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}

	@DeleteMapping("/deleteUser/{userId}")
	public ResponseEntity<Response> deleteUser(@PathVariable Integer userId){
		
		userService.deleteUser(userId);
		
		return new ResponseEntity<Response>(new Response("User deleted successfully", true), HttpStatus.OK);
	}
}
