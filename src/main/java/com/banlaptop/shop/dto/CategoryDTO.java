package com.banlaptop.shop.dto;

public class CategoryDTO extends BaseDTO {
    private Long id;
    private CategoryDTO parent;
    private String title;
    private String metaTitle;
    private String slug;
    private String content;




    public CategoryDTO() {
    }

    public CategoryDTO getParent() {
        return parent;
    }

    public void setParent(CategoryDTO parent) {
        this.parent = parent;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "CategoryDTO{" +
                "id=" + id +
                ", parent=" + parent +
                ", title='" + title + '\'' +
                ", metaTitle='" + metaTitle + '\'' +
                ", slug='" + slug + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
