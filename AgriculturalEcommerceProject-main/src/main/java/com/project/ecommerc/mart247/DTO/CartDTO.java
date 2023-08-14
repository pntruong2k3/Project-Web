package com.project.ecommerc.mart247.DTO;

import java.util.List;

import com.project.ecommerc.mart247.entity.CartEntity;
import com.project.ecommerc.mart247.entity.CartItemEntity;
import com.project.ecommerc.mart247.entity.UserEntity;

public class CartDTO {
	private long id;
	private List<CartItemEntity> cartItem;
	private UserEntity user;
	private double totalPrice;
	 
	public CartDTO() {};
	
	public CartDTO(long id, List<CartItemEntity> cartItem, UserEntity user, double totalPrice) {
		super();
		this.id = id;
		this.cartItem = cartItem;
		this.user = user;
		this.totalPrice = totalPrice;
	}
	public CartDTO(CartEntity cartEntity) {
		this.id = cartEntity.getId();
		this.totalPrice = cartEntity.getTotalPrice();
		this.user = cartEntity.getUser();
		this.cartItem = cartEntity.getCartItem();
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public List<CartItemEntity> getCartItem() {
		return cartItem;
	}
	public void setCartItem(List<CartItemEntity> cartItem) {
		this.cartItem = cartItem;
	}
	public UserEntity getUser() {
		return user;
	}
	public void setUser(UserEntity user) {
		this.user = user;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	 
	 
}