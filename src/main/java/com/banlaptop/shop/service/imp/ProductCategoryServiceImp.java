package com.banlaptop.shop.service.imp;


import com.banlaptop.shop.dto.ProductCategoryDTO;
import com.banlaptop.shop.entity.ProductCategory;
import com.banlaptop.shop.repository.ProductCategoryRepository;
import com.banlaptop.shop.repository.ProductRepository;
import com.banlaptop.shop.service.ProductCategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

;

@Service
public class ProductCategoryServiceImp extends GenericServiceImp<ProductCategory,Long, ProductCategoryDTO>
        implements ProductCategoryService {


    @Autowired
    ModelMapper mapper=new ModelMapper();



    @Autowired
    ProductCategoryRepository productCategoryRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    public ProductCategoryServiceImp(ProductCategoryRepository productCategoryRepository) {
        super(productCategoryRepository);
    }

    @Override
    public List<ProductCategoryDTO> findProductCategoryByProductId(Long id){
        return productCategoryRepository.findProductCategoryByProductId(id)
                .stream()
                .map(item->transformEntityToDTO(item))
                .collect(Collectors.toList());

    }

    @Transactional
    @Override
    public void deleteByProductId(Long id) {
        productCategoryRepository.deleteByProductId(id);
    }

    @Transactional
    @Override
    public void deleteByCategoryId(Long id) {
        productCategoryRepository.deleteByCategoryId(id);
    }


    @Override
    public ProductCategoryDTO transformEntityToDTO(ProductCategory element) {
        return mapper.map(element,ProductCategoryDTO.class);
    }

    @Override
    public ProductCategory transformDTOToEntity(ProductCategoryDTO element) {
        return mapper.map(element,ProductCategory.class);
    }



}
