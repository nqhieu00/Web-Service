package com.banlaptop.shop.service.imp;

import com.banlaptop.shop.dto.RoleDTO;
import com.banlaptop.shop.entity.Role;
import com.banlaptop.shop.repository.GenericRepository;
import com.banlaptop.shop.service.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImp extends GenericServiceImp<Role,Long, RoleDTO> implements RoleService {


    ModelMapper mapper=new ModelMapper();


    @Autowired
    public RoleServiceImp(GenericRepository<Role, Long> repository) {
        super(repository);
    }

    @Override
    public Role transformDTOToEntity(RoleDTO element) {
        return mapper.map(element,Role.class);
    }

    @Override
    public RoleDTO transformEntityToDTO(Role element) {
        return mapper.map(element,RoleDTO.class);
    }
}
