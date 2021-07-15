package com.eia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eia.model.entity.Profile;
import com.eia.service.ProfileService;

@RestController
@RequestMapping(value = "users/{userId}/profiles") //todo profile debe tener un user
public class ProfileController {

	@Autowired
	private ProfileService profileService;
	
	@GetMapping(value="/{profileId}")
	public ResponseEntity<Profile> getProfileById(@PathVariable("userId") Integer userId,
												  @PathVariable("profileId") Integer profileId){
		return new ResponseEntity<Profile>(profileService.getByUserIdAndProfileId(userId, profileId), HttpStatus.OK);
	}
	
	
	@PostMapping
	public ResponseEntity<Profile> createProfile(@PathVariable("userId") Integer userId, @RequestBody Profile Profile){
		
		return new ResponseEntity<Profile>(profileService.create(Profile, userId), HttpStatus.CREATED);
	}
	
}
