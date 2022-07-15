package com.mindblower.friends.services;

import com.mindblower.friends.entities.Contact;
import com.mindblower.friends.entities.User;

public interface ContactService {

	Contact createContact(Contact contact,User user);
	
	Contact updateContact(Contact contact,Integer contactIdInteger);
	
	void deleteContact(Integer contactIdInteger);
}
