package com.project.ecommerc.mart247.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.project.ecommerc.mart247.DTO.CategoryDTO;
import com.project.ecommerc.mart247.entity.CategoryEntity;

public interface CategoryService {
	List<CategoryEntity> getAllCategory();

	void saveCategory(CategoryDTO categoryDTO, MultipartFile file);
	
	void editCategory(CategoryDTO categoryDTO, MultipartFile file);
	
	void deleteCategory(Long id);

	Optional<CategoryEntity> findCategoryById(Long id);

	Optional<CategoryEntity> findCategorybyName(String name);

	List<CategoryEntity> getCategory();

	Page<CategoryDTO> getEntitiesByName(int page, int pageSize, String sortField, String sortDirection, String name);

	Page<CategoryDTO> getEntities(int page, int pageSize, String sortField, String sortDirection);
//	  Page<CategoryEntity> getCategoriesPage(int page);

	List<CategoryEntity> getCategorybyId();
	List<CategoryDTO> getCateByParenAndChild();

}
