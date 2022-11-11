package com.banlaptop.shop.service.imp;

import com.banlaptop.shop.dto.CartItemDTO;
import com.banlaptop.shop.entity.CartItem;
import com.banlaptop.shop.entity.Product;
import com.banlaptop.shop.exception.CartException;
import com.banlaptop.shop.exception.ProductException;
import com.banlaptop.shop.repository.CartItemRepository;
import com.banlaptop.shop.repository.CartRepository;
import com.banlaptop.shop.repository.ProductRepository;
import com.banlaptop.shop.service.CartItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartItemServiceImp extends GenericServiceImp<CartItem,Long, CartItemDTO> implements CartItemService {

    private Map<Long,CartItemDTO> cartItemMap=new HashMap<Long,CartItemDTO>();

    @Autowired
    ModelMapper mapper=new ModelMapper();

    @Autowired
    CartItemRepository cartItemRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CartRepository cartRepository;



    @Autowired
    public CartItemServiceImp(CartItemRepository repository) {
        super(repository);
    }



    private void LoadMap(List<CartItemDTO> cartItemDTOS){
        cartItemDTOS.forEach(item->cartItemMap.put(item.getId(),item));
    }


    @Override
    public CartItem transformDTOToEntity(CartItemDTO element) {
        return mapper.map(element,CartItem.class);
    }

    @Override
    public CartItemDTO transformEntityToDTO(CartItem element) {
        return mapper.map(element,CartItemDTO.class);
    }

    @Override
    public List<CartItemDTO> findCartItemsByCartId(Long cartId) {
        List<CartItem> cartItems=cartItemRepository.findCartItemsByCartId(cartId);
        return cartItems.stream().map(e->transformEntityToDTO(e)).collect(Collectors.toList());
    }

    @Override
    public List<CartItemDTO> findCartItemsByUserId(Long userId) {
        List<CartItem> cartItems=cartItemRepository.findCartItemsByUserId(userId);
     //   LoadMap(cartItems.stream().map(e->transformEntityToDTO(e)).collect(Collectors.toList()));
        return cartItems.stream().map(e->transformEntityToDTO(e)).collect(Collectors.toList());
    }


    @Override
    public CartItemDTO create(CartItemDTO element) {
        if(!cartRepository.existsById(element.getCart().getId())){
            throw new CartException("cartId does not exist");
        }
        Optional<Product> productDTO=productRepository.findById(element.getProduct().getId());
        if(productDTO.isPresent()){
            element.setPrice(element.getQuantity()*productDTO.get().getPrice()*(1-productDTO.get().getDiscount()/100));
            element=super.create(element);
            return element;
        }
        else {
            throw new ProductException("productId does not exist");
        }





    }

    @Override
    public CartItemDTO update(CartItemDTO element, Long id) {

       Optional<Product> productDTO=productRepository.findById(element.getProduct().getId());
       if(productDTO.isPresent()){
           element.setPrice(element.getQuantity()*productDTO.get().getPrice()*(1-productDTO.get().getDiscount()/100));
           element=super.update(element,id);
           return element;
       }
       else {
           throw new ProductException("productId does not exist");
       }

    }

    @Override
    public void delete(Long id) {
        super.delete(id);
    }
}
