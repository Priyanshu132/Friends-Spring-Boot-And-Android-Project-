package com.mindblower.friends.controllers;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.aspectj.weaver.tools.Trace;
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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mindblower.friends.Dto.UserDto;
import com.mindblower.friends.Repositories.AuthTokenRepo;
import com.mindblower.friends.entities.AuthToken;
import com.mindblower.friends.entities.User;
import com.mindblower.friends.exception.ResourceNotFoundException;
import com.mindblower.friends.reponse.Response;
import com.mindblower.friends.security.Auth;
import com.mindblower.friends.services.AuthTokenService;
import com.mindblower.friends.services.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private AuthTokenService authTokenService;
	
	@PostMapping("/save")
	public ResponseEntity<Response> createUser(@Valid @RequestBody UserDto userDto){
		
		User user = mapper.map(userDto, User.class);
		
		user.setPassword(Auth.generatePassword(user.getPassword()));
		user.setOnline(true);
		User updateduser = userService.createUser(user);
		
		String authString = Auth.getAuthenticationToken(updateduser.getFirst_name());
		
		AuthToken authToken = new AuthToken();
		authToken.setAuthToken(authString);
		authToken.setUser(updateduser);
		
		authTokenService.saveAuthToken(authToken);
		
		UserDto userDto2 = mapper.map(updateduser, UserDto.class);
		Response response = new Response("User created Successfully", true,1,userDto2);
		response.setAuthorization(authString);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Response> updateUser(@RequestHeader(required = true) String Authorization,
												@Valid @RequestBody UserDto userDto){
		
		User user = authTokenService.getCustomerFromToken(Authorization);
		User userfromDto = mapper.map(userDto, User.class);
		User updatedUser = userService.updateUser(userfromDto,user.getId());
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

	@DeleteMapping("/delete")
	public ResponseEntity<Response> deleteUser(@RequestHeader(required = true) String Authorization){
		
		
		User user = authTokenService.getCustomerFromToken(Authorization);
		userService.deleteUser(user.getId());
		
		return new ResponseEntity<Response>(new Response("User deleted successfully", true), HttpStatus.OK);
	}
	
	@PutMapping("/changePassword")
	public ResponseEntity<Response> changeUserPassword(@RequestHeader(required = true) String Authorization,
														@RequestParam(required = true) String new_password,
												       @RequestParam(required = true) String curr_password){
		
		
		User user = authTokenService.getCustomerFromToken(Authorization);
		Response response = null;
		if(curr_password.equals(new_password)) {
			 response = new Response("New Password can not be same as old Password", false);
		}
		else if(Auth.checkpassword(curr_password,user.getPassword())) {
			user.setPassword(Auth.generatePassword(new_password));
			 response = new Response("Password Updated Successfully", true);
		}
		else {
			 response = new Response("Current Password did not matched !!", false);
		}
		
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@PostMapping("/logout")
	public ResponseEntity<Response> logoutUser(@RequestHeader(required = true) String Authorization){
		
		
		User user = authTokenService.getCustomerFromToken(Authorization);
		user.setOnline(false);
		userService.updateUser(user, user.getId());
		
		userService.logoutUser(user);
		
		return new ResponseEntity<Response>(new Response("User Logout successfully", true), HttpStatus.OK);
	}
}
