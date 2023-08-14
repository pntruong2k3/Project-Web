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

import com.project.ecommerc.mart247.DTO.OrderDetailsDTO;

@Entity
@Table(name = "orderDetails")
public class OrderDetailsEntity {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private ProductEntity product;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "order_id")
	private OrderEntity order;

	@Column(name = "price")
	private double price;


	@Column(name = "quantity")
	private int quantity;
	
	@Column(name = "total_price")
	private double totalPrice;

	public OrderDetailsEntity() {}

	public OrderDetailsEntity(long id, ProductEntity product, OrderEntity order, double price, int quantity,
			double totalPrice) {
		super();
		this.id = id;
		this.product = product;
		this.order = order;
		this.price = price;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
	}
	
	public OrderDetailsEntity(OrderDetailsDTO dto) {
		this.id = dto.getId();
		this.product = dto.getProduct();
		this.order = dto.getOrder();
		this.price = dto.getPrice();
		this.quantity = dto.getQuantity();
		this.totalPrice = dto.getTotalPrice();
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
