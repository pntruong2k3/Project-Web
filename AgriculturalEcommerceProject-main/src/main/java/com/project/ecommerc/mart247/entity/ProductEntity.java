package com.project.ecommerc.mart247.entity;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.project.ecommerc.mart247.DTO.ProductDTO;

@Table(name = "product")
@Entity
public class ProductEntity {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "name")
	private String name;

	@Column(name = "price")
	private double price;

//	@Column(name = "category_id")
//	private long category_id;

	@Column(name = "discount")
	private double discount;

	@Column(columnDefinition = "LONGTEXT", name = "img")
	private String img;

	@Column(name = "description")
	private String description;

	@Column(name = "unit")
	private String unit;

	@Column(name = "quantity")
	private int quantity;

	@OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
	@JoinColumn(name = "product_details_id")
	private ProductDetailsEntity productDetails;

	@Column(columnDefinition = "datetime", name = "created_at")
	private LocalDateTime createdAt;

	@Column(columnDefinition = "datetime", name = "updated_at")
	private LocalDateTime updatedAt;

	@Column(columnDefinition = "datetime", name = "deleted_at")
	private LocalDateTime deletedAt;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "category_id")
	private CategoryEntity category;

	public ProductEntity() {
	}

	public ProductEntity(long id, String name, double price, double discount, String img, String description,
			String unit, int quantity, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt,
			CategoryEntity category, ProductDetailsEntity productDetails) {
		super();
		this.id = id;
		this.name = name;
		// this.category_id = category_id;
		this.price = price;
		this.discount = discount;
		this.img = img;
		this.description = description;
		this.unit = unit;
		this.quantity = quantity;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.deletedAt = deletedAt;
		this.category = category;
		this.productDetails = productDetails;
	}

	public ProductEntity(ProductDTO productDTO) {
		this.id = productDTO.getId();
		this.category = productDTO.getCategory();
		this.name = productDTO.getName();
		this.price = productDTO.getPrice();
		this.discount = productDTO.getDiscount();
		this.img = productDTO.getImg();
		this.description = productDTO.getDescription();
		this.unit = productDTO.getUnit();
		this.quantity = productDTO.getQuantity();
		this.productDetails = productDTO.getProductDetails();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public ProductDetailsEntity getProductDetails() {
		return productDetails;
	}

	public void setProductDetails(ProductDetailsEntity productDetails) {
		this.productDetails = productDetails;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
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

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public LocalDateTime getDeletedAt() {
		return deletedAt;
	}

	public void setDeletedAt(LocalDateTime deletedAt) {
		this.deletedAt = deletedAt;
	}

	public CategoryEntity getCategory() {
		return category;
	}

	public void setCategory(CategoryEntity category) {
		this.category = category;
	}

//	public long getCategory_id() {
//		return category_id;
//	}
//
//
//	public void setCategory_id(long category_id) {
//		this.category_id = category_id;
//	}

	@PrePersist
	protected void onCreate() {
		if (createdAt == null) {
			createdAt = LocalDateTime.now();
		}

	}

	@PreUpdate
	protected void onUpdate() {
		if (updatedAt == null) {
			updatedAt = LocalDateTime.now();
		}
	}

}