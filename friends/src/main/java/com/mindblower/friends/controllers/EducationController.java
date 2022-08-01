package com.mindblower.friends.controllers;

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
import org.springframework.web.bind.annotation.RestController;
import com.mindblower.friends.Dto.EducationDto;
import com.mindblower.friends.entities.Education;
import com.mindblower.friends.entities.User;
import com.mindblower.friends.exception.ResourceNotFoundException;
import com.mindblower.friends.reponse.Response;
import com.mindblower.friends.security.Auth;
import com.mindblower.friends.services.AuthTokenService;
import com.mindblower.friends.services.EducationService;

@RestController
@RequestMapping("api/education/")
public class EducationController {

	@Autowired
	private EducationService educationService;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private AuthTokenService authTokenService;
	
	@PutMapping("update/{id}")
	public ResponseEntity<Response> updateEducation(@RequestHeader(required = true) String Authorization,
													@Valid @RequestBody EducationDto educationDto,
													@PathVariable(required = true) Integer id) {
		
		User user = authTokenService.getCustomerFromToken(Authorization);
		Education education = mapper.map(educationDto, Education.class);
		education.setId(id);
		Education updatedEducation = educationService.updateEducation(education,id);
		Response response = new Response("Education Updated Successfully", true,1,updatedEducation);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@PostMapping("add")
	public ResponseEntity<Response> addEducation(@RequestHeader(required = true) String Authorization,
												@Valid @RequestBody EducationDto educationDto) {
		
		User user = authTokenService.getCustomerFromToken(Authorization);
		Education education = mapper.map(educationDto, Education.class);
		Education updatedEducation = educationService.createEducation(education, user);
		Response response = new Response("Education Added Successfully", true,1,updatedEducation);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@DeleteMapping("delete/{Id}")
	public ResponseEntity<Response> deleteEducation(@RequestHeader(required = true) String Authorization,
													@PathVariable Integer Id) {
		
		User user = authTokenService.getCustomerFromToken(Authorization);
		try {
			educationService.deleteEducation(Id);
		} catch (Exception e) {
			throw new ResourceNotFoundException("Education","Id", Id);
		}
		Response response = new Response("Education Deleted Successfully", true);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<Response> getAllEducation(@RequestHeader(required = true) String Authorization){
		
		User user = authTokenService.getCustomerFromToken(Authorization);
		
		Response response = new Response("Education Fetched successfully",true,user.getEducation().size(),user.getEducation());
		return new ResponseEntity<Response>(response, HttpStatus.OK);

		
	}
}
