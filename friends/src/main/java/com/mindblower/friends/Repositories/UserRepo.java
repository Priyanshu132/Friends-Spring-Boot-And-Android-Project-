package com.mindblower.friends.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindblower.friends.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {

	
}
