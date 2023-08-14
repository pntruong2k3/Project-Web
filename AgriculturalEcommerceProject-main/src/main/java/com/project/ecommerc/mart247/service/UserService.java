package com.project.ecommerc.mart247.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.project.ecommerc.mart247.DTO.UserDTO;
import com.project.ecommerc.mart247.entity.RoleEntity;
import com.project.ecommerc.mart247.entity.UserEntity;


public interface UserService {
	  List<UserEntity> getAllUser();
	
	  void saveUser(UserEntity user);
	
	  void deleteUser(Long id);
	
	  Optional<UserEntity> findUserById(Long id);
	
	  UserDTO findByUserName(String userName);
	  
	  List<RoleEntity> getAllRole();
	  
	  Page<UserEntity> getAllUsers(Pageable pageable);
	
	  Page<UserDTO> getEntitiesByName(int page, int pageSize, String sortField, String sortDirection, String name);
		
	  Page<UserDTO> getEntities(int page, int pageSize, String sortField, String sortDirection);
	  
	  int findUsersByRole();
}
