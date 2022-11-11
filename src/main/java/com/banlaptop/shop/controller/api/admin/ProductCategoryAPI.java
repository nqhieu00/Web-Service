package com.banlaptop.shop.controller.api.admin;


import com.banlaptop.shop.dto.ProductCategoryDTO;
import com.banlaptop.shop.entity.ProductCategory;
import com.banlaptop.shop.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/productCategories")
public class ProductCategoryAPI extends GenericAPI<ProductCategory,Long, ProductCategoryDTO> {


    @Autowired
    public ProductCategoryAPI(ProductCategoryService genericService) {
        super(genericService);
    }

    @Autowired
    ProductCategoryService productCategoryService;


    @Override
    public String getRoles() {
        return null;
    }
}
