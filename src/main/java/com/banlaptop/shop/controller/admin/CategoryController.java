package com.banlaptop.shop.controller.admin;

import com.banlaptop.shop.dto.CategoryDTO;
import com.banlaptop.shop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping
    public ModelAndView showCategories(){
        ModelAndView modelAndView=new ModelAndView("/admin/layouts/category/categories");
        modelAndView.addObject("categoriesDTO",categoryService.getAll());
        modelAndView.addObject("tree",categoryService.getCategories());
        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView modelAndView=new ModelAndView("/admin/layouts/category/add");
        CategoryDTO categoryDTO=new CategoryDTO();
        modelAndView.addObject("categoryDTO",categoryDTO);
        modelAndView.addObject("tree",categoryService.getCategories());
        return modelAndView;
    }

    @GetMapping(value = "/{id}")
    public ModelAndView edit(@PathVariable Long id){
        ModelAndView modelAndView=new ModelAndView("/admin/layouts/category/edit");
        try {
            modelAndView.addObject("categoryDTO",categoryService.get(id));
            modelAndView.addObject("tree",categoryService.getCategories());
        }
        catch (Exception e){

        }
        return modelAndView;
    }


}
