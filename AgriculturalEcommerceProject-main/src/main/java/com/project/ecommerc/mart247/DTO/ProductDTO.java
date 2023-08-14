package com.project.ecommerc.mart247.DTO;

import com.project.ecommerc.mart247.entity.CategoryEntity;
import com.project.ecommerc.mart247.entity.ProductDetailsEntity;
import com.project.ecommerc.mart247.entity.ProductEntity;

public class ProductDTO {
	private long id;
	private String name;

	private long categoryId;

	private String img;
	private double price;
	private double discount;
	private String description;
	private int quantity;
	private String unit;
	private ProductDetailsEntity productDetails;

	private CategoryEntity category;

	

	

	public ProductDTO(long id, String name, long categoryId, String img, double price, double discount,
			String description, String unit, int quantity, CategoryEntity category,ProductDetailsEntity productDetails) {
		super();
		this.id = id;

		this.name = name;
		this.categoryId = categoryId;
		this.img = img;
		this.price = price;
		this.discount = discount;
		this.description = description;
		this.unit = unit;
		this.quantity = quantity;
		this.category = category;
		this.productDetails = productDetails;
	}

	public ProductDTO(ProductEntity productEntity) {
		this.id = productEntity.getId();

		//this.category_id = productEntity.getCategory_id();

		this.category = productEntity.getCategory();

		this.name = productEntity.getName();
		this.price = productEntity.getPrice();
		this.discount = productEntity.getDiscount();
		this.img = productEntity.getImg();
		this.description = productEntity.getDescription();
		this.unit = productEntity.getUnit();
		this.quantity = productEntity.getQuantity();
		this.productDetails = productEntity.getProductDetails();
	}

	public ProductDTO() {
	}

	
	
	public ProductDetailsEntity getProductDetails() {
		return productDetails;
	}

	public void setProductDetails(ProductDetailsEntity productDetails) {
		this.productDetails = productDetails;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


//	public long getCategoryId() {
//		return category_id;
//	}
//
//	public void setCategoryId(long category_id) {
//		this.category_id = category_id;
//	}

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}


	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	

//	@Override
//	public String toString() {
//		return "ProductDTO [id=" + id + ", name=" + name + ", subcategoryId=" + category_id + ", img=" + img + "]";
//	}

	public CategoryEntity getCategory() {
		return category;
	}

	public void setCategory(CategoryEntity category) {
		this.category = category;
	}



}
