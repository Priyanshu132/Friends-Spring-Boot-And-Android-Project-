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

import com.mindblower.friends.Dto.LanguageDto;
import com.mindblower.friends.Dto.WorkDto;
import com.mindblower.friends.entities.Language;
import com.mindblower.friends.entities.User;
import com.mindblower.friends.entities.Work;
import com.mindblower.friends.exception.ResourceNotFoundException;
import com.mindblower.friends.reponse.Response;
import com.mindblower.friends.security.Auth;
import com.mindblower.friends.services.AuthTokenService;
import com.mindblower.friends.services.WorkService;

@RestController
@RequestMapping("api/work/")
public class WorkController {

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private WorkService workService;
	
	@Autowired
	private AuthTokenService authTokenService;
	
	@PutMapping("update/{id}")
	public ResponseEntity<Response> updateWork(@RequestHeader(required = true) String Authorization,
												@Valid @RequestBody WorkDto workDto,
												@PathVariable(required = true) Integer id) {
		
		User user = authTokenService.getCustomerFromToken(Authorization);
		Work work = mapper.map(workDto, Work.class);
		work.setId(id);
		Work updatedWork = workService.updateWork(work, id);
		Response response = new Response("Work Updated Successfully", true,1,updatedWork);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@PostMapping("add")
	public ResponseEntity<Response> addWork(@RequestHeader(required = true) String Authorization,
											@Valid @RequestBody WorkDto workDto) {
		
		User user = authTokenService.getCustomerFromToken(Authorization);
		Work work = mapper.map(workDto, Work.class);
		Work updatedWork = workService.createWork(work,user);
		Response response = new Response("Work Added Successfully", true,1,updatedWork);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@DeleteMapping("delete/{Id}")
	public ResponseEntity<Response> deleteWork(@RequestHeader(required = true) String Authorization,
											@PathVariable Integer Id) {
	
		User user = authTokenService.getCustomerFromToken(Authorization);
		try {
			workService.deleteWork(Id);
		} catch (Exception e) {
			throw new ResourceNotFoundException("Work","Id", Id);
		}
		Response response = new Response("Work Deleted Successfully", true);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<Response> getAllWork(@RequestHeader(required = true) String Authorization){
		
		User user = authTokenService.getCustomerFromToken(Authorization);
		
		Response response = new Response("Work Fetched successfully",true,user.getWork_place().size(),user.getWork_place());
		return new ResponseEntity<Response>(response, HttpStatus.OK);

		
	}
}
