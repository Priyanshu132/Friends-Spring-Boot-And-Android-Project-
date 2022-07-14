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
import com.mindblower.friends.Dto.ContactDto;
import com.mindblower.friends.entities.Contact;
import com.mindblower.friends.exception.ResourceNotFoundException;
import com.mindblower.friends.reponse.Response;
import com.mindblower.friends.services.ContactService;

@RestController
@RequestMapping("api/contact/")
public class ContactController {

	@Autowired
	private ContactService contactService;
	
	@Autowired
	private ModelMapper mapper;
	
	@PutMapping("update")
	public ResponseEntity<Response> updateContact(@Valid @RequestBody ContactDto contactDto) {
		
		Contact contact = mapper.map(contactDto, Contact.class);
		Contact updatedContact = contactService.updateContact(contact, contactDto.getId());
		Response response = new Response("Contact Updated Successfully", true,1,updatedContact);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@PostMapping("add")
	public ResponseEntity<Response> addContact(@Valid @RequestBody ContactDto contactDto) {
		
		Contact contact = mapper.map(contactDto, Contact.class);
		Contact updatedContact = contactService.createContact(contact, contactDto.getUserIdInteger());
		Response response = new Response("Contact Added Successfully", true,1,updatedContact);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@DeleteMapping("delete/{Id}")
	public ResponseEntity<Response> deleteContact(@PathVariable Integer Id) {
		
		try {
			contactService.deleteContact(Id);
		} catch (Exception e) {
			throw new ResourceNotFoundException("Contact","Id", Id);
		}
		Response response = new Response("Contact Deleted Successfully", true);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
}
