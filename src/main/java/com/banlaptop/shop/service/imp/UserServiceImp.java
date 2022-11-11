package com.banlaptop.shop.service.imp;

import com.banlaptop.shop.dto.UserDTO;
import com.banlaptop.shop.entity.User;
import com.banlaptop.shop.repository.UserRepository;
import com.banlaptop.shop.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserServiceImp extends GenericServiceImp<User,Long, UserDTO> implements UserService {


    @Autowired
    ModelMapper mapper=new ModelMapper();

    @Autowired
    UserRepository userRepository;




    private void encodePassword(UserDTO userDTO){
        if(userDTO.getPassword()!=null){
            PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
            userDTO.setPasswordHash(passwordEncoder.encode(userDTO.getPassword()));
        }

    }

    @Override
    public UserDTO create(UserDTO element) {
        encodePassword(element);
        return super.create(element);
    }

    @Override
    public UserDTO update(UserDTO element, Long id) {
        encodePassword(element);
        return super.update(element, id);
    }

    @Autowired
    public UserServiceImp(UserRepository repository) {
        super(repository);
    }

    @Override
    public User transformDTOToEntity(UserDTO element) {

        return mapper.map(element, User.class);
    }

    @Override
    public UserDTO transformEntityToDTO(User element) {
        return mapper.map(element,UserDTO.class);
    }

    @Override
    public UserDTO findByEmail(String email) {
        Optional<User> user= Optional.ofNullable(userRepository.findByEmail(email));
        if(user.isPresent()){
            return transformEntityToDTO(userRepository.findByEmail(email));
        }
        else {
            return null;
        }

    }


}
