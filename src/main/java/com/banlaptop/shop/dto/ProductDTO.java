package com.banlaptop.shop.dto;

import java.util.Date;

public class ProductDTO extends BaseDTO {
	
	private Long id;
	private String title;
	private String metaTitle;
	private String slug;
	private String summary;
	private Integer type;
	private String sku;
	private float price;
	private float discount;
	private int quantity;
	private Integer shop;
	private Date publishedAt;
	private Date startsAt;
	private Date endsAt;
	private String content;
	private String thumbnail;

	public ProductDTO(Long id, String title, String metaTitle, String slug, String summary, Integer type, String sku, float price, float discount, int quantity, Integer shop, Date publishedAt, Date startsAt, Date endsAt, String content, String thumbnail) {
		this.id = id;
		this.title = title;
		this.metaTitle = metaTitle;
		this.slug = slug;
		this.summary = summary;
		this.type = type;
		this.sku = sku;
		this.price = price;
		this.discount = discount;
		this.quantity = quantity;
		this.shop = shop;
		this.publishedAt = publishedAt;
		this.startsAt = startsAt;
		this.endsAt = endsAt;
		this.content = content;
		this.thumbnail = thumbnail;
	}

	public ProductDTO() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMetaTitle() {
		return metaTitle;
	}

	public void setMetaTitle(String metaTitle) {
		this.metaTitle = metaTitle;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
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

	public Integer getShop() {
		return shop;
	}

	public void setShop(Integer shop) {
		this.shop = shop;
	}

	public Date getPublishedAt() {
		return publishedAt;
	}

	public void setPublishedAt(Date publishedAt) {
		this.publishedAt = publishedAt;
	}

	public Date getStartsAt() {
		return startsAt;
	}

	public void setStartsAt(Date startsAt) {
		this.startsAt = startsAt;
	}

	public Date getEndsAt() {
		return endsAt;
	}

	public void setEndsAt(Date endsAt) {
		this.endsAt = endsAt;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public float getCurrentPrice(){
		 return price*(1-discount/100);
	}

	@Override
	public String toString() {
		return "ProductDTO{" +
				"id=" + id +
				", title='" + title + '\'' +
				", metaTitle='" + metaTitle + '\'' +
				", slug='" + slug + '\'' +
				", summary='" + summary + '\'' +
				", type=" + type +
				", sku='" + sku + '\'' +
				", price=" + price +
				", discount=" + discount +
				", quantity=" + quantity +
				", shop=" + shop +
				", publishedAt=" + publishedAt +
				", startsAt=" + startsAt +
				", endsAt=" + endsAt +
				", content='" + content + '\'' +
				", thumbnail='" + thumbnail + '\'' +
				'}';
	}
}
