package com.project.ecommerc.mart247.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.ecommerc.mart247.DTO.OrderDetailsDTO;
import com.project.ecommerc.mart247.service.serviceImpl.OrderDetailsServiceImp;

@Controller
@RequestMapping("/admin")
public class OrderDetailsController {
	@Autowired
	private OrderDetailsServiceImp orderDetailsServiceImp;
	
	@RequestMapping(value = "/orderDetails")
	public String home(Model model, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int pageSize, @RequestParam(defaultValue = "id") String sortField,
			@RequestParam(defaultValue = "asc") String sortDirection, @RequestParam(required = false) String name) {
		Page<OrderDetailsDTO> orderDetailsPage;
		if (name != null && !name.isEmpty()) {
			orderDetailsPage = orderDetailsServiceImp.findAllOrderDetailsByUsername(page, pageSize, sortField, sortDirection, name);
		} else {
			orderDetailsPage = orderDetailsServiceImp.getAllOrderDetails(page, pageSize, sortField, sortDirection);
		}
		model.addAttribute("orderDetailsPage", orderDetailsPage);
		model.addAttribute("sortField", sortField);
		model.addAttribute("name", name);
		model.addAttribute("sortDirection", sortDirection);
		return "admin/manageOrderDetails";
	}
}
