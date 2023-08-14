
package com.project.ecommerc.mart247.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.project.ecommerc.mart247.DTO.CartDTO;



@Table(name = "cart")
@Entity
public class CartEntity {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@OneToMany(mappedBy = "cart")
	@Column(name = "cartItem_id")
	private List<CartItemEntity> cartItem;

	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserEntity user;
	
	@Column(name = "total_price")
	private double totalPrice;

	public CartEntity() {
	}

	public CartEntity(long id, UserEntity user, double totalPrice, List<CartItemEntity> cartItem) {
		super();
		this.id = id;
		this.user = user;
		this.totalPrice = totalPrice;
		this.cartItem = cartItem;
	}
	
	public CartEntity(CartDTO cartDTO) {
		this.id = cartDTO.getId();
		this.totalPrice = cartDTO.getTotalPrice();
		this.user = cartDTO.getUser();
		this.cartItem = cartDTO.getCartItem();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public List<CartItemEntity> getCartItem() {
		return cartItem;
	}

	public void setCartItem(List<CartItemEntity> cartItem) {
		this.cartItem = cartItem;
	}

}
