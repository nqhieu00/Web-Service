package com.banlaptop.shop.dto;

import java.sql.Timestamp;

public class ProductReviewDTO {
	private long id;
	private long productId;
	private long parentId;
	private String title;
	private int rating;
	private Timestamp createAt;
	private Timestamp updateAt;
	private Timestamp publisheAt;
	private String content;
	public ProductReviewDTO() {
		// TODO Auto-generated constructor stub
	}
	public ProductReviewDTO(long id, long productId, long parentId, String title, int rating, Timestamp createAt,
			Timestamp updateAt, Timestamp publisheAt, String content) {
		
		this.id = id;
		this.productId = productId;
		this.parentId = parentId;
		this.title = title;
		this.rating = rating;
		this.createAt = createAt;
		this.updateAt = updateAt;
		this.publisheAt = publisheAt;
		this.content = content;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public long getParentId() {
		return parentId;
	}
	public void setParentId(long parentId) {
		this.parentId = parentId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public Timestamp getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Timestamp createAt) {
		this.createAt = createAt;
	}
	public Timestamp getUpdateAt() {
		return updateAt;
	}
	public void setUpdateAt(Timestamp updateAt) {
		this.updateAt = updateAt;
	}
	public Timestamp getPublisheAt() {
		return publisheAt;
	}
	public void setPublisheAt(Timestamp publisheAt) {
		this.publisheAt = publisheAt;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

}
