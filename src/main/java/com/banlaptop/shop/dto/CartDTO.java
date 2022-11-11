package com.banlaptop.shop.dto;



public class CartDTO {
	private long id;
	private UserDTO user;
	private float totalPrice;

	public CartDTO() {
	}

	public CartDTO(long id, UserDTO user, float totalPrice) {
		this.id = id;
		this.user = user;
		this.totalPrice = totalPrice;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "CartDTO{" +
				"id=" + id +
				", user=" + user +
				", totalPrice=" + totalPrice +
				'}';
	}
}
