package com.banlaptop.shop.controller.web;

import com.banlaptop.shop.dto.CartDTO;
import com.banlaptop.shop.dto.MyUser;
import com.banlaptop.shop.service.CartItemService;
import com.banlaptop.shop.service.CartService;
import com.banlaptop.shop.service.ProductService;
import com.banlaptop.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;


@Controller(value = "homeControllerOfWeb")
public class HomeController {

    @Autowired
    ProductService productService;

    @Autowired
    CartItemService cartItemService;

    @Autowired
    UserService userService;

    @Autowired
    CartService cartService;

    @RequestMapping( value = {"/"})
    public ModelAndView home(Authentication authentication){
        ModelAndView modelAndView = new ModelAndView("/web/layouts/home");
        modelAndView.addObject("products",productService.getAll());
        if(authentication!=null){
            MyUser user = (MyUser) authentication.getPrincipal();
            Optional<CartDTO> cartDTO=Optional.ofNullable(cartService.findByUserId(user.getId()));
            if(cartDTO.isPresent()){
                modelAndView.addObject("cartId",cartDTO.get().getId());
            }
            modelAndView.addObject("carts",cartItemService.findCartItemsByUserId(user.getId()));
        }


        return modelAndView;
    }
}
