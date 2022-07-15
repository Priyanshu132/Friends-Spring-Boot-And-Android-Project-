package com.mindblower.friends.services.impl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindblower.friends.Repositories.AuthTokenRepo;
import com.mindblower.friends.entities.AuthToken;
import com.mindblower.friends.entities.User;
import com.mindblower.friends.exception.UserTokenNotFoundException;
import com.mindblower.friends.services.AuthTokenService;

@Service
public class AuthTokenServiceImpl implements AuthTokenService {
	
	@Autowired
	private AuthTokenRepo authTokenRepo;

	@Override
	public User getCustomerFromToken(String authString) {
		
		AuthToken authToken = authTokenRepo.findByAuthToken(authString);
		
		if(Objects.isNull(authToken)) {
			throw new UserTokenNotFoundException("User", "Auth Token ",authString);
		}
		User user = authToken.getUser();
		
		if(Objects.isNull(user)) {
			throw new UserTokenNotFoundException("User", "Auth Token ",authString);
		}
		
		return user;
	}

	@Override
	public void saveAuthToken(AuthToken authToken) {
		
		authTokenRepo.save(authToken);

	}

}
