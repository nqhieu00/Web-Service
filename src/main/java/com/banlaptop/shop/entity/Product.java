package com.banlaptop.shop.entity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "product")
public class Product extends Base {



    @OneToMany(mappedBy = "product")
    List<CartItem> cartItems=new ArrayList<>();


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

    @OneToMany(mappedBy = "product")
    List<ProductCategory> productCategory=new ArrayList<>();

    @OneToMany(mappedBy = "product")
    List<ProductMetaEntity> productMetas=new ArrayList<>();

    @OneToMany(mappedBy = "product")
    List<ProductReview> productReviews=new ArrayList<>();

    @OneToMany(mappedBy = "product")
    List<OrderItem> orderItems=new ArrayList<>();


    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public List<ProductMetaEntity> getProductMetas() {
        return productMetas;
    }

    public void setProductMetas(List<ProductMetaEntity> productMetas) {
        this.productMetas = productMetas;
    }

    public List<ProductReview> getProductReviews() {
        return productReviews;
    }

    public void setProductReviews(List<ProductReview> productReviews) {
        this.productReviews = productReviews;
    }

    public List<ProductCategory> getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(List<ProductCategory> productCategory) {
        this.productCategory = productCategory;
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

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

}
