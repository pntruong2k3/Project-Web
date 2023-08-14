package com.project.ecommerc.mart247.controller.client;

import java.util.List;

import com.project.ecommerc.mart247.DTO.CategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.ecommerc.mart247.DTO.ProductDTO;
import com.project.ecommerc.mart247.entity.CategoryEntity;
import com.project.ecommerc.mart247.repository.CategoryRepository;
import com.project.ecommerc.mart247.repository.UserRepository;
import com.project.ecommerc.mart247.service.serviceImpl.CategoryServiceImp;
import com.project.ecommerc.mart247.service.serviceImpl.ProductServiceImp;
import com.project.ecommerc.mart247.service.serviceImpl.UserServiceImp;

@Controller
public class HomeController {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private UserServiceImp userServiceImp;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ProductServiceImp productService;
	
	@Autowired
	private CategoryServiceImp categoryServiceImp;

	@RequestMapping({ "/", "/home" })
	public String home(Model model, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "8") int pageSize, @RequestParam(defaultValue = "id") String sortField,
			@RequestParam(defaultValue = "asc") String sortDirection, @RequestParam(required = false) String name) {

		// Show list category
		List<CategoryDTO> categories = categoryServiceImp.getCateByParenAndChild();

		// show list product

		ProductDTO product = new ProductDTO();

		Page<ProductDTO> productPage;
		if (name != null && !name.isEmpty()) {
			productPage = productService.getEntitiesByName(page, pageSize, sortField, sortDirection, name);
		} else {
			productPage = productService.getEntities(page, pageSize, sortField, sortDirection);
		}

		model.addAttribute("sortField", sortField);
		model.addAttribute("name", name);
		model.addAttribute("sortDirection", sortDirection);
		model.addAttribute("productPage", productPage);

		model.addAttribute("productDTO", product);

		model.addAttribute("categories", categories);
		/// test
		
		return "client/home";	
	}
	
	@RequestMapping(value = "/contact")
	public String home() {

		
		
		return "client/contact";	
	}
	
	

	/*
	 * @ResponseStatus(HttpStatus.BAD_REQUEST)
	 * 
	 * @ExceptionHandler(Exception.class) public Map<String, String>
	 * handleValidationExceptions( MethodArgumentNotValidException ex) { Map<String,
	 * String> errors = new HashMap<>();
	 * ex.getBindingResult().getAllErrors().forEach((error) -> { String fieldName =
	 * ((FieldError) error).getField(); String errorMessage =
	 * error.getDefaultMessage(); errors.put(fieldName, errorMessage); }); return
	 * errors; }
	 */
}
