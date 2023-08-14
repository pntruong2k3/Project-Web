package com.project.ecommerc.mart247.service.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ecommerc.mart247.DTO.MyUserDTO;
import com.project.ecommerc.mart247.entity.CartEntity;
import com.project.ecommerc.mart247.entity.CartItemEntity;
import com.project.ecommerc.mart247.entity.ProductEntity;
import com.project.ecommerc.mart247.repository.CartItemRepository;
import com.project.ecommerc.mart247.repository.CartRepository;
import com.project.ecommerc.mart247.service.CartItemService;
import com.project.ecommerc.mart247.service.ProductService;
import com.project.ecommerc.mart247.util.CommonUtil;

@Service
public class CartItemServiceImp implements CartItemService {
	@Autowired
	private ProductService productServiceImp;
	@Autowired
	private CartItemRepository cartItemRepository;
	@Autowired
	private CartRepository cartRepository;

	@Override
	public List<CartItemEntity> getAllCartItem() {
		return cartItemRepository.findAll();
	}

	@Override
	public void saveCartItem(CartItemEntity cartItem) {
		cartItemRepository.save(cartItem);

	}

	@Override
	public void deleteCartItem(Long id) {
		cartItemRepository.deleteById(id);
	}
	
	@Override
	public void deleteCartItem(CartItemEntity cartItem) {
		cartItemRepository.delete(cartItem);
	}

	@Override
	public Optional<CartItemEntity> findCartItemById(Long id) {
		// TODO Auto-generated method stub
		return cartItemRepository.findById(id);
	}

	public List<CartItemEntity> getCartItemById() {
		// TODO Auto-generated method stub
		return null;
	}

	public CartItemEntity addCartItem(long productId, int quantity) {
		ProductEntity product = productServiceImp.findProductById(productId).orElseThrow();
	    MyUserDTO myUser = CommonUtil.getMyUser();
	    CartEntity cart = cartRepository.findById(myUser.getCartId()).orElseThrow();
	    List<CartItemEntity> cartItemList = cart.getCartItem();
	    
	    Optional<CartItemEntity> existingCartItem = cartItemList.stream()
	        .filter(cartItem -> cartItem.getProduct().getId() == product.getId())
	        .findFirst();
	    
	    CartItemEntity cartItem;
	    if (existingCartItem.isPresent()) {
	        cartItem = existingCartItem.get();
	    } else {
	        cartItem = new CartItemEntity();
	        cartItemList.add(cartItem);
	    }
	    
	    cartItem.setCart(cart);
	    cartItem.setProduct(product);
	    cartItem.setQuantity(quantity);
	    cartItem.setTotalPrice(product.getPrice() * cartItem.getQuantity());
	    
	    return cartItemRepository.save(cartItem);
	}


	public CartItemEntity getCartItemByProductId(long productId, CartEntity cart) {
	    return cart.getCartItem().stream()
	            .filter(cartItem -> cartItem.getProduct().getId() == productId)
	            .findFirst()
	            .orElse(null);
	}
	
	public void removeAllCartItems() {
		MyUserDTO user = CommonUtil.getMyUser();
		CartEntity cart = cartRepository.findById(user.getCartId()).orElseThrow();
		 cartItemRepository.deleteAll(cart.getCartItem());
		
	}

	

}