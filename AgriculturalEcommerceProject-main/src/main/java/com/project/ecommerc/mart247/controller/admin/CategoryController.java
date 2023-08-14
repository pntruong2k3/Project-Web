package com.project.ecommerc.mart247.controller.admin;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.project.ecommerc.mart247.DTO.CategoryDTO;
import com.project.ecommerc.mart247.entity.CategoryEntity;
import com.project.ecommerc.mart247.repository.CategoryRepository;
import com.project.ecommerc.mart247.service.serviceImpl.CategoryServiceImp;

@Controller
@RequestMapping("/admin")
public class CategoryController {
	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private CategoryServiceImp categoryService;

	@RequestMapping(value = "/category")
	public String home(Model model, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int pageSize, @RequestParam(defaultValue = "id") String sortField,
			@RequestParam(defaultValue = "asc") String sortDirection, @RequestParam(required = false) String name) {

		Page<CategoryDTO> categoryPage;
		if (name != null && !name.isEmpty()) {
			categoryPage = categoryService.getEntitiesByName(page, pageSize, sortField, sortDirection, name);
		} else {
			categoryPage = categoryService.getEntities(page, pageSize, sortField, sortDirection);
		}

		CategoryDTO category = new CategoryDTO();
		List<CategoryEntity> listCateById = categoryService.getCategorybyId();

		model.addAttribute("sortField", sortField);
		model.addAttribute("name", name);
		model.addAttribute("sortDirection", sortDirection);
		model.addAttribute("listCateById", listCateById);
		model.addAttribute("categoryDTO", category);
		model.addAttribute("categoryPage", categoryPage);
		return "admin/manageCategory";
	}

	@RequestMapping(value = "/category/add")
	public String addCategory(Model model) {

		CategoryDTO category = new CategoryDTO();
		List<CategoryEntity> listCateById = categoryService.getCategorybyId();

		model.addAttribute("listCateById", listCateById);
		model.addAttribute("categoryDTO", category);
		return "addCategory";
	}

	@RequestMapping(value = "/category/add", method = RequestMethod.POST)
	public String save(Model model, @ModelAttribute CategoryDTO categoryDTO,
			@RequestParam("imageFile") MultipartFile file) {

		categoryService.saveCategory(categoryDTO, file);

		return "redirect:/admin/category";

	}

	@RequestMapping(value = "/category/edit/{id}")
	public String editCategory(Model model, @PathVariable("id") long id) {

		List<CategoryEntity> listCateById = categoryService.getCategorybyId();
		Optional<CategoryEntity> cateById = categoryService.findCategoryById(id);
		model.addAttribute("listCateById", listCateById);
		model.addAttribute("cateById", cateById);
		return "admin/editCategory";
	}

	@RequestMapping(value = "/category/edit", method = RequestMethod.POST)
	public String saveEditCategory(Model model, @ModelAttribute CategoryDTO categoryDTO,
			@RequestParam("imageFile") MultipartFile file) {		
		categoryService.editCategory(categoryDTO, file);
		return "redirect:/admin/category";
	}

	@RequestMapping(value = "/category/delete/{id}")
	@Transactional
	public String deleteCategory(Model model, @PathVariable("id") long id) {

		categoryService.deleteCategory(id);

		return "redirect:/admin/category";
	}

}
