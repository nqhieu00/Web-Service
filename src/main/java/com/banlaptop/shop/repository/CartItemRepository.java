package com.banlaptop.shop.repository;

import com.banlaptop.shop.entity.CartItem;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartItemRepository extends GenericRepository<CartItem,Long> {
    List<CartItem> findCartItemsByCartId(Long cartId);

    @Query("FROM CartItem as cartItem inner join cartItem.cart as cartItem_user  where cartItem_user.user.id= ?1 order by cartItem.id desc " )
    List<CartItem> findCartItemsByUserId(Long userId);

    @Query("SELECT count(cartItem) from CartItem as cartItem inner join cartItem.cart as cartItem_user  where cartItem_user.user.id= ?1")
    Long countByUserId(Long userId);

}
