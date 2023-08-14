package com.project.ecommerc.mart247.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.project.ecommerc.mart247.DTO.CartItemDTO;

@Table(name = "cart_item")
@Entity
public class CartItemEntity {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "quantity")
	private int quantity;

	@Column(name = "total_price")
	private double totalPrice;

	@ManyToOne
	@JoinColumn(name = "cart_id")
	private CartEntity cart;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private ProductEntity product;

	public CartItemEntity() {
	}

	public CartItemEntity(long id, int quantity, double totalPrice, CartEntity cart, ProductEntity product) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
		this.cart = cart;
		this.product = product;
	}

	public CartItemEntity(CartItemDTO cartItemDTO) {
		this.id = cartItemDTO.getId();
		this.quantity = cartItemDTO.getQuantity();
		this.totalPrice = cartItemDTO.getTotalPrice();
		this.cart = cartItemDTO.getCart();
		this.product = cartItemDTO.getProduct();
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

	public void setProduct(ProductEntity product) {
		this.product = product;
	}

}
