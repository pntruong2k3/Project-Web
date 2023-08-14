package com.project.ecommerc.mart247.service.serviceImpl;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.ecommerc.mart247.DTO.CategoryDTO;
import com.project.ecommerc.mart247.entity.CategoryEntity;
import com.project.ecommerc.mart247.repository.CategoryRepository;
import com.project.ecommerc.mart247.service.CategoryService;
import com.project.ecommerc.mart247.util.CommonUtil;

@Service
public class CategoryServiceImp implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public List<CategoryEntity> getAllCategory() {

		return categoryRepository.findAll();
	}
	
	@Override
	public void saveCategory(CategoryDTO categoryDTO, MultipartFile file) {
		String base64Image = CommonUtil.ConvertImg(file);
		CategoryEntity category = new CategoryEntity(categoryDTO);
		category.setImg(base64Image);
		categoryRepository.save(category);
	}
	
	@Override
	public void editCategory(CategoryDTO categoryDTO, MultipartFile file) {
		Optional<CategoryEntity> categoryOptional = categoryRepository.findById(categoryDTO.getId());
		CategoryEntity category = categoryOptional.get();
		if(!file.isEmpty()) {
			String base64Image = CommonUtil.ConvertImg(file);
			category.setImg(base64Image);
		}else {
			category.setImg(categoryDTO.getImg());
		}
	
		category.setName(categoryDTO.getName());
		
		categoryRepository.save(category);
	};

	@Override
	public void deleteCategory(Long id) {
		categoryRepository.deleteCategoryById(id);

	}

	@Override
	public Optional<CategoryEntity> findCategoryById(Long id) {

		return categoryRepository.findById(id);
	}

	@Override
	public Optional<CategoryEntity> findCategorybyName(String name) {

		return Optional.of(categoryRepository.findByName(name));
	}

	@Override
	public List<CategoryEntity> getCategory() {

		return categoryRepository.getCategory();
	}
	
	@Override
	public List<CategoryEntity> getCategorybyId(){
		return categoryRepository.getCatebyId();
	}


	public Page<CategoryDTO> getEntitiesByName(int page, int pageSize, String sortField, String sortDirection,
			String name) {
		Page<CategoryDTO> dtoPage = null;

		if (!sortField.equals("productCount")) {
			Sort sort = Sort.by(sortField);
			sort = sortDirection.equals("desc") ? sort.descending() : sort.ascending();
			Pageable pageable = PageRequest.of(page, pageSize, sort);
			Page<CategoryEntity> categoryEntityPage = categoryRepository.findByNameContainingIgnoreCaseAndDeletedAtIsNull(name, pageable);
			dtoPage = convertToPageCateDTO(categoryEntityPage);
		} else {
			Pageable pageable = PageRequest.of(page, pageSize);
			Page<CategoryEntity> categoryEntityPage = categoryRepository.findByNameContainingIgnoreCaseAndDeletedAtIsNull(name, pageable);
			Page<CategoryDTO> categoryDTOS = convertToPageCateDTO(categoryEntityPage);
			dtoPage = new PageImpl<CategoryDTO>(categoryDTOS.stream()
					.sorted(sortDirection.equals("desc")
							? Comparator.comparingInt(CategoryDTO::getProductCount).reversed()
							: Comparator.comparingInt(CategoryDTO::getProductCount))
					.collect(Collectors.toList()), categoryDTOS.getPageable(), categoryDTOS.getTotalElements());
		}
		return dtoPage;
//		return new PageImpl<>(categoryDTOList, pageable, categoryEntityPage.getTotalElements());;
	}

	public Page<CategoryDTO> getEntities(int page, int pageSize, String sortField, String sortDirection) {
		Page<CategoryDTO> dtoPage = null;

		if (!sortField.equals("productCount")) {
			Sort sort = Sort.by(sortField);
			sort = sortDirection.equals("desc") ? sort.descending() : sort.ascending();
			Pageable pageable = PageRequest.of(page, pageSize, sort);
			Page<CategoryEntity> categoryEntityPage = categoryRepository.findByDeletedAtIsNull(pageable);
			dtoPage = convertToPageCateDTO(categoryEntityPage);
		} else {
			Pageable pageable = PageRequest.of(page, pageSize);
			///

			List<CategoryEntity> categoryEntities = categoryRepository.findByDeletedAtIsNull();
			int start = (int) pageable.getOffset();
			int end = Math.min((start + pageable.getPageSize()), categoryEntities.size());
			List<CategoryDTO> categoryDTOS1 = convertToListCateDTO(categoryEntities);
			List<CategoryDTO> categoryDTOList = categoryDTOS1.stream()
					.sorted(sortDirection.equals("desc")
							? Comparator.comparingInt(CategoryDTO::getProductCount).reversed()
							: Comparator.comparingInt(CategoryDTO::getProductCount))
					.collect(Collectors.toList()).subList(start, end);
			;

			dtoPage = new PageImpl<>(categoryDTOList, pageable, categoryEntities.size());
		}
		return dtoPage;
		
	}

	private Page<CategoryDTO> convertToPageCateDTO(Page<CategoryEntity> categoryEntities) {
		Page<CategoryDTO> categoryDTOS = categoryEntities.map(entity -> {
			CategoryDTO dto = new CategoryDTO(entity);
			BeanUtils.copyProperties(dto, entity);
			dto.setProductCount(entity.getProducts().size());
			return dto;
		});
		return categoryDTOS;
	}

	private List<CategoryDTO> convertToListCateDTO(List<CategoryEntity> categoryEntities) {
		List<CategoryDTO> categoryDTOS = categoryEntities.stream().map(entity -> {
			CategoryDTO dto = new CategoryDTO(entity);
			BeanUtils.copyProperties(dto, entity);
			dto.setProductCount(entity.getProducts().size());
			return dto;
		}).collect(Collectors.toList());
		return categoryDTOS;
	}

	public List<CategoryEntity> getParenCate(){
		return categoryRepository.findByParentId(0L);
	}

	public List<CategoryEntity> getChildByParent(Long parentId){
		return categoryRepository.findByParentId(parentId);
	}

	public List<CategoryDTO> getCateByParenAndChild(){
		List<CategoryDTO> result = convertToListCateDTO(getParenCate());
		try {
			result.forEach(i -> i.setChildCate(convertToListCateDTO(getChildByParent(i.getId()))));
		}catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
}
