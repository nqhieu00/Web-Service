package com.banlaptop.shop.service;



import com.banlaptop.shop.dto.ProductDTO;
import com.banlaptop.shop.entity.Product;

public interface ProductService extends GenericService<Product,Long, ProductDTO> {

    void deleteMultiple(Long[] id);
}
