package com.eia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eia.model.entity.Address;
import com.eia.service.AddressService;

@RestController
@RequestMapping(value="users/{userId}/profiles/{profileId}/addresses")
public class AddressController {

	@Autowired
	private AddressService addressService;
	
	@GetMapping
	public ResponseEntity<List<Address>> findAddressByProfileIdAndUserId(@PathVariable(value="userId") Integer userId, 
																   		 @PathVariable(value="profileId") Integer profileId){
		
		return new ResponseEntity<List<Address>>(addressService.findAddressByProfileIdAndUserId(userId, profileId), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Address> createAddress(@RequestBody Address address, 
												 @PathVariable(value="userId") Integer userId, 
	   		                                     @PathVariable(value="profileId") Integer profileId){
		
		return new ResponseEntity<Address>(addressService.createAddres(address,userId,profileId), HttpStatus.CREATED);
	}
}
