package com.mindblower.friends.services;

import com.mindblower.friends.entities.AuthToken;
import com.mindblower.friends.entities.User;

public interface AuthTokenService {

	User getCustomerFromToken(String authString);
	
	void saveAuthToken(AuthToken authToken);
	
	void deleteUserAuthRow(User user);
}
