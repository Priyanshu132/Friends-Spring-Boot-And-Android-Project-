package com.mindblower.friends.controllers;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
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

import com.mindblower.friends.Dto.UserDto;
import com.mindblower.friends.entities.User;
import com.mindblower.friends.exception.ResourceNotFoundException;
import com.mindblower.friends.reponse.Response;
import com.mindblower.friends.services.UserService;

@Controller
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper mapper;
	
	@PostMapping("/saveUser")
	public ResponseEntity<Response> createUser(@Valid @RequestBody UserDto userDto){
		
		User user = mapper.map(userDto, User.class);
		User updateduser = userService.createUser(user);
		UserDto userDto2 = mapper.map(updateduser, UserDto.class);
		Response response = new Response("User created Successfully", true,1,userDto2);
		
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@PutMapping("/updateUser")
	public ResponseEntity<Response> updateUser(@Valid @RequestBody UserDto userDto){
		
		
		Integer userId = userDto.getId();
		User user = mapper.map(userDto, User.class);
		User updatedUser = userService.updateUser(user,userId);
		UserDto savedUserDto = mapper.map(updatedUser, UserDto.class);
		Response response = new Response("User Updated Successfully", true,1,savedUserDto);
		
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<Response> getUserById(@PathVariable Integer userId){
		
		User updatedUser = userService.getUserbyId(userId);
		Response response = new Response("User Fetched Successfully", true,1,updatedUser);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<Response> getAllUsers(){
		
		List<User> users = userService.getAllUsers();
		List<UserDto> userDtos= users.stream().map(user -> mapper.map(user, UserDto.class)).collect(Collectors.toList());
		Response response = new Response("User Fetched Successfully", true,users.size(),userDtos);
		
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}

	@DeleteMapping("/deleteUser/{userId}")
	public ResponseEntity<Response> deleteUser(@PathVariable Integer userId){
		
		userService.deleteUser(userId);
		
		return new ResponseEntity<Response>(new Response("User deleted successfully", true), HttpStatus.OK);
	}
}
