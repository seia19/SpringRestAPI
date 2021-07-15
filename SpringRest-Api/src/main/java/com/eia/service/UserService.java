package com.eia.service;



import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.server.ResponseStatusException;

import com.eia.model.entity.User;
import com.eia.repository.IUserRepository;



@Service
public class UserService {
	
	
	private static final Logger log = LoggerFactory.getLogger(UserService.class);

	
	@Autowired
	private IUserRepository userRepository;
	
	PageRequest porPaginacion;
	
	/**Query Personalizado @Query & Pageable*/
	
   
    public List<String> getUsersByUserNameOnly(int page, int size){
    	
    	porPaginacion = PageRequest.of(page, size);
    	return userRepository.findUserNameOnly(porPaginacion);   	
    	
    }
	
	
	public Page<User> getUsers(int page, int size){
		
		/**Paginación de registros*/
		                          //int Page, int size
			porPaginacion = PageRequest.of(page, size);
		
		 return userRepository.findAll(porPaginacion);
		
	}
	
	
	public User getUserById(Integer userId) {
		
		String msjNotUserFound = String.format("el usuario con el id %d no fue encontrado", userId);
		
		return userRepository.findById(userId)
							 .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, msjNotUserFound));
	}
	
	
	/**Spring Cache @Cachable - Almacenará una copia de la ejecución de getUerByUserName en caché*/
	
	
	@Cacheable(value = "users")//CacheConfig --> getCacheManager()
	public User getUserByUserName(String userName) {
		
		log.info("get user by username {}" , userName);
		
		User tempUser = userRepository.findByUserName(userName);

		if(userName.equals(tempUser.getUserName())==false) {
			
			String msjNotUserFound = String.format("el usuario con el nombre %s no fue encontrado", userName);

			throw new ResponseStatusException(HttpStatus.NOT_FOUND, msjNotUserFound);
		}
		else {
		return  tempUser;
		}
	}

	public User getUserByPassword(String pass) {
		
		String msjNotUserFound = String.format("el usuario con el password %s no fue encontrado", pass);

		
		return userRepository.findByPassword(pass)
							 .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, msjNotUserFound));
	}
	
	
	public User getUserByUserNameAndPassword(String userName, String pass) {
		
		String msjNotUserFound = String.format("el usuario con el username %s y el password %s no fue encontrado", userName, pass);

		
		return userRepository.findByUserNameAndPassword(userName, pass)
							 .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, msjNotUserFound));
	}
	
	public User createUser(User user) {
		return userRepository.save(user);
	}
	
	/**Spring Cache @CacheEvic - Eliminará la copia de la ejecución de getUerByUserName de la caché*/
	@CacheEvict(value="users") //CacheConfig --> getCacheManager()
	public void deleteUserByUserName(String userName) {
	
		User user = getUserByUserName(userName);
		userRepository.delete(user);
		
	}
	
}
