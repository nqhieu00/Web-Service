package com.banlaptop.shop.service;


import com.banlaptop.shop.dto.CategoryDTO;
import com.banlaptop.shop.entity.Category;
import org.springframework.ui.ModelMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface CategoryService extends GenericService<Category,Long, CategoryDTO> {

    List<ModelMap> getCategories();

    void deleteMultiple(Long[] ids);
    Integer getTotalItem();
    Map<String,String> findAllParentId();
    Map<Long,String> mapCategories();

    List<CategoryDTO> findCategoryByProductId(Long Id);
    HashMap<Long,List<CategoryDTO>> mapProductCategory();
}
