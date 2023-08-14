package com.project.ecommerc.mart247.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.ecommerc.mart247.entity.ProductDetailsEntity;

@Repository
public interface ProductDetailsRepository extends JpaRepository<ProductDetailsEntity, Long> {
	
	@Query("SELECT pdt FROM ProductDetailsEntity pdt JOIN pdt.product pd WHERE pd.deletedAt IS NULL")
	Page<ProductDetailsEntity> findAllProductDetails(Pageable pageable);
	
	@Query("SELECT pd FROM ProductDetailsEntity pd JOIN pd.product p WHERE p.deletedAt IS NULL AND p.name LIKE %:name%")
	Page<ProductDetailsEntity> findByProduct_Name(@Param("name") String name, Pageable pageable);

}