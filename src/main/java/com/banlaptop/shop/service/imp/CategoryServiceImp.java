package com.banlaptop.shop.service.imp;


import com.banlaptop.shop.dto.CategoryDTO;
import com.banlaptop.shop.entity.Category;
import com.banlaptop.shop.entity.Product;
import com.banlaptop.shop.repository.CategoryRepository;
import com.banlaptop.shop.repository.ProductCategoryRepository;
import com.banlaptop.shop.repository.ProductRepository;
import com.banlaptop.shop.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImp extends GenericServiceImp<Category,Long, CategoryDTO> implements CategoryService {


    ModelMapper mapper=new ModelMapper();


    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductCategoryRepository productCategoryRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    public CategoryServiceImp(CategoryRepository categoryRepository) {
        super(categoryRepository);
    }



    @Override
    public Map<String,String> findAllParentId() {
        Map<String,String> map=new LinkedHashMap<>();
        List<Category> categoryEntities=categoryRepository.findAll();
        for (Category item:
                categoryEntities) {
           map.put(String.valueOf(item.getId()),String.valueOf(item.getId()));
        }
        return map;
    }

    @Override
    public Map<Long, String> mapCategories() {
        Map<Long,String> mapCategory=new LinkedHashMap<>();
        List<Category> categoryEntities=categoryRepository.findAll();
        categoryEntities.forEach(e->mapCategory.put(e.getId(),e.getTitle()));
        return mapCategory;
    }
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<ModelMap> getCategories(){
        List<ModelMap> list = new ArrayList<>();
        categoryTree(null, list);
        return list;
    }



    //Duyet cay theo kieu hau to: Left->Right->Root
    private void categoryTree(Long parentId, List<ModelMap> list){
        String sql = "";
        if(parentId == null)
            sql = "SELECT c.`id`, c.`parent_id`, c.`title` FROM `category` c WHERE c.`parent_id` IS NULL ";
        else
            sql = "SELECT c.`id`, c.`parent_id`, c.`title` FROM `category` c WHERE c.`parent_id` ="+parentId;

        jdbcTemplate.query(sql, rs -> {
            ModelMap m = new ModelMap();
            m.put("id", rs.getLong("id"));
            m.put("text", rs.getString("title"));
            String sqlCountProduct="SELECT COUNT(*) FROM product_category WHERE category_id = "+rs.getLong("id");
            jdbcTemplate.query(sqlCountProduct,results->{
                m.put("countProduct",results.getInt("COUNT(*)"));
            });
            List<ModelMap> nodes = new ArrayList<>();
            categoryTree(rs.getLong("id"), nodes);
            m.put("nodes", nodes);

            list.add(m);
        });
    }



    @Override
    public List<CategoryDTO> findCategoryByProductId(Long Id) {
       return productCategoryRepository.findCategoryByProductId(Id).stream().map(item->transformEntityToDTO(item)).collect(Collectors.toList());
    }

    @Override
    public HashMap<Long,List<CategoryDTO>> mapProductCategory() {
        HashMap<Long,List<CategoryDTO>> map=new LinkedHashMap<>();
        List<Product> products=productRepository.findAll();
        products.forEach(item->map.put(item.getId(),findCategoryByProductId(item.getId())));
        return map;
    }

    @Transactional
    @Override
    public void deleteMultiple(Long[] ids) {
        for (Long id:ids){
            delete(id);
        }
    }

    @Transactional
    @Override
    public void delete(Long id) {
        productCategoryRepository.deleteByCategoryId(id);
        super.delete(id);
    }

    @Override
    public Integer getTotalItem() {
        return (int) categoryRepository.count();
    }

    @Override
    public Category transformDTOToEntity(CategoryDTO element) {

        return mapper.map(element, Category.class);
    }

    @Override
    public CategoryDTO transformEntityToDTO(Category element) {
        return mapper.map(element,CategoryDTO.class);
    }


}
