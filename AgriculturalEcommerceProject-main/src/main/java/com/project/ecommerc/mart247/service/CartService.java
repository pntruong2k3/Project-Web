package com.project.ecommerc.mart247.service;

import java.util.List;
import java.util.Optional;

import com.project.ecommerc.mart247.DTO.CartDTO;
import com.project.ecommerc.mart247.entity.CartEntity;

public interface CartService {

	void saveCart(CartEntity Cart);

	void deleteCart(Long id);

	Optional<CartEntity> findCartById(Long id);

	void saveCart(CartDTO dto);

	CartDTO getCartDTOById(long id);

	double getTotalPriceDTO(CartDTO dto);

	double getTotalPriceOnCart();

	int getTotalProductOncart();
}