package com.banlaptop.shop.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cart",uniqueConstraints = @UniqueConstraint(columnNames = "user_id"))
public class Cart extends Base{


    private @Id @GeneratedValue(strategy =GenerationType.IDENTITY) Long id;

    @OneToMany(mappedBy = "cart")
    private List<CartItem> cartItems=new ArrayList<>();

    @OneToOne()
    @JoinColumn(name="user_id")
    private User user;


    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
