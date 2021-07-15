package com.eia.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.eia.model.entity.Profile;
import com.eia.model.entity.User;

import net.bytebuddy.implementation.bind.annotation.Default;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer>  {

	
	 public User findByUserName(String userName);
	 
	 public Optional<User> findByPassword(String password);
	 
	 public Optional<User> findByUserNameAndPassword(String userName, String password);
	 
	 @Query("SELECT u.userName FROM User u")//--> JPQL 'Java Persistence Query Language'
	 public List<String> findUserNameOnly(PageRequest page);



}
