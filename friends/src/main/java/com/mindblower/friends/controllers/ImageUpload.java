package com.mindblower.friends.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mindblower.friends.entities.User;
import com.mindblower.friends.reponse.Response;
import com.mindblower.friends.services.AuthTokenService;
import com.mindblower.friends.services.StorageStrategy;
import com.mindblower.friends.services.UserService;


@RestController
@RequestMapping("/upload")
public class ImageUpload {

	@Autowired
	StorageStrategy storageStrategy;
	
	@Autowired
	private AuthTokenService authTokenService;
	
	@Autowired
	UserService userService;
	
	
	@PostMapping("/profile")
	public ResponseEntity<Response> uploadProfileImage(@RequestHeader(required = true) String Authorization,
													@RequestParam("file") MultipartFile file) throws IOException {
		
		User user = authTokenService.getCustomerFromToken(Authorization);
		String fileNameString = storageStrategy.uploadFile(file);
		user.setProfile_image(fileNameString);
		userService.updateUser(user);
		Response response = new Response("Uploaded",true,1,fileNameString);
		return new ResponseEntity<Response>(response,HttpStatus.OK);		
	}
	
	@PostMapping("/cover")
	public ResponseEntity<Response> uploadCoverImage(@RequestHeader(required = true) String Authorization,
													@RequestParam("file") MultipartFile file) throws IOException {
		
		User user = authTokenService.getCustomerFromToken(Authorization);
		String fileNameString = storageStrategy.uploadFile(file);
		user.setCover_image(fileNameString);
		userService.updateUser(user);
		Response response = new Response("Uploaded",true,1,fileNameString);
		return new ResponseEntity<Response>(response,HttpStatus.OK);		
	}
	

	
}
