package com.eia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eia.model.entity.Role;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Integer> {

}
