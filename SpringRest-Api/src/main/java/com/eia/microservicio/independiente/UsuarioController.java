package com.eia.microservicio.independiente;

/**
   path param --> @PathVariable / @RequestBody
   user/eia/email/10
   
   query param --> @RequestParam
   user/eia/email?startDate=19-01-2019&endDate=20-01-2019
 
 */

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.eia.service.UserService;

@RestController //Stereotype
@RequestMapping(value = "/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private Usuario usuario;
	
	/**Query Param*/
	
	@GetMapping(value="/queryIni")
	@ResponseBody
	public ResponseEntity<List<Usuario>> usuarioStartWith(@RequestParam(value="startWith",required=false)  String startWith){
		
		List<Usuario> usuarioIniciales = usuarioService.getListaUsuarioWith(startWith);
		
		return new ResponseEntity<List<Usuario>>(usuarioIniciales, HttpStatus.OK);
	}
	
	/**Path param*/
	
	// Handle Method.
	@GetMapping
	public ResponseEntity<List<Usuario>> users(){
		
		return new ResponseEntity<List<Usuario>>(usuarioService.getListaUsuario(), HttpStatus.OK);
	}
	
	@GetMapping(value="/{user}")
	public ResponseEntity<Usuario> usuarioByName(@PathVariable(value = "user")  String  userName){
		return new ResponseEntity<Usuario>(usuarioService.getUserByName(userName), HttpStatus.FOUND);
	}
	
	@PostMapping(value="/{userName}/{nickName}/{password}", produces = MediaType.APPLICATION_JSON_VALUE)
	//@ResponseBody
	public ResponseEntity<Usuario> crearUsuario(@PathVariable(value="userName")  String userName,
											 @PathVariable(value="nickName")  String nickName,
											 @PathVariable(value="password")  String password){
	
		 usuario = new Usuario(userName,nickName,password); 
		
		return new ResponseEntity<Usuario>(usuarioService.createUser(usuario), HttpStatus.CREATED);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Usuario> createUser(@RequestBody Usuario user){
		
		return new ResponseEntity<Usuario>(usuarioService.createUser(user), HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<Usuario> updateUser(@RequestBody Usuario user) {
		
		Usuario actualizarUsuario = usuarioService.updateUser(user, user.getUserName());
		
		return new ResponseEntity<Usuario>(actualizarUsuario, HttpStatus.OK);
	}
	
	@DeleteMapping
	public ResponseEntity<Usuario> deleteUser(@RequestBody Usuario user){
		
		Usuario eliminarUsuario = usuarioService.deleteUser(user, user.getUserName());
		return new ResponseEntity<Usuario>(eliminarUsuario,HttpStatus.NO_CONTENT);
	}
	
}
