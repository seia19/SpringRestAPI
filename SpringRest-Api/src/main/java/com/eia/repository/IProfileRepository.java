package com.eia.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.eia.model.entity.Profile;

@Repository
public interface IProfileRepository extends JpaRepository<Profile, Integer>{
	
											//   id=?1 indica que se trata del primer parámetro & p.id=?2 indica que es el segundo parámetro, ambos dinámicos/variables(?)
	//@Query("SELECT p FROM Profile p WHERE p.user.id=?1 AND p.id=?2") 
      @Query("select p from Profile p where p.user.id=?1 AND p.id=?2")
	Optional <Profile> findByUserIdAndProfileId(Integer userId, Integer profileId);


}
