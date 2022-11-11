package com.banlaptop.shop.service;


import com.banlaptop.shop.dto.UserDTO;
import com.banlaptop.shop.entity.User;

public interface UserService extends GenericService<User,Long, UserDTO> {
    UserDTO findByEmail(String email);

}
