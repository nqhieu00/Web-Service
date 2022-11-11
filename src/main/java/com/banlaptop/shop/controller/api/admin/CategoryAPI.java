package com.banlaptop.shop.controller.api.admin;


import com.banlaptop.shop.dto.CategoryDTO;
import com.banlaptop.shop.entity.Category;
import com.banlaptop.shop.service.CategoryService;
import com.banlaptop.shop.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "categoryApiOfAdmin")
@RequestMapping("/api/categories")
public class CategoryAPI extends GenericAPI<Category,Long, CategoryDTO>{

    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductCategoryService productCategoryService;

    @Autowired
    public CategoryAPI(CategoryService categoryService) {
        super(categoryService);
    }

    @DeleteMapping
    void deleteCategory(@RequestBody Long[] id){
       categoryService.deleteMultiple(id);
    }

    @Override
    public String getRoles() {
        return null;
    }
}