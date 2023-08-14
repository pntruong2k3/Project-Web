package com.project.ecommerc.mart247.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.project.ecommerc.mart247.DTO.ProductDetailsDTO;

@Entity
@Table(name = "product_details")
public class ProductDetailsEntity {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@OneToOne
	@JoinColumn(name="product_id")
	private ProductEntity product;

	@Column(name = "description")
	private String description;
	
	@Column(name = "location")
	private String location;

	
	public ProductDetailsEntity() {}
	
	public ProductDetailsEntity(long id, ProductEntity product, String description, String location) {
		super();
		this.id = id;
		this.product = product;
		this.description = description;
		this.location = location;
	}
	
	
	public ProductDetailsEntity(ProductDetailsDTO productDetailsDTO) {
		this.id = productDetailsDTO.getId();
		this.description = productDetailsDTO.getDescription();
		this.location = productDetailsDTO.getLocation();
		this.product = productDetailsDTO.getProduct();
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	

}
