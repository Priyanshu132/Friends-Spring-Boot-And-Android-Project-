package com.mindblower.friends.services.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.mindblower.friends.Repositories.UserRepo;
import com.mindblower.friends.entities.User;
import com.mindblower.friends.exception.ResourceNotFoundException;
import com.mindblower.friends.payloads.UserDto;
import com.mindblower.friends.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		
		User user = mapper.map(userDto, User.class);
	
		User savedUser = userRepo.save(user);
		
		UserDto savedUserDto = mapper.map(savedUser, UserDto.class);
		
		return savedUserDto;
	}

	@Override
	public UserDto updateUser(UserDto userDto,Integer userId) {

		User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
		User user1 = mapper.map(userDto, User.class);
		User savedUser = userRepo.save(user1);
		
		UserDto savedUserDto = mapper.map(savedUser, UserDto.class);
		
		return savedUserDto;
		
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
	public UserDto getUserbyId(Integer userId) {
		
		User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User"," id ",userId));
		UserDto userDto = mapper.map(user, UserDto.class);
		return userDto;
	}

	@Override
	public List<UserDto> getAllUsers() {
		
		List<User> users  = userRepo.findAll();
		
		List<UserDto> userDtos= users.stream().map(user -> mapper.map(user, UserDto.class)).collect(Collectors.toList());
	
		return userDtos;
	}

}
