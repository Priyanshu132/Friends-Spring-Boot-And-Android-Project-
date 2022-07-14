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
import com.mindblower.friends.Dto.LanguageDto;
import com.mindblower.friends.entities.Education;
import com.mindblower.friends.entities.Language;
import com.mindblower.friends.exception.ResourceNotFoundException;
import com.mindblower.friends.reponse.Response;
import com.mindblower.friends.services.LanguageService;

@RestController
@RequestMapping("api/language/")
public class LanguageController {

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private LanguageService languageService;
	
	@PutMapping("update")
	public ResponseEntity<Response> updateLanguage(@Valid @RequestBody LanguageDto languageDto) {
		
		Language language = mapper.map(languageDto, Language.class);
		Language updatedLanguage = languageService.updateLanguage(language, languageDto.getId());
		Response response = new Response("Language Updated Successfully", true,1,updatedLanguage);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@PostMapping("add")
	public ResponseEntity<Response> addLanguage(@Valid @RequestBody LanguageDto languageDto) {
		
		Language language = mapper.map(languageDto, Language.class);
		Language updatedLanguage = languageService.createLanguage(language, languageDto.getUserIdInteger());
		Response response = new Response("Language Added Successfully", true,1,updatedLanguage);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@DeleteMapping("delete/{Id}")
	public ResponseEntity<Response> deleteLanguage(@PathVariable Integer Id) {
		
		try {
			languageService.deleteLanguage(Id);
		} catch (Exception e) {
			throw new ResourceNotFoundException("Language","Id", Id);
		}
		Response response = new Response("Language Deleted Successfully", true);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
}
