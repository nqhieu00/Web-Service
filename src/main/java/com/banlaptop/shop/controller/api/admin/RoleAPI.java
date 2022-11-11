package com.banlaptop.shop.controller.api.admin;

import com.banlaptop.shop.dto.RoleDTO;
import com.banlaptop.shop.entity.Role;
import com.banlaptop.shop.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/roles")
public class RoleAPI extends GenericAPI<Role,Long, RoleDTO> {

    @Override
    public String getRoles() {
        return null;
    }

    @Autowired
    public RoleAPI(GenericService<Role, Long, RoleDTO> genericService) {
        super(genericService);
    }
}
