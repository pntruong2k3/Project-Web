package com.project.ecommerc.mart247.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.ecommerc.mart247.entity.CartItemEntity;

@Repository
public interface CartItemRepository  extends JpaRepository<CartItemEntity, Long>{
	
}