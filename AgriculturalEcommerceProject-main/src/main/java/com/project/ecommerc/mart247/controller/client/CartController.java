package com.project.ecommerc.mart247.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.ecommerc.mart247.DTO.CartDTO;
import com.project.ecommerc.mart247.DTO.CategoryDTO;
import com.project.ecommerc.mart247.DTO.MyUserDTO;
import com.project.ecommerc.mart247.entity.CartItemEntity;
import com.project.ecommerc.mart247.repository.CartItemRepository;
import com.project.ecommerc.mart247.service.CartItemService;
import com.project.ecommerc.mart247.service.CartService;
import com.project.ecommerc.mart247.service.CategoryService;
import com.project.ecommerc.mart247.service.ProductService;
import com.project.ecommerc.mart247.service.UserService;
import com.project.ecommerc.mart247.util.CommonUtil;

@Controller
public class CartController {
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private ProductService productService;
	@Autowired
	private UserService userService;
	@Autowired
	private CartItemService cartItemService;
	@Autowired
	private CartItemRepository cartItemRepository;
	@Autowired
	private CartService cartService;
	@Autowired
	private CategoryService categoryService;

	@GetMapping("/cart")
	public String showCart(Model model) {
		MyUserDTO myUser = CommonUtil.getMyUser();
		long cartId = myUser.getCartId();
		CartDTO cartDTO = cartService.getCartDTOById(cartId);
		List<CartItemEntity> cartItems = cartDTO.getCartItem();
		int totalProduct = cartService.getTotalProductOncart();
		double totalPrice = cartService.getTotalPriceDTO(cartDTO);
		
		List<CategoryDTO> categories = categoryService.getCateByParenAndChild();
		model.addAttribute("totalProduct", totalProduct);
		model.addAttribute("totalPrice", totalPrice);
		model.addAttribute("categories", categories);
		model.addAttribute("cartDTO", cartDTO);

		for (CartItemEntity cartItemEntity : cartItems) {
			if (cartItemEntity.getQuantity() > cartItemEntity.getProduct().getQuantity()) {
				model.addAttribute("msg", "Số lượng sản phẩm " + cartItemEntity.getProduct().getName() + " không đủ");
				return "client/cart";
			}
		}

		return "client/cart";
	}

	@GetMapping("/calculateTotalPrice")
	@ResponseBody
	public double calculateTotalPrice() throws Exception {

		return cartService.getTotalPriceOnCart();
	}

	@GetMapping("/calculateTotalProduct")
	@ResponseBody
	public double calculateTotalProduct() throws Exception {

		return cartService.getTotalProductOncart();
	}

//	@GetMapping("/calculateTotalPriceCartItem")
//	@ResponseBody
//	public double calculateTotalPriceCartItem(@RequestParam("cartItemId") long cartItemId) throws Exception {
//		CartItemEntity cartItem = cartItemRepository.findById(cartItemId).orElseThrow();
//	    return cartItem.getTotalPrice();
//	}

}
