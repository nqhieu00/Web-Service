package com.banlaptop.shop.repository;

import com.banlaptop.shop.entity.Cart;

public interface CartRepository extends GenericRepository<Cart,Long> {
    Cart findByUserId(Long userId);
    Boolean existsByUserId(Long UserId);
}
