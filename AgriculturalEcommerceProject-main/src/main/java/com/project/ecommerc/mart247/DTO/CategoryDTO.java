package com.project.ecommerc.mart247.DTO;

import com.project.ecommerc.mart247.entity.CategoryEntity;

import java.util.List;

public class CategoryDTO {
	private long id;
	private String name;
	private long parentId;
	private String img;
	private int productCount;

	private List<CategoryDTO> childCate;

	public CategoryDTO(long id, String name, long parentId, String img) {
		super();
		this.id = id;
		this.name = name;
		this.parentId = parentId;
		this.img = img;
	}
	
	public CategoryDTO(CategoryEntity categoryEntity) {
		this.id = categoryEntity.getId();
		this.name = categoryEntity.getName();
		this.parentId = categoryEntity.getParentId();
		this.img = categoryEntity.getImg();
	}

	public CategoryDTO() {
	}



	public int getProductCount() {
		return productCount;
	}

	public void setProductCount(int productCount) {
		this.productCount = productCount;
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

	public List<CategoryDTO> getChildCate() {
		return childCate;
	}

	public void setChildCate(List<CategoryDTO> childCate) {
		this.childCate = childCate;
	}

	@Override
	public String toString() {
		return "CategoryDTO [id=" + id + ", name=" + name + ", parentId=" + parentId + ", img=" + img + "]";
	}

}
