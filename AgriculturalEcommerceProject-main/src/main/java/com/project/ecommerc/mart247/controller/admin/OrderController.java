package com.project.ecommerc.mart247.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.ecommerc.mart247.DTO.OrderDTO;
import com.project.ecommerc.mart247.service.serviceImpl.OrderServiceImp;

@Controller
@RequestMapping("/admin")
public class OrderController {
	
	@Autowired
	private OrderServiceImp orderServiceImp;
	
	
	@RequestMapping(value = "/order")
	public String home(Model model, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int pageSize, @RequestParam(defaultValue = "id") String sortField,
			@RequestParam(defaultValue = "asc") String sortDirection, @RequestParam(required = false) String name) {
		Page<OrderDTO> orderPage;
		if (name != null && !name.isEmpty()) {
			orderPage = orderServiceImp.getAllOrderByUserName(page, pageSize, sortField, sortDirection, name);
		} else {
			orderPage = orderServiceImp.getAllOrder(page, pageSize, sortField, sortDirection);
		}
		model.addAttribute("orderPage", orderPage);
		model.addAttribute("sortField", sortField);
		model.addAttribute("name", name);
		model.addAttribute("sortDirection", sortDirection);
		return "admin/manageOrder";
	}
	
	@RequestMapping(value = "/order/changeStatus/{id}", method = RequestMethod.POST)
	@ResponseBody
	public String changeStatus(@PathVariable("id") long id) {
	    // Xử lý logic để thay đổi trạng thái và trả về chuỗi String
	    String newStatus = orderServiceImp.changeStatus(id);
	    return newStatus;
	}

}
