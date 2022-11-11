package com.banlaptop.shop.service;

import com.banlaptop.shop.dto.CartItemDTO;
import com.banlaptop.shop.entity.CartItem;

import java.util.List;

public interface CartItemService extends GenericService<CartItem,Long, CartItemDTO>{
    List<CartItemDTO> findCartItemsByCartId(Long cartId);
    List<CartItemDTO> findCartItemsByUserId(Long userId);


}
