package com.mindblower.friends.services;

import com.mindblower.friends.entities.Contact;

public interface ContactService {

	Contact createContact(Contact contact,Integer userIdInteger);
	
	Contact updateContact(Contact contact,Integer contactIdInteger);
	
	void deleteContact(Integer contactIdInteger);
}
