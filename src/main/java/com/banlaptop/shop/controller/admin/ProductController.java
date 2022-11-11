package com.banlaptop.shop.controller.admin;

import com.banlaptop.shop.dto.ProductDTO;
import com.banlaptop.shop.service.CategoryService;
import com.banlaptop.shop.service.ProductCategoryService;
import com.banlaptop.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    ProductCategoryService productCategoryService;

    @Autowired
    CategoryService categoryService;

    @GetMapping
    public ModelAndView showProduct(){
        ModelAndView modelAndView=new ModelAndView("/admin/layouts/product/products");
        modelAndView.addObject("productDTOs",productService.getAll());
        modelAndView.addObject("mapProductCategoryDTO",categoryService.mapProductCategory());
        return modelAndView;
    }
    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView modelAndView=new ModelAndView("/admin/layouts/product/add");
        ProductDTO productDTO=new ProductDTO();
        modelAndView.addObject("tree",categoryService.getCategories());
        modelAndView.addObject("productDTO",productDTO);
        return modelAndView;
    }

    @GetMapping(value = "/{id}")
    public ModelAndView edit(@PathVariable Long id){
        ModelAndView modelAndView=new ModelAndView("/admin/layouts/product/edit");
        modelAndView.addObject("tree",categoryService.getCategories());
        ProductDTO productDTO=productService.get(id);
        modelAndView.addObject("productDTO",productDTO);
        modelAndView.addObject("productCategoriesDTO",productCategoryService.findProductCategoryByProductId(id));
        return modelAndView;
    }
}
