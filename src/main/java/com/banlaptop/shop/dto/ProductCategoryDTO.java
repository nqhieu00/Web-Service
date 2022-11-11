package com.banlaptop.shop.dto;

public class ProductCategoryDTO {

	private Long id;
	private ProductDTO product;
	private CategoryDTO category;

	public ProductCategoryDTO(Long id,ProductDTO product, CategoryDTO category) {
		this.id=id;
		this.product = product;
		this.category = category;
	}

	public ProductCategoryDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ProductDTO getProduct() {
		return product;
	}

	public void setProduct(ProductDTO product) {
		this.product = product;
	}

	public CategoryDTO getCategory() {
		return category;
	}

	public void setCategory(CategoryDTO category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "ProductCategoryDTO{" +
				"id=" + id +
				", product=" + product +
				", category=" + category +
				'}';
	}
}
