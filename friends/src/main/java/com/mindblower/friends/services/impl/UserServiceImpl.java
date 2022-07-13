package com.mindblower.friends.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.mindblower.friends.Repositories.UserRepo;
import com.mindblower.friends.entities.User;
import com.mindblower.friends.exception.ResourceNotFoundException;
import com.mindblower.friends.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	
	
	
	@Override
	public User createUser(User user) {
		
		User savedUser = userRepo.save(user);
		
		return savedUser;
	}

	@Override
	public User updateUser(User user,Integer userId) {

		userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
		User savedUser = userRepo.save(user);
		
		return savedUser;
		
	}

	@Override
	public void deleteUser(Integer userId) {
		
		try {
			userRepo.deleteById(userId);
		} catch (Exception e) {
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

}
