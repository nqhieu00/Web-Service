package com.banlaptop.shop.dto;



public class CartItemDTO {
	private Long id;
	private ProductDTO product;
	private CartDTO cart;
	private String sku;
	private float discount;
	private int quantity;
	private float price;
	public CartItemDTO() {

	}

	public CartItemDTO(Long id, ProductDTO product, CartDTO cart, String sku, float discount, int quantity, float price) {
		this.id = id;
		this.product = product;
		this.cart = cart;
		this.sku = sku;
		this.discount = discount;
		this.quantity = quantity;
		this.price = price;
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

	public CartDTO getCart() {
		return cart;
	}

	public void setCart(CartDTO cart) {
		this.cart = cart;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "CartItemDTO{" +
				"id=" + id +
				", product=" + product +
				", cart=" + cart +
				", sku='" + sku + '\'' +
				", discount=" + discount +
				", quantity=" + quantity +
				", price=" + price +
				'}';
	}
}
