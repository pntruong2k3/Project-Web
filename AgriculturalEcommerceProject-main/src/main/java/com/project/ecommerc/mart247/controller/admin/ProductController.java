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

import com.project.ecommerc.mart247.DTO.ProductDTO;
import com.project.ecommerc.mart247.entity.CategoryEntity;
import com.project.ecommerc.mart247.entity.ProductEntity;
import com.project.ecommerc.mart247.service.serviceImpl.CategoryServiceImp;
import com.project.ecommerc.mart247.service.serviceImpl.ProductServiceImp;


@Controller
@RequestMapping("/admin")
public class ProductController {
	
	@Autowired
	private ProductServiceImp productService;
	@Autowired
	private CategoryServiceImp categoryServiceImp; 
	
	@RequestMapping(value = "/product")
	public String home(Model model, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int pageSize, @RequestParam(defaultValue = "id") String sortField,
			@RequestParam(defaultValue = "asc") String sortDirection, @RequestParam(required = false) String name) {
		

		
//		List<CategoryEntity> listCateById = categoryRepository.getCategory();

//		List<ProductEntity> products = productService.getAllProduct();
		List<CategoryEntity> listCateById = categoryServiceImp.getCategory();

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
		
		model.addAttribute("listCateById", listCateById);
		model.addAttribute("productDTO", product);
		
		return "admin/manageProduct";
	}

	@RequestMapping(value = "/product/add")
	public String addProduct(Model model) {
		List<CategoryEntity> listCateById = categoryServiceImp.getCategorybyId();

		ProductDTO product = new ProductDTO();
		
		model.addAttribute("listCateById", listCateById);
		model.addAttribute("productDTO", product);
		return "addProduct";
	}

	@RequestMapping(value = "/product/add", method = RequestMethod.POST)
	public String save(Model model, @ModelAttribute ProductDTO productDTO,
			@RequestParam("imageFile") MultipartFile file) {

		productService.saveProduct(productDTO, file);
			return "redirect:/admin/product"; // Redirect to an error page if no file is selected

		
	}

	@RequestMapping(value = "/product/edit/{id}")
	public String editProduct(Model model, @PathVariable("id") long id) {
		
		List<CategoryEntity> listCateById = categoryServiceImp.getCategorybyId();
		Optional<ProductEntity> prodId = productService.findProductById(id);
		
		model.addAttribute("prodId", prodId);
		model.addAttribute("listCateById", listCateById);
		return "admin/editProduct";
	}

	@RequestMapping(value = "/product/edit", method = RequestMethod.POST)
	public String saveEditProduct(Model model, @ModelAttribute ProductDTO productDTO,
			@RequestParam("imageFile") MultipartFile file) {
		
				productService.editProduct(productDTO,file);

				return "redirect:/admin/product"; // Redirect to a success page
			
		
		
		
	}

	@RequestMapping(value = "/product/delete/{id}")
	@Transactional
	public String deleteProduct(Model model, @PathVariable("id") long id) {

		productService.deleteProduct(id);
//
//		ProductDTO product = new ProductDTO();
//		Optional<ProductEntity> cateById = productRepository.findById(id);

//		model.addAttribute("cateById", cateById.get());
//		model.addAttribute("productDTO", product);
		return "redirect:/admin/product";
	}
	
}
