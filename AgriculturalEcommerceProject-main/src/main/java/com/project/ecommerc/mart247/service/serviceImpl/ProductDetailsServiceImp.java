package com.project.ecommerc.mart247.service.serviceImpl;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.project.ecommerc.mart247.DTO.ProductDetailsDTO;
import com.project.ecommerc.mart247.entity.ProductDetailsEntity;
import com.project.ecommerc.mart247.entity.ProductEntity;
import com.project.ecommerc.mart247.repository.ProductDetailsRepository;
import com.project.ecommerc.mart247.repository.ProductRepository;
import com.project.ecommerc.mart247.service.ProductDetailsService;

@Service
public class ProductDetailsServiceImp implements ProductDetailsService{

	@Autowired
	private ProductDetailsRepository productDetailsRepository;
	

	@Override
	public Page<ProductDetailsEntity> getAllProductDetails(Pageable pageable) {
		return productDetailsRepository.findAllProductDetails(pageable);
		
		
	}
	
	public void editProductDetails(ProductDetailsDTO productDetails) {
		ProductDetailsEntity entity = new ProductDetailsEntity(productDetails);
		entity.setId(productDetails.getId());
		productDetailsRepository.save(entity);
		
	}

	
	public Page<ProductDetailsDTO> getEntitiesByProductName(int page, int pageSize, String sortField, String sortDirection, String name) {
		Page<ProductDetailsDTO> dtoPage = null;
		Sort sort = Sort.by(sortField);
		sort = sortDirection.equals("desc") ? sort.descending() : sort.ascending();
		Pageable pageable = PageRequest.of(page, pageSize, sort);
		Page<ProductDetailsEntity> productEntityPage = productDetailsRepository.findByProduct_Name(name, pageable);
		dtoPage = convertToPageProdDTO(productEntityPage);

		return dtoPage;

	}
	
	public Page<ProductDetailsDTO> getEntities(int page, int pageSize, String sortField, String sortDirection) {
		Page<ProductDetailsDTO> dtoPage = null;
		Sort sort = Sort.by(sortField);
		sort = sortDirection.equals("desc") ? sort.descending() : sort.ascending();
		Pageable pageable = PageRequest.of(page, pageSize, sort);
		Page<ProductDetailsEntity> productEntityPage = productDetailsRepository.findAllProductDetails(pageable);
		dtoPage = convertToPageProdDTO(productEntityPage);

		return dtoPage;

	}
	
	
	
	

	@Override
	public ProductDetailsDTO getDTOById(long id) {
		Optional<ProductDetailsEntity> entityOptional = productDetailsRepository.findById(id);
		ProductDetailsDTO dto = new ProductDetailsDTO(entityOptional.get());
		
		return dto;
	}

	private Page<ProductDetailsDTO> convertToPageProdDTO(Page<ProductDetailsEntity> productEntities) {
		Page<ProductDetailsDTO> productDTOS = productEntities.map(entity -> {
			ProductDetailsDTO dto = new ProductDetailsDTO(entity);
			BeanUtils.copyProperties(dto, entity);
			
			return dto;
		});
		return productDTOS;
	}
}
