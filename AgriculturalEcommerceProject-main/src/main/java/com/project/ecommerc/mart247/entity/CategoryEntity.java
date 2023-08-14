package com.project.ecommerc.mart247.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.project.ecommerc.mart247.DTO.CategoryDTO;

@Table(name = "category")
@Entity
public class CategoryEntity {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "name")
	private String name;

	@Column(name = "parent_id")
	private long parentId;

	@Column(columnDefinition = "LONGTEXT", name = "img")
	private String img;

	@Column(columnDefinition = "datetime", name = "created_at")
	private LocalDateTime createdAt;

	@Column(columnDefinition = "datetime", name = "updated_at")
	private LocalDateTime updatedAt;

	@Column(columnDefinition = "datetime", name = "deleted_at")
	private LocalDateTime deletedAt;

	 @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE)
	    private List<ProductEntity> products;
	
	

	public CategoryEntity() {
	};

	public CategoryEntity(CategoryDTO categoryDTO) {
		this.id = categoryDTO.getId();
		this.name = categoryDTO.getName();
		this.parentId = categoryDTO.getParentId();
		this.img = categoryDTO.getImg();
	}

	public CategoryEntity(long id, String name, long parentId, String img) {
		super();
		this.id = id;
		this.name = name;
		this.parentId = parentId;
		this.img = img;
	}

	
	
	public List<ProductEntity> getProducts() {
		return products;
	}

	public void setProducts(List<ProductEntity> products) {
		this.products = products;
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

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
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

	@Override
	public String toString() {
		return "CategoryEntity [id=" + id + ", name=" + name + ", parentId=" + parentId + ", img=" + img + "]";
	}

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
