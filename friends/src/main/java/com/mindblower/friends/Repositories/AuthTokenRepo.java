package com.mindblower.friends.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindblower.friends.entities.AuthToken;
import com.mindblower.friends.exception.ResourceNotFoundException;

@Repository
public interface AuthTokenRepo extends JpaRepository<AuthToken,Integer> {

	AuthToken findByAuthToken(String authToken) throws ResourceNotFoundException;
}
