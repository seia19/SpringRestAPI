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

import com.eia.model.entity.User;
import com.eia.model.entity.UserInRole;
import com.eia.service.UserInRoleService;

@RestController
@RequestMapping(value="/roles")
public class UserInRoleController {
	
	
	@Autowired
	private UserInRoleService uirService;
	
	@GetMapping(value = "/{roleId}/users")
	public ResponseEntity<List<User>> findUserInRole(@PathVariable(value="roleId") Integer roleId){
		
		
		return new ResponseEntity<List<User>>(uirService.findUserInRoleByRole(roleId), HttpStatus.OK);
	}

	@PostMapping(value = "/{roleId}/users/{userId}")
	public ResponseEntity<UserInRole> asignarUserInRole(@RequestBody UserInRole userInRole,
													    @PathVariable(value="roleId") Integer roleId,
													    @PathVariable(value="userId") Integer userId ){
		
		return new ResponseEntity<UserInRole>(uirService.asignarUserInRole(userInRole, roleId, userId), HttpStatus.CREATED);
	}
}
