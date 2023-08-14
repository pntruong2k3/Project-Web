package com.project.ecommerc.mart247.service.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ecommerc.mart247.DTO.CartDTO;
import com.project.ecommerc.mart247.DTO.MyUserDTO;
import com.project.ecommerc.mart247.entity.CartEntity;
import com.project.ecommerc.mart247.entity.CartItemEntity;
import com.project.ecommerc.mart247.repository.CartRepository;
import com.project.ecommerc.mart247.service.CartService;
import com.project.ecommerc.mart247.util.CommonUtil;

@Service
public class CartServiceImp implements CartService {

	@Autowired
	private CartRepository cartRepository;

	@Override
	public void saveCart(CartEntity cart) {
		cartRepository.save(cart);

	}

	@Override
	public void saveCart(CartDTO dto) {
		CartEntity entity = cartRepository.findById(dto.getId()).orElse(null);
		if (entity != null) {
			entity.setTotalPrice(dto.getTotalPrice());
			cartRepository.save(entity);
		}
	}

	@Override
	public void deleteCart(Long id) {
		cartRepository.deleteById(id);
		;

	}

	@Override
	public Optional<CartEntity> findCartById(Long id) {
		return cartRepository.findById(id);
	}

	public double getTotalPriceDTO(CartDTO dto) {
		double totalPrice = dto.getCartItem().stream()
				.mapToDouble(cartItem -> cartItem.getProduct().getPrice() * cartItem.getQuantity()).sum();
		return totalPrice;
	}

	public CartDTO getCartDTOById(long id) {
		CartEntity entity = cartRepository.findById(id).orElseThrow();

		return new CartDTO(entity);
	}

//	private List<CartDTO> convertToListCartDTO(List<CartEntity> cartEntities) {
//		List<CartDTO> cartDTO = cartEntities.stream().map(entity -> {
//			CartDTO dto = new CartDTO(entity);
//			BeanUtils.copyProperties(dto, entity);
//
//			return dto;
//		}).collect(Collectors.toList());
//		return cartDTO;
//	}

	public double getTotalPriceOnCart() {
		double totalPrice = 0.0;
		MyUserDTO myUser = CommonUtil.getMyUser();
		if(myUser==null) {
			return 0;
		}
		CartDTO cartDTO = getCartDTOById(myUser.getCartId());		
		for (CartItemEntity cartItem : cartDTO.getCartItem()) {
			totalPrice += cartItem.getQuantity() * cartItem.getProduct().getPrice();
		}
		cartDTO.setTotalPrice(totalPrice);
		saveCart(cartDTO);
		return totalPrice;
	}

	@Override
	public int getTotalProductOncart() {
		MyUserDTO myUser = CommonUtil.getMyUser();
		if(myUser==null) {
			return 0;
		}
		CartDTO cartDTO = getCartDTOById(myUser.getCartId());		
		
		return cartDTO.getCartItem().size();
	}

}