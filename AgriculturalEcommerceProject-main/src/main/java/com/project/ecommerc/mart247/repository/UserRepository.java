package com.project.ecommerc.mart247.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.ecommerc.mart247.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
	
	UserEntity findByUsername(String username);

	boolean existsByUsername(String username);
	boolean existsByEmail(String email);
	
	UserEntity findByEmail(String email);
	
	@Query(value = "select * from user where id = :id",   nativeQuery = true)
	List<UserEntity> getUserById(Long id);
	
	@Query(value = "select * from user where deleted_at IS NULL",   nativeQuery = true)
	Page<UserEntity> getAllUsers(Pageable pageable);
	 
	Page<UserEntity> findByNameContainingIgnoreCase(String name,Pageable pageable);
	
	
	@Query(value = "update user set user.deleted_at = now() where user.id = :id", nativeQuery = true)
	@Modifying
	void deleteUser(@Param("id") Long id);
			 
	 @Query("SELECT u FROM UserEntity u WHERE u.role.id =3 ")
	    List<UserEntity> findUsersByRole();
	
}