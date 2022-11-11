package com.banlaptop.shop.repository;



import com.banlaptop.shop.entity.Category;
import com.banlaptop.shop.entity.ProductCategory;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductCategoryRepository extends GenericRepository<ProductCategory,Long>{

    @Query("SELECT C FROM ProductCategory as PC INNER JOIN PC.category as C "+
            "WHERE PC.product.id=?1")
    List<Category> findCategoryByProductId(Long Id);

    List<ProductCategory> findProductCategoryByProductId(Long Id);

    void deleteByProductId(Long id);

    void deleteByCategoryId(Long id);
}
