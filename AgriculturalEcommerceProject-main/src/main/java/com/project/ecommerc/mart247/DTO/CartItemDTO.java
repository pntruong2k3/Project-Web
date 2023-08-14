package com.project.ecommerc.mart247.DTO;

import com.project.ecommerc.mart247.entity.CartEntity;
import com.project.ecommerc.mart247.entity.CartItemEntity;
import com.project.ecommerc.mart247.entity.ProductEntity;

public class CartItemDTO {
	
	private long id;
	private int quantity;
	private double totalPrice;
	private CartEntity cart;
	private ProductEntity product;
	
	public CartItemDTO(CartItemEntity cartItemEntity) {
		this.id = cartItemEntity.getId();
		this.quantity = cartItemEntity.getQuantity();
		this.totalPrice = cartItemEntity.getTotalPrice();
		this.cart = cartItemEntity.getCart();
		this.product = cartItemEntity.getProduct();
	}
	
	public CartItemDTO(long id, int quantity, double totalPrice, CartEntity cart, ProductEntity product) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
		this.cart = cart;
		this.product = product;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public CartEntity getCart() {
		return cart;
	}
	public void setCart(CartEntity cart) {
		this.cart = cart;
	}
	public ProductEntity getProduct() {
		return product;
	}
	public void setProductId(ProductEntity product) {
		this.product = product;
	}
	
	
}