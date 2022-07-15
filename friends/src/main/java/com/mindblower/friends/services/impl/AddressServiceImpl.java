package com.mindblower.friends.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mindblower.friends.Repositories.AddressRepo;
import com.mindblower.friends.entities.Address;
import com.mindblower.friends.entities.User;
import com.mindblower.friends.exception.ResourceNotFoundException;
import com.mindblower.friends.services.AddressService;
import com.mindblower.friends.services.UserService;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepo addressRepo;
	
	@Autowired
	private UserService userService;

	
	@Override
	public Address updateAddress(Address address, Integer addressId) {
		
		addressRepo.findById(addressId).orElseThrow(()-> new ResourceNotFoundException("Address","id",addressId));
		
		Address updatedAddress =  addressRepo.save(address);
		
		return updatedAddress;
	}

	@Override
	public void deleteAddress(Integer addressId) {
		
		try {
			addressRepo.deleteById(addressId);
		} catch (Exception e) {
			throw new ResourceNotFoundException("Address","id",addressId);
		}

	}

	@Override
	public Address addAddress(Address address,User user) {
		
		Address updatedAddress =  addressRepo.save(address);
		user.getAddress().add(updatedAddress);
		userService.updateUser(user, user.getId());
		
		return updatedAddress;
	}

}
