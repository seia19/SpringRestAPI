package com.eia.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.eia.model.entity.Address;
import com.eia.model.entity.Profile;
import com.eia.repository.IAddresRepository;
import com.eia.repository.IProfileRepository;

@Service
public class AddressService {

	@Autowired
	private IAddresRepository addressRepository;
	
	@Autowired
	private IProfileRepository profileRepository;
	
	public List<Address> findAddressByProfileIdAndUserId(Integer userId, Integer profileId) {

		return addressRepository.findByProfileId(userId, profileId);
	}

	
	public Address createAddres(Address address, Integer userId, Integer profileId) {

		Optional<Profile> resultadoBusquedaProfile = profileRepository.findByUserIdAndProfileId(userId, profileId);
		
			if (resultadoBusquedaProfile.isPresent()==true) {
				
				address.setProfile(resultadoBusquedaProfile.get());
				
				return addressRepository.save(address);
			
			}else {
				
				String msjNotProfileFound = String.format("el usuario con id %d & profile con el id %d no fue encontrado", userId, profileId);
				
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, msjNotProfileFound);
			}
				
		
	}

}
