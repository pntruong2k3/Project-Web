package com.project.ecommerc.mart247.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.ecommerc.mart247.DTO.OrderDetailsDTO;
import com.project.ecommerc.mart247.entity.OrderDetailsEntity;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetailsEntity, Long>{
	
	@Query("SELECT odd FROM OrderDetailsEntity odd " +
		       "JOIN odd.order od " +
		       "JOIN od.user u " +
		       "WHERE u.deletedAt IS NULL")
	Page<OrderDetailsEntity> findAllOrderDetails(Pageable pageable);
	
	@Query("SELECT odd FROM OrderDetailsEntity odd " +
		       "JOIN odd.order od " +
		       "JOIN od.user u " +
		       "WHERE u.deletedAt IS NULL and u.username LIKE %:name%")
	Page<OrderDetailsEntity> findAllOrderDetailsByUsername(@Param("name")String name, Pageable pageable);
	
	@Query("SELECT od FROM OrderDetailsEntity od WHERE od.order.id = :id")
	List<OrderDetailsEntity> findAllById(@Param("id")long id);
}
