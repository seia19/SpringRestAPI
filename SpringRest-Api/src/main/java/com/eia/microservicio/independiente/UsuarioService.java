package com.eia.microservicio.independiente;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.github.javafaker.Faker;

@Service
public class UsuarioService {

	@Autowired
	private Faker faker;
	
	private List<Usuario> listaUsuario = new ArrayList<Usuario>();
	
	@PostConstruct
	public void init() {
		
		for(int i=0; i<100;i++) {
		
		listaUsuario.add(new Usuario(
				 		faker.name().username(),
				 		faker.funnyName().name(),
						faker.dragonBall().character()));
		
		}
	}

	/*QUERY PARAM*/
	
	public List<Usuario> getListaUsuarioWith(String startWith){
		
		if(startWith!=null) {
			
			return	listaUsuario.stream()
								.filter(u->u.getUserName().startsWith(startWith))
								.collect(Collectors.toList());
			
		}else {
			return listaUsuario;
		}
		
	}
	
	/*PATH PARAM*/
	
	public List<Usuario> getListaUsuario() {
		return listaUsuario;
	}


	public Usuario getUserByName(String userName) {
		
	/*	listaUsuario.stream()
					.filter(tempUser -> tempUser.getUserName()
					.equals(userName))
					.collect(Collectors.toList());*/
		
		String usNotFound = String.format( "Required User %s not found!!", userName) ;
		
		return	listaUsuario.stream()
							.filter(u -> u.getUserName().equals(userName))
							.findAny()
							.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, usNotFound));
				 
		
	}
	
	public Usuario createUser(Usuario user) {
		
		if(
			listaUsuario.stream()
						.anyMatch(u -> u.getUserName().equals(user.getUserName()))) {
			
			String respUsuarioRepetido = String.format("El usuario %s ya existe en la lista!!", user.getUserName());
			throw new ResponseStatusException(HttpStatus.CONFLICT, respUsuarioRepetido);
		}
		
		else {
			listaUsuario.add(user);
		}
						
			
		return user;
	}
	
	public Usuario updateUser(Usuario user, String userName) {
		
		Usuario usuarioExistenteToBeUpdated = getUserByName(userName);
		
		usuarioExistenteToBeUpdated.setNickName(user.getNickName());
		usuarioExistenteToBeUpdated.setPassword(user.getPassword());
		
		return usuarioExistenteToBeUpdated;
	}
	
	public Usuario deleteUser(Usuario user, String userName) {
		
		Usuario usuarioExistenteToBeDeleted = getUserByName(userName);
		
		listaUsuario.remove(usuarioExistenteToBeDeleted);
		
		return null;
	}
	
}
