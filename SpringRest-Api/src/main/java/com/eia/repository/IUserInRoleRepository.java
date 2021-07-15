package com.eia.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.eia.model.entity.User;
import com.eia.model.entity.UserInRole;

public interface IUserInRoleRepository extends JpaRepository<UserInRole, Integer> {

	
	@Query("SELECT uir.user FROM UserInRole uir WHERE uir.role.id = :roleId")
	Optional<List<User>> findUserInRoleByRoleIdReturnsUserList(Integer roleId);

}
