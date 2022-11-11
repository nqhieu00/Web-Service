package com.banlaptop.shop.service;


import com.banlaptop.shop.dto.ProductCategoryDTO;
import com.banlaptop.shop.entity.ProductCategory;

import java.util.List;

public interface ProductCategoryService extends GenericService<ProductCategory,Long, ProductCategoryDTO> {

    List<ProductCategoryDTO> findProductCategoryByProductId(Long id);
    void deleteByProductId(Long id);
    void deleteByCategoryId(Long id);
}
