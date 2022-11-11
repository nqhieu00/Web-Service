package com.banlaptop.shop.controller.api.admin;

import com.banlaptop.shop.dto.CartDTO;
import com.banlaptop.shop.dto.CartItemDTO;
import com.banlaptop.shop.dto.MyUser;
import com.banlaptop.shop.dto.UserDTO;
import com.banlaptop.shop.entity.CartItem;
import com.banlaptop.shop.exception.GenericException;
import com.banlaptop.shop.service.CartItemService;
import com.banlaptop.shop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController(value = "cartItemsApiOfAdmin")
@RequestMapping("/api/cartItems")
public class CartItemAPI extends GenericAPI<CartItem, Long, CartItemDTO> {


    @Autowired
    CartItemService cartItemService;

    @Autowired
    CartService cartService;

    @Autowired
    public CartItemAPI(CartItemService genericService) {
        super(genericService);
    }

    @GetMapping(value = "/user/{id}")
    public ResponseEntity findByUserId(@PathVariable Long id) {
        try {
            return new ResponseEntity(cartItemService.findCartItemsByUserId(id), HttpStatus.OK);
        } catch (GenericException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        }

    }

    @Override
    public String getRoles() {
        return null;
    }

    @Override
    public ResponseEntity create(@RequestBody CartItemDTO dto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            MyUser user = (MyUser) authentication.getPrincipal();
            if(!cartService.existsByUserId(user.getId())){
                CartDTO cartDTO = new CartDTO ();
                UserDTO userDTO = new UserDTO();
                userDTO.setId(user.getId());
                cartDTO.setUser(userDTO);
                dto.setCart(cartService.create(cartDTO));
            }
        }else {
            System.out.println("You are not logged in");
        }
        return super.create(dto);
    }
}
