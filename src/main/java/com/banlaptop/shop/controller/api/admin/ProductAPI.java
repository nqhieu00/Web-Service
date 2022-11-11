package com.banlaptop.shop.controller.api.admin;


import com.banlaptop.shop.dto.ProductDTO;
import com.banlaptop.shop.entity.Product;
import com.banlaptop.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController(value = "productApiOfAdmin")
@RequestMapping(value = "/api/products")
public class ProductAPI extends GenericAPI<Product,Long, ProductDTO> {

    @Autowired
    ProductService productService;

    @Autowired
    public ProductAPI(ProductService productService) {
        super(productService);
    }

    @DeleteMapping
    void deleteCategory(@RequestBody Long[] id){
        productService.deleteMultiple(id);
    }


    @Override
    public String getRoles() {
        return "USER, ADMIN";
    }
}
