package com.banlaptop.shop.controller.web;

import com.banlaptop.shop.dto.CartItemDTO;
import com.banlaptop.shop.dto.MyUser;
import com.banlaptop.shop.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping( value = "/user/cart" )
public class CartController {

    @Autowired
    CartItemService cartItemService;

   @GetMapping("")
    public ModelAndView showCart(Authentication authentication){
        List<CartItemDTO> cartItemDTOS =new ArrayList<>();
        if(authentication!=null){
            MyUser user=(MyUser) authentication.getPrincipal();
            cartItemDTOS=cartItemService.findCartItemsByUserId(user.getId());
        }
        ModelAndView modelAndView=new ModelAndView("/web/layouts/cart");
        modelAndView.addObject("carts",cartItemDTOS);

        return modelAndView;
    }

}
