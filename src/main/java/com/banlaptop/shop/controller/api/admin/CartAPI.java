package com.banlaptop.shop.controller.api.admin;

import com.banlaptop.shop.dto.CartDTO;
import com.banlaptop.shop.entity.Cart;
import com.banlaptop.shop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "cartApiOfAdmin")
@RequestMapping("/api/carts")
public class CartAPI extends GenericAPI<Cart,Long, CartDTO> {

    @Autowired
    CartService cartService;

    @Autowired
    public CartAPI(CartService genericService) {
        super(genericService);
    }

    @Override
    public String getRoles() {
        return null;
    }
}
