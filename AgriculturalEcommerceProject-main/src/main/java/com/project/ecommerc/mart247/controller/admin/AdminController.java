package com.project.ecommerc.mart247.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.ecommerc.mart247.DTO.OrderDTO;
import com.project.ecommerc.mart247.repository.CategoryRepository;
import com.project.ecommerc.mart247.repository.UserRepository;
import com.project.ecommerc.mart247.service.CategoryService;
import com.project.ecommerc.mart247.service.serviceImpl.OrderServiceImp;
import com.project.ecommerc.mart247.service.serviceImpl.ProductServiceImp;
import com.project.ecommerc.mart247.service.serviceImpl.UserServiceImp;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private UserServiceImp userServiceImp;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private HttpServletResponse response;
	@Autowired
	private OrderServiceImp orderServiceImp;
	@Autowired
	private ProductServiceImp productServiceImp;

	@RequestMapping({ "/", "/home" })
	public String adminHome(Model model) {
		int totalOrder = orderServiceImp.getTotalOrder();
		int totalProduct = productServiceImp.getTotalProduct();
		int totalUser = userServiceImp.findUsersByRole();
		List<OrderDTO> orderList = orderServiceImp.find5TopOrder(0, 5);

		model.addAttribute("totalUser", totalUser);
		model.addAttribute("orderList", orderList);
		model.addAttribute("totalProduct", totalProduct);
		model.addAttribute("totalOrder", totalOrder);
		return "admin/adminHome";
	}

}
