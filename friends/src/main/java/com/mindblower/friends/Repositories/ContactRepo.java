package com.mindblower.friends.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindblower.friends.entities.Contact;

public interface ContactRepo extends JpaRepository<Contact, Integer>{

}
