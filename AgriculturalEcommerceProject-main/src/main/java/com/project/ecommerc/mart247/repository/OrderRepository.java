package com.project.ecommerc.mart247.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.ecommerc.mart247.entity.OrderEntity;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long>{
	@Query("SELECT od FROM OrderEntity od JOIN od.user u WHERE u.deletedAt is NULL")
	Page<OrderEntity> findAllOrder(Pageable pageable);
	@Query("SELECT od FROM OrderEntity od JOIN od.user u WHERE u.deletedAt is NULL")
	List<OrderEntity> findAllOrder();
	
	@Query("SELECT od FROM OrderEntity od JOIN od.user u WHERE u.username LIKE %:name% AND u.deletedAt IS NULL")
	Page<OrderEntity> findByOderByUser_Name(@Param("name") String name, Pageable pageable);
	
	
	@Query("SELECT o FROM OrderEntity o ORDER BY o.orderDate DESC")
	List<OrderEntity> find5TopOrder(Pageable pageable);

}
