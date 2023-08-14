package com.project.ecommerc.mart247.controller.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.ecommerc.mart247.entity.CartItemEntity;
import com.project.ecommerc.mart247.entity.ProductEntity;
import com.project.ecommerc.mart247.service.serviceImpl.CartItemServiceImp;
import com.project.ecommerc.mart247.service.serviceImpl.ProductServiceImp;
import com.project.ecommerc.mart247.service.serviceImpl.UserDetailsServiceImp;
import com.project.ecommerc.mart247.service.serviceImpl.UserServiceImp;

@Controller
public class CartItemController {
	@Autowired
	private UserDetailsServiceImp userDetailsServiceImp;
	@Autowired
	private ProductServiceImp productServiceImp;
	private UserServiceImp userServiceimp;
	@Autowired
	private CartItemServiceImp cartItemServiceImp;
	@PostMapping("/addProductToCart")
    @ResponseBody
    public ResponseEntity<String> addProductToCart(@RequestParam("productId") long productId, @RequestParam(value="quantity", defaultValue = "1") int quantity) {
		

        cartItemServiceImp.addCartItem(productId, quantity);
        return ResponseEntity.ok("CartItem saved successfully");
	}
	
	@PostMapping("/saveCartItem")
    @ResponseBody
    public ResponseEntity<String> saveCartItem(@RequestParam("cartItemId") Long cartItemId, @RequestParam("quantity") int quantity) {
        // Lấy thông tin CartItem từ cơ sở dữ liệu
        CartItemEntity cartItem = cartItemServiceImp.findCartItemById(cartItemId).orElseThrow();
        if (cartItem == null) {
            // Xử lý CartItem không tồn tại
            return ResponseEntity.badRequest().body("CartItem not found");
        }
        
        double totalMoney = quantity * cartItem.getProduct().getPrice();
        
        // Cập nhật số lượng CartItem
        cartItem.setQuantity(quantity);
        cartItem.setTotalPrice(totalMoney);
        cartItemServiceImp.saveCartItem(cartItem);

        // Trả về kết quả thành công
        return ResponseEntity.ok("CartItem saved successfully");
    }
	
	@DeleteMapping("/removeCartItem/{cartItemId}")
	public ResponseEntity<String> removeCartItem(@PathVariable long cartItemId) {
	    cartItemServiceImp.deleteCartItem(cartItemId);
	    return ResponseEntity.ok("CartItem removed successfully");
	}
	
	@PostMapping("/removeAllCartItems")
	public String removeAllCartItems() {
		cartItemServiceImp.removeAllCartItems();
	    return "redirect:/cart"; 
	}
	
	

}
