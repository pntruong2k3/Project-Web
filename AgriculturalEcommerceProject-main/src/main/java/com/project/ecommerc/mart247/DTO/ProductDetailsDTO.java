package com.project.ecommerc.mart247.DTO;

import com.project.ecommerc.mart247.entity.ProductDetailsEntity;
import com.project.ecommerc.mart247.entity.ProductEntity;

public class ProductDetailsDTO {
	private long id;
	private ProductEntity product;
	private String description;
	private String location;
	
	public ProductDetailsDTO() {};
	
	public ProductDetailsDTO(long id, ProductEntity product, String description, String location) {
		super();
		this.id = id;
		this.product = product;
		this.description = description;
		this.location = location;
	}
	

	public ProductDetailsDTO(ProductDetailsEntity entity) {
		this.id = entity.getId();
		this.product = entity.getProduct();
		this.description = entity.getDescription();
		this.location = entity.getLocation();
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
