package com.eia.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.eia.model.entity.Role;
import com.eia.repository.IRoleRepository;

@Service
public class RoleService {

	@Autowired
	private IRoleRepository roleRepository;
	
	public List<Role> getRoles(){

		return  roleRepository.findAll();
	}
	
	
	public Role getRole(Integer roleId) {
		
	/*	Role tempRole = new Role();
		
		Optional<Role> findById = roleRepository.findById(roleId);
		
		if (findById.isPresent()) {
		
			tempRole = findById.get();
			return tempRole;
		}
		
		else {
			String msjNotFound = String.format("El registro Role con id %d no ha sido encontrado", roleId);
			
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, msjNotFound);
		}*/
	
		//	return roleRepository.findById(roleId).orElseThrow();
		
		return  roleRepository.findById(roleId).get();
		
	}
	
	
	public Role crearRole(Role role) {
		return roleRepository.save(role);
	}
	
	public Role updateRole(Integer roleId, Role role) {
		
		Optional<Role> busquedaIdRol = roleRepository.findById(roleId);
		
		if(busquedaIdRol.isPresent()== true) {
			
			return roleRepository.save(role);
			
		}else {
			
			String msjNotFound = String.format("El Role especificado con id %d no ha sido encontrado", roleId );
		
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, msjNotFound);
		}
	}
	
	public void deleteRole(Integer roleId) {

		Optional<Role> buscarRole = roleRepository.findById(roleId);

		if (buscarRole.isPresent()) {
			roleRepository.deleteById(roleId);
		} else {

			String msjNotFound = String.format("El Role especificado con id %d no ha sido encontrado", roleId);

			throw new ResponseStatusException(HttpStatus.NOT_FOUND, msjNotFound);
		}
	}
}
