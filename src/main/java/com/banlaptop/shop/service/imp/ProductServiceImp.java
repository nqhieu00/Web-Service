package com.banlaptop.shop.service.imp;


import com.banlaptop.shop.dto.ProductDTO;
import com.banlaptop.shop.entity.Product;
import com.banlaptop.shop.repository.ProductCategoryRepository;
import com.banlaptop.shop.repository.ProductRepository;
import com.banlaptop.shop.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ProductServiceImp extends GenericServiceImp<Product,Long, ProductDTO> implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductCategoryRepository productCategoryRepository;

    ModelMapper mapper=new ModelMapper();

    @Autowired
    public ProductServiceImp(ProductRepository productRepository) {
        super(productRepository);
    }

    @Override
    @Transactional
    public void deleteMultiple(Long[] ids){
        for (Long id :ids){
            delete(id);
        }
    }


    @Override
    @Transactional
    public void delete(Long id) {
        productCategoryRepository.deleteByProductId(id);
        super.delete(id);
    }



    @Override
    public Product transformDTOToEntity(ProductDTO element) {
        return mapper.map(element,Product.class);
    }

    @Override
    public ProductDTO transformEntityToDTO(Product element) {
        return mapper.map(element,ProductDTO.class);
    }
}
