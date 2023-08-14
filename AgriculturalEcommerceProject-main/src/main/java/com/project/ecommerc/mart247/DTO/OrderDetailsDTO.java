package com.project.ecommerc.mart247.DTO;

import com.project.ecommerc.mart247.entity.OrderDetailsEntity;
import com.project.ecommerc.mart247.entity.OrderEntity;
import com.project.ecommerc.mart247.entity.ProductEntity;

public class OrderDetailsDTO {

	private long id;
	private ProductEntity product;
	private OrderEntity order;
	private double price;
	private int quantity;
	private double totalPrice;

	public OrderDetailsDTO() {
	}

	public OrderDetailsDTO(long id, ProductEntity product, OrderEntity order, double price, int quantity,
			double totalPrice) {
		super();
		this.id = id;
		this.product = product;
		this.order = order;
		this.price = price;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
	}

	public OrderDetailsDTO(OrderDetailsEntity entity) {
		this.id = entity.getId();
		this.product = entity.getProduct();
		this.order = entity.getOrder();
		this.price = entity.getPrice();
		this.quantity = entity.getQuantity();
		this.totalPrice = entity.getTotalPrice();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public ProductEntity getProduct() {
		return product;
	}

	public void setProduct(ProductEntity product) {
		this.product = product;
	}

	public OrderEntity getOrder() {
		return order;
	}

	public void setOrder(OrderEntity order) {
		this.order = order;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
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
	};

}
