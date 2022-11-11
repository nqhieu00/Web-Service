package com.banlaptop.shop.service;

import com.banlaptop.shop.dto.CartDTO;
import com.banlaptop.shop.entity.Cart;

public interface CartService extends GenericService<Cart,Long, CartDTO> {
    CartDTO findByUserId(Long userId);

    Boolean existsByUserId(Long userId);
}
