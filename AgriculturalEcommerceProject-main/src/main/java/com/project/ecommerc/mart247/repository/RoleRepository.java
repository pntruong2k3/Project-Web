package com.project.ecommerc.mart247.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.ecommerc.mart247.entity.RoleEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long>{
	Optional<RoleEntity> findById(Long id);
}
