package com.mindblower.friends.services;

import java.util.List;

import com.mindblower.friends.entities.User;


public interface UserService {

	User createUser(User user);
	
	User updateUser(User user,Integer userId);
	
	void deleteUser(Integer userId);
	
	User getUserbyId(Integer userId);
	
	List<User> getAllUsers();
	
	void logoutUser(User user);
	
}
