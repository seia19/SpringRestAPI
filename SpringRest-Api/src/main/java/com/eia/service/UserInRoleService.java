package com.eia.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.eia.model.entity.Role;
import com.eia.model.entity.User;
import com.eia.model.entity.UserInRole;
import com.eia.repository.IRoleRepository;
import com.eia.repository.IUserInRoleRepository;
import com.eia.repository.IUserRepository;

@Service
public class UserInRoleService {


	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	
    @Autowired
    private IUserInRoleRepository userInRoleRepository;
    
    
	@Autowired
	private IUserRepository userRepository;
	
	@Autowired
	private IRoleRepository roleRepository;
    
	
	public UserInRole asignarUserInRole(UserInRole userInRole, Integer roleId, Integer userId) {

		Optional<User> resultadoBusquedaUser = userRepository.findById(userId); 
		
		Optional<Role> resultadoBusquedaRole = roleRepository.findById(roleId);
		
		if(resultadoBusquedaRole.isPresent() && resultadoBusquedaUser.isPresent()) {
			
			UserInRole uir = new UserInRole(userService.getUserById(userId), roleService.getRole(roleId));
		
			return userInRoleRepository.save(uir);
			
			
		}	else {
			
			String msjUserOrRoleNotFound = String.format("El Usuario con id %d o el Role con id %d no ha sido encontrado", userId, roleId);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, msjUserOrRoleNotFound);
		}
		
	}


	public List<User> findUserInRoleByRole(Integer roleId) {
		
		
		return userInRoleRepository.findUserInRoleByRoleIdReturnsUserList(roleId)
								   .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("El Role con id %d no fue Encontrado", roleId)));
	}

}
