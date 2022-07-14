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
import com.mindblower.friends.Dto.EducationDto;
import com.mindblower.friends.entities.Education;
import com.mindblower.friends.exception.ResourceNotFoundException;
import com.mindblower.friends.reponse.Response;
import com.mindblower.friends.services.EducationService;

@RestController
@RequestMapping("api/education/")
public class EducationController {

	@Autowired
	private EducationService educationService;
	
	@Autowired
	private ModelMapper mapper;
	
	@PutMapping("update")
	public ResponseEntity<Response> updateEducation(@Valid @RequestBody EducationDto educationDto) {
		
		Education education = mapper.map(educationDto, Education.class);
		Education updatedEducation = educationService.updateEducation(education, educationDto.getId());
		Response response = new Response("Education Updated Successfully", true,1,updatedEducation);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@PostMapping("add")
	public ResponseEntity<Response> addEducation(@Valid @RequestBody EducationDto educationDto) {
		
		Education education = mapper.map(educationDto, Education.class);
		Education updatedEducation = educationService.createEducation(education, educationDto.getUserIdInteger());
		Response response = new Response("Education Added Successfully", true,1,updatedEducation);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@DeleteMapping("delete/{Id}")
	public ResponseEntity<Response> deleteEducation(@PathVariable Integer Id) {
		
		try {
			educationService.deleteEducation(Id);
		} catch (Exception e) {
			throw new ResourceNotFoundException("Education","Id", Id);
		}
		Response response = new Response("Education Deleted Successfully", true);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
}
