package com.eia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eia.model.entity.Role;
import com.eia.service.RoleService;

@RestController
@RequestMapping("/roles")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	

	@GetMapping
	public ResponseEntity<List<Role>> getRoles(){
		return new ResponseEntity<List<Role>>(roleService.getRoles(),HttpStatus.OK);
	}
	
	@GetMapping(value="/{roleId}")
	public ResponseEntity<Role> getRoleById(@PathVariable(value="roleId") Integer roleId){
		return new ResponseEntity<Role>(roleService.getRole(roleId),HttpStatus.NOT_FOUND);
	}
	
	@PostMapping
	public ResponseEntity<Role> createRole(@RequestBody Role role){
		return new ResponseEntity<Role>(roleService.crearRole(role),HttpStatus.CREATED);
	}
	
	@PutMapping(value="/{roleId}")
	public ResponseEntity<Role> UpdateRole(@PathVariable(value = "roleId") Integer roleId, 
										   @RequestBody Role role){
		
		return new ResponseEntity<Role>(roleService.updateRole(roleId, role),HttpStatus.CREATED);
	}
	
	@DeleteMapping(value="/{roleId}")
	public ResponseEntity<Void> deleteRole(@PathVariable(value="roleId") Integer roleId){
		
		roleService.deleteRole(roleId);
		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
