package com.project.ecommerc.mart247.service;

import java.util.List;
import java.util.Optional;

import com.project.ecommerc.mart247.entity.CartEntity;
import com.project.ecommerc.mart247.entity.CartItemEntity;

public interface CartItemService {
	List<CartItemEntity> getAllCartItem();

	void saveCartItem(CartItemEntity CartItem);

	void deleteCartItem(Long id);

	Optional<CartItemEntity> findCartItemById(Long id);

	CartItemEntity addCartItem(long productId, int quantity);

	CartItemEntity getCartItemByProductId(long productId, CartEntity cart);

	void deleteCartItem(CartItemEntity cartItem);
}