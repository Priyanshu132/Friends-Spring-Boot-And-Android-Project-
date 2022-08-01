package com.mindblower.friends.services;

import java.util.List;

import com.mindblower.friends.entities.User;
import com.mindblower.friends.reponse.Response;


public interface UserService {

	User createUser(User user);
	
	User updateUser(User user);
	
	void deleteUser(Integer userId);
	
	User getUserbyId(Integer userId);
	
	List<User> getAllUsers();
	
	void logoutUser(User user);
	
	Response login(String username,String password);
	
}
