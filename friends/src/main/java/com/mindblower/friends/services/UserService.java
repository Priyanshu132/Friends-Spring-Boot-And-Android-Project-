package com.mindblower.friends.services;

import java.util.List;

import com.mindblower.friends.payloads.UserDto;


public interface UserService {

	UserDto createUser(UserDto userDto);
	
	UserDto updateUser(UserDto userDto,Integer userId);
	
	void deleteUser(Integer userId);
	
	UserDto getUserbyId(Integer userId);
	
	List<UserDto> getAllUsers();
	
}
