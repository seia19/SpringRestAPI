package com.eia.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.eia.model.entity.Profile;
import com.eia.model.entity.User;
import com.eia.repository.IProfileRepository;
import com.eia.repository.IUserRepository;

@Service
public class ProfileService {

	@Autowired
	private IProfileRepository profileRepository;
	
	@Autowired
	private IUserRepository userRepository;
	
	
	public Profile getByUserIdAndProfileId(Integer userId, Integer profileId) {
		
		return profileRepository.findByUserIdAndProfileId(userId, profileId)
								.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, 
																			   String.format("El Usuario con id %d y su perfil %d no ha sido encontrado", userId, profileId)));
	}
	
	public Profile create(Profile profile, Integer userId) {
		
		Optional<User> resultadoBusquedaUser = userRepository.findById(userId);
		
		if (resultadoBusquedaUser.isPresent()==true) {
			
			profile.setUser(resultadoBusquedaUser.get());
			return profileRepository.save(profile);
		}
		else {
			String msjUserIdNotFound = String.format("El Usuario con id %d no ha sido encontrado", userId);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, msjUserIdNotFound);
		}
		
		
	}


	
	
}

