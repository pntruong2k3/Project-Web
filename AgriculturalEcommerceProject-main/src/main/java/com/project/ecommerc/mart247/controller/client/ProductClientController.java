package com.project.ecommerc.mart247.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.ecommerc.mart247.DTO.CategoryDTO;
import com.project.ecommerc.mart247.DTO.ProductDTO;
import com.project.ecommerc.mart247.service.CategoryService;
import com.project.ecommerc.mart247.service.ProductService;

@Controller
public class ProductClientController {
	
	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(value = "/danh-muc/{id}")
	public String home(Model model,@PathVariable("id") long id, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "8") int pageSize, @RequestParam(defaultValue = "id") String sortField,
			@RequestParam(defaultValue = "asc") String sortDirection) {
		
		Page<ProductDTO> productPage = productService.getProductByCategory(id, page, pageSize, sortField, sortDirection);
		List<CategoryDTO> categories = categoryService.getCateByParenAndChild();

		model.addAttribute("categories", categories);
		model.addAttribute("productPage", productPage);
		model.addAttribute("id", id);
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDirection", sortDirection);
		return "client/danhMuc";
	}
	
	@RequestMapping(value = "/product")
	public String home(Model model, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "8") int pageSize, @RequestParam(defaultValue = "id") String sortField,
			@RequestParam(defaultValue = "asc") String sortDirection, @RequestParam(required = false) String name) {
		

	
		Page<ProductDTO> productPage;
		if (name != null && !name.isEmpty()) {
			productPage = productService.getEntitiesByName(page, pageSize, sortField, sortDirection, name);
		} else {
			productPage = productService.getEntities(page, pageSize, sortField, sortDirection);
		}
		List<CategoryDTO> categories = categoryService.getCateByParenAndChild();

		model.addAttribute("categories", categories);
		model.addAttribute("sortField", sortField);
		model.addAttribute("name", name);
		model.addAttribute("sortDirection", sortDirection);
		model.addAttribute("productPage", productPage);
		
		
		return "client/allProduct";
	}
	
	@RequestMapping(value = "/product/{id}")
	public String productDetails(Model model,@PathVariable("id") long id) {
		
		ProductDTO productDTO = productService.findProductDTOById(id);
		List<CategoryDTO> categories = categoryService.getCateByParenAndChild();

		model.addAttribute("categories", categories);
		model.addAttribute("id", id);
		model.addAttribute("productDTO", productDTO);
		
		return "client/productDetails";
	}
}
