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

import com.mindblower.friends.Dto.EducationDto;
import com.mindblower.friends.Dto.LanguageDto;
import com.mindblower.friends.entities.Education;
import com.mindblower.friends.entities.Language;
import com.mindblower.friends.entities.User;
import com.mindblower.friends.exception.ResourceNotFoundException;
import com.mindblower.friends.reponse.Response;
import com.mindblower.friends.security.Auth;
import com.mindblower.friends.services.AuthTokenService;
import com.mindblower.friends.services.LanguageService;

@RestController
@RequestMapping("api/language/")
public class LanguageController {

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private LanguageService languageService;
	
	@Autowired
	private AuthTokenService authTokenService;
	
	@PutMapping("update/{id}")
	public ResponseEntity<Response> updateLanguage(@RequestHeader(required = true) String Authorization,
													@Valid @RequestBody LanguageDto languageDto,
													@PathVariable(required = true) Integer id) {
		
		User user = authTokenService.getCustomerFromToken(Authorization);
		Language language = mapper.map(languageDto, Language.class);
		language.setId(id);
		Language updatedLanguage = languageService.updateLanguage(language, id);
		Response response = new Response("Language Updated Successfully", true,1,updatedLanguage);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@PostMapping("add")
	public ResponseEntity<Response> addLanguage(@RequestHeader(required = true) String Authorization,
												@Valid @RequestBody LanguageDto languageDto) {
		
		User user = authTokenService.getCustomerFromToken(Authorization);
		Language language = mapper.map(languageDto, Language.class);
		Language updatedLanguage = languageService.createLanguage(language,user);
		Response response = new Response("Language Added Successfully", true,1,updatedLanguage);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@DeleteMapping("delete/{Id}")
	public ResponseEntity<Response> deleteLanguage(@RequestHeader(required = true) String Authorization,
													@PathVariable Integer Id) {
		
		User user = authTokenService.getCustomerFromToken(Authorization);
		try {
			languageService.deleteLanguage(Id);
		} catch (Exception e) {
			throw new ResourceNotFoundException("Language","Id", Id);
		}
		Response response = new Response("Language Deleted Successfully", true);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
}
