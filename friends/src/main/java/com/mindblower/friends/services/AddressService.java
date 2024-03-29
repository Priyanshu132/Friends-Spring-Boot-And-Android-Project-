package com.mindblower.friends.services;

import java.util.List;

import com.mindblower.friends.Dto.AddressDto;
import com.mindblower.friends.Dto.UserDto;
import com.mindblower.friends.entities.Address;
import com.mindblower.friends.entities.User;

public interface AddressService {

	
	Address updateAddress(Address address,Integer addressId);
	
	void deleteAddress(Integer addressId);
	
	Address addAddress(Address address,User user);
	
}
