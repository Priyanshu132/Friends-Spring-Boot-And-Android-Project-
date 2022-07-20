package com.mindblower.friends.services.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.mindblower.friends.Repositories.UserRepo;
import com.mindblower.friends.entities.AuthToken;
import com.mindblower.friends.entities.User;
import com.mindblower.friends.exception.ResourceNotFoundException;
import com.mindblower.friends.exception.UserTokenNotFoundException;
import com.mindblower.friends.reponse.Response;
import com.mindblower.friends.security.Auth;
import com.mindblower.friends.services.AuthTokenService;
import com.mindblower.friends.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private AuthTokenService authTokenService;
	
	@Override
	public User createUser(User user) {
		
		User savedUser = userRepo.save(user);
		
		return savedUser;
	}

	@Override
	public User updateUser(User user,Integer userId) {

		User savedUser = userRepo.save(user);
		
		
		return savedUser;
		
	}

	@Override
	public void deleteUser(Integer userId) {
		
		try {
			userRepo.deleteById(userId);
		} catch (Exception e) {
			System.out.println(e.getMessage() +" " +e.getLocalizedMessage());
			throw new ResourceNotFoundException("User", " id ", userId);
		}
		
		
	}

	@Override
	public User getUserbyId(Integer userId) {
		
		User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User"," id ",userId));

		return user;
	}

	@Override
	public List<User> getAllUsers() {
		
		List<User> users  = userRepo.findAll();
	
		return users;
	}

	@Override
	public void logoutUser(User user) {
		
		authTokenService.deleteUserAuthRow(user);
		
	}

	@Override
	public Response login(String username, String password) {
		
		Response response = new Response();
		User user = userRepo.findByEmail(username);
		if(Objects.isNull(user)) {
			throw new UserTokenNotFoundException("User", " Email ", username);
		}
		String hashedPasString = Auth.generatePassword(password);
		
		if(Auth.checkpassword(password, hashedPasString)) {
			
			user.setOnline(true);
			String authTokenString = Auth.getAuthenticationToken(user.getFirst_name());
			AuthToken authToken =  new AuthToken();
			authToken.setAuthToken(authTokenString);
			authToken.setUser(user);
			
			authTokenService.saveAuthToken(authToken);
			response.setAuthorization(authTokenString);
			response.setObject(user);
			response.setCount(1);
			response.setMessage("User Loged In Successfully");
			response.setSuccess(true);
		}
		else {
			response.setMessage("Incorrect Password");
			response.setSuccess(false);
		}
		
		return response;
	}

}
