package com.eia.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eia.model.entity.User;
import com.eia.service.UserService;

import io.micrometer.core.annotation.Timed;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController //Stereotype
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	private UserService userService;
	
   
	
    @GetMapping(value="/usersNameOnly")
    @Timed(value = "get.users.inicial.names")//métrica sintaxis: @Timed(value="nombre.de.mi.metodo")
    public ResponseEntity<List<String>> getUsersInicialNames(@RequestParam(value="page", required = false, defaultValue="0") int page,
    														 @RequestParam(value="size", required = false, defaultValue="5") int size){
    	
    	return new ResponseEntity<List<String>>(userService.getUsersByUserNameOnly(page, size), HttpStatus.OK);
    }
	
		
	
	@GetMapping
  //@Timed("nombre.del.metodo")
	@Timed(value = "get.users")//métrica
	public ResponseEntity<Page<User>> getUsers(@RequestParam(value ="page", required = false, defaultValue = "0") int page, //defaultvalue="0"--> Indica que la paginación comienza en la página 0
											   @RequestParam(value ="size", required = false, defaultValue = "10") int size){//Al ser QueryParam los value de @RequestParam no llevan el '/'
		
		return new ResponseEntity<Page<User>>(userService.getUsers(page,size), HttpStatus.OK);
	}
	
	 
	
	@GetMapping(value="/{userId}")
	@ApiOperation(value = "mi Api: Devuelve un user mediante su id n.n " , response = User.class)
	@ApiResponses( value = {
				   			@ApiResponse(code = 200, message = "El usuario fue encontrado con exito ^.^"),
				   			@ApiResponse(code=404, message= "Desafortunadamente el usuario NO fue encontrado 0.0")})
	public ResponseEntity<User> getUserById(@PathVariable(value="userId") Integer userId){
		
		return new ResponseEntity<User>(userService.getUserById(userId), HttpStatus.FOUND);
	}
	

	
	@GetMapping(value="/name/{userName}")
	public ResponseEntity<User> getUserByName(@PathVariable(value="userName") String userName){
		
		return new ResponseEntity<User>(userService.getUserByUserName(userName), HttpStatus.FOUND);
	}
	

	
	@GetMapping(value="/pass/{password}")
	public ResponseEntity<User> getUserByPassword(@PathVariable(value="password") String pass){
		
		return new ResponseEntity<User>(userService.getUserByPassword(pass), HttpStatus.OK);
	}
	
	
	@PostMapping(value="/namepass")
	public ResponseEntity<User> getUserByUserNameAndPassAuthority(@RequestBody User user){
		
		return new ResponseEntity<User>(userService.getUserByUserNameAndPassword(user.getUserName(), user.getPassword()), HttpStatus.ACCEPTED);
	}
	
	
	@PostMapping(value="/createUser")
	public ResponseEntity<User> createUser(@RequestBody User user){
		
		return new ResponseEntity<User>(userService.createUser(user), HttpStatus.CREATED);
	}
	
	@DeleteMapping(value="/name/{userName}")
	public ResponseEntity<Void> deleteUser(@PathVariable(value="userName") String UserName){
		
		userService.deleteUserByUserName(UserName);
		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
