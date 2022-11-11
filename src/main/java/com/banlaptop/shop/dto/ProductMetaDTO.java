package com.banlaptop.shop.dto;

public class ProductMetaDTO {
	private long id;
	private long productId;
	private String key;
	private String content;
	public ProductMetaDTO() {
		// TODO Auto-generated constructor stub
	}
	public ProductMetaDTO(long id, long productId, String key, String content) {
		
		this.id = id;
		this.productId = productId;
		this.key = key;
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
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
	
}
