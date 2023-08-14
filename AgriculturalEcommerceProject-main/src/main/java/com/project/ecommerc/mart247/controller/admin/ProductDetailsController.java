package com.project.ecommerc.mart247.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.ecommerc.mart247.DTO.ProductDetailsDTO;
import com.project.ecommerc.mart247.service.serviceImpl.ProductDetailsServiceImp;
import com.project.ecommerc.mart247.service.serviceImpl.ProductServiceImp;

@Controller
@RequestMapping("/admin")
public class ProductDetailsController {

	@Autowired
	private ProductDetailsServiceImp productDetailsServiceImp;

	@RequestMapping(value = "/productDetails")
	public String home(Model model, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int pageSize, @RequestParam(defaultValue = "id") String sortField,
			@RequestParam(defaultValue = "asc") String sortDirection, @RequestParam(required = false) String name) {
		Page<ProductDetailsDTO> productDetailsPage;
		if (name != null && !name.isEmpty()) {
			productDetailsPage = productDetailsServiceImp.getEntitiesByProductName(page, pageSize, sortField, sortDirection, name);
		} else {
			 productDetailsPage = productDetailsServiceImp.getEntities(page, pageSize, sortField,
					sortDirection);
		}
		
		model.addAttribute("productDetailsPage", productDetailsPage);
		model.addAttribute("sortField", sortField);
		model.addAttribute("name", name);
		model.addAttribute("sortDirection", sortDirection);
		return "admin/manageProductDetails";
	}

	@RequestMapping(value = "/productDetails/edit/{id}")
	public String editProductDetails(Model model, @PathVariable("id") long id) {

		ProductDetailsDTO prodId = productDetailsServiceImp.getDTOById(id);

		model.addAttribute("prodId", prodId);

		return "admin/editProductDetails";
	}

	@RequestMapping(value = "/productDetails/edit", method = RequestMethod.POST)
	public String saveEditProductDetails(Model model, @ModelAttribute ProductDetailsDTO productDTO)
			 {
		
				productDetailsServiceImp.editProductDetails(productDTO);

				return "redirect:/admin/productDetails"; // Redirect to a success page

		
	}
}
