package com.project.ecommerc.mart247.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.project.ecommerc.mart247.DTO.ProductDTO;
import com.project.ecommerc.mart247.entity.ProductEntity;

public interface ProductService {

	Page<ProductEntity> getAllProduct(Pageable pageable);

	void deleteProduct(Long id);

	Optional<ProductEntity> findProductById(Long id);
	ProductDTO findProductDTOById(Long id);
	void editProduct(ProductDTO productDTIO, MultipartFile file);

	List<ProductEntity> getProduct();

	Optional<ProductEntity> findProductByName(String name);

	void saveProduct(ProductDTO productDTO, MultipartFile file);

	int getTotalProduct();

	Page<ProductDTO> getProductByCategory(long id, int page, int pageSize, String sortField, String sortDirection);

	Page<ProductDTO> getEntitiesByName(int page, int pageSize, String sortField, String sortDirection, String name);

	Page<ProductDTO> getEntities(int page, int pageSize, String sortField, String sortDirection);

	/*
	 * @Override public List<ProductEntity> getCategoryId0() {
	 * 
	 * return ProductRepository.getCatebyId(); }
	 */
}
