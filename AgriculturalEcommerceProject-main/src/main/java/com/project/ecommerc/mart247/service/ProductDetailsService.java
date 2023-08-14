package com.project.ecommerc.mart247.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.project.ecommerc.mart247.DTO.ProductDetailsDTO;
import com.project.ecommerc.mart247.entity.ProductDetailsEntity;


public interface ProductDetailsService {
	 Page<ProductDetailsEntity> getAllProductDetails(Pageable pageable);
	 ProductDetailsDTO getDTOById(long id);
}
