package com.mindblower.friends.controllers;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindblower.friends.Dto.AddressDto;

import com.mindblower.friends.entities.Address;

import com.mindblower.friends.reponse.Response;
import com.mindblower.friends.services.AddressService;


@RestController
@RequestMapping("address/")
public class AddressController {
	
	@Autowired
	private AddressService addressService;
	
	
	
	@Autowired
	private ModelMapper mapper;

	@PutMapping("update")
	public ResponseEntity<Response> updateAddress(@Valid @RequestBody AddressDto addressDto) {
		
		Address address = mapper.map(addressDto, Address.class);
		Address updatedAddress = addressService.updateAddress(address, addressDto.getId());
		Response response = new Response("Address Updated Successfully", true,1,updatedAddress);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
		
	}
	
	@PostMapping("add")
	public ResponseEntity<Response> addAddress(@Valid @RequestBody AddressDto addressDto) {
		
		
		Address address = mapper.map(addressDto, Address.class);
		Address updatedAddress = addressService.addAddress(address,addressDto.getUserId());
		
		Response response = new Response("New Address Added Successfully", true,1,updatedAddress);
		return new ResponseEntity<Response>(response,HttpStatus.OK);		
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<Response> deleteAddress(@PathVariable Integer id) throws Exception{
		
		throw new Exception("Under Construction");
//		addressService.deleteAddress(id);
//		return new ResponseEntity<Response>(new Response("Address deleted successfully", true), HttpStatus.OK);

		
	}
}
