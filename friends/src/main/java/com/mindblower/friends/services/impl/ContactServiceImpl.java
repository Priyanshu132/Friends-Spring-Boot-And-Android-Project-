package com.mindblower.friends.services.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.mindblower.friends.Repositories.ContactRepo;
import com.mindblower.friends.Repositories.UserRepo;
import com.mindblower.friends.entities.Contact;
import com.mindblower.friends.entities.User;
import com.mindblower.friends.exception.ResourceNotFoundException;
import com.mindblower.friends.services.ContactService;
import com.mindblower.friends.services.UserService;

public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactRepo contactRepo;
	
	@Autowired
	private UserService userService;
	
	@Override
	public Contact createContact(Contact contact, Integer userIdInteger) {
		
		User user = userService.getUserbyId(userIdInteger);
		Contact updatedContact = contactRepo.save(contact);
		
		user.getContact().add(updatedContact);
		userService.updateUser(user, userIdInteger);
		
		return updatedContact;
	}

	@Override
	public Contact updateContact(Contact contact, Integer contactIdInteger) {
		
		contactRepo.findById(contactIdInteger).orElseThrow(()->new ResourceNotFoundException("Contact", "Id", contactIdInteger));
		Contact updatedContact = contactRepo.save(contact);
		return updatedContact;
	}

	@Override
	public void deleteContact(Integer contactIdInteger) {
		
		try {
			contactRepo.deleteById(contactIdInteger);
		} catch (Exception e) {
			
			throw new ResourceNotFoundException("Contact", "Id", contactIdInteger);
		}
		
	}

}
