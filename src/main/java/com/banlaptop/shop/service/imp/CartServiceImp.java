package com.banlaptop.shop.service.imp;

import com.banlaptop.shop.dto.CartDTO;
import com.banlaptop.shop.entity.Cart;
import com.banlaptop.shop.exception.CartException;
import com.banlaptop.shop.exception.UserException;
import com.banlaptop.shop.repository.CartRepository;
import com.banlaptop.shop.repository.UserRepository;
import com.banlaptop.shop.service.CartService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImp extends GenericServiceImp<Cart,Long, CartDTO> implements CartService {

    @Autowired
    ModelMapper mapper=new ModelMapper();

    @Autowired
    UserRepository userRepository;

    @Autowired
    CartRepository cartRepository;


    @Autowired
    public CartServiceImp(CartRepository repository) {
        super(repository);
    }



    @Override
    public Cart transformDTOToEntity(CartDTO element) {
        return mapper.map(element,Cart.class);
    }

    @Override
    public CartDTO transformEntityToDTO(Cart element) {
        return mapper.map(element,CartDTO.class);
    }

    @Override
    public CartDTO findByUserId(Long userId) {
        Optional<Cart> cart=Optional.ofNullable(cartRepository.findByUserId(userId));
        if(cart.isPresent()){
            return transformEntityToDTO(cart.get());
        }
        else {
            return null;
        }

    }

    @Override
    public CartDTO create(CartDTO element) {
        if(!userRepository.existsById(element.getUser().getId())){
            throw new UserException("userId does not exist ");
        }
        if(existsByUserId(element.getUser().getId())){
            throw new CartException("the user has a shopping cart");
        }
        CartDTO  cartDTO=super.create(element);
        return cartDTO;


    }

    @Override
    public Boolean existsByUserId(Long userId){
        return cartRepository.existsByUserId(userId);
    }
}
