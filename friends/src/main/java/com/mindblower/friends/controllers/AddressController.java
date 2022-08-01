package com.mindblower.friends.controllers;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindblower.friends.Dto.AddressDto;

import com.mindblower.friends.entities.Address;
import com.mindblower.friends.entities.User;
import com.mindblower.friends.reponse.Response;
import com.mindblower.friends.security.Auth;
import com.mindblower.friends.services.AddressService;
import com.mindblower.friends.services.AuthTokenService;


@RestController
@RequestMapping("address/")
public class AddressController {
	
	@Autowired
	private AddressService addressService;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private AuthTokenService authTokenService;

	@PutMapping("update/{id}")
	public ResponseEntity<Response> updateAddress(@RequestHeader(required = true) String Authorization,
													@Valid @RequestBody AddressDto addressDto,
													@PathVariable(required = true) Integer id) {
		
		User user = authTokenService.getCustomerFromToken(Authorization);
		Address address = mapper.map(addressDto, Address.class);
		address.setId(id);
		Address updatedAddress = addressService.updateAddress(address,id);
		Response response = new Response("Address Updated Successfully", true,1,updatedAddress);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
		
	}
	
	@PostMapping("add")
	public ResponseEntity<Response> addAddress(@RequestHeader(required = true) String Authorization,
												@Valid @RequestBody AddressDto addressDto) {
		Auth auth = new Auth();
		User user = authTokenService.getCustomerFromToken(Authorization);
		Address address = mapper.map(addressDto, Address.class);
		Address updatedAddress = addressService.addAddress(address,user);
		
		Response response = new Response("New Address Added Successfully", true,1,updatedAddress);
		return new ResponseEntity<Response>(response,HttpStatus.OK);		
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<Response> deleteAddress(@RequestHeader(required = true) String Authorization,
													@PathVariable Integer id) throws Exception{
		User user = authTokenService.getCustomerFromToken(Authorization);
		
		addressService.deleteAddress(id);
		return new ResponseEntity<Response>(new Response("Address deleted successfully", true), HttpStatus.OK);

		
	}
	
	@GetMapping("/")
	public ResponseEntity<Response> getAllAddress(@RequestHeader(required = true) String Authorization){
		
		User user = authTokenService.getCustomerFromToken(Authorization);
		
		Response response = new Response("Address Fetched successfully",true,user.getAddress().size(),user.getAddress());
		return new ResponseEntity<Response>(response, HttpStatus.OK);

		
	}
	
	
}
