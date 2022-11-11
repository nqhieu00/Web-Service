package com.banlaptop.shop.controller.api.admin;


import com.banlaptop.shop.dto.UserDTO;
import com.banlaptop.shop.entity.User;
import com.banlaptop.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/users")
public class UserAPI extends GenericAPI<User,Long, UserDTO> {

    @Autowired
    public UserAPI(UserService userService) {
        super(userService);
    }

    @Override
    public String getRoles() {
        return "ADMIN";
    }
}
