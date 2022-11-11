package com.banlaptop.shop.service.imp;


import com.banlaptop.shop.dto.MyUser;
import com.banlaptop.shop.entity.User;
import com.banlaptop.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User user=userRepository.findByEmail(s);
        if(user==null){
            throw new UsernameNotFoundException("User " + s + " was not found in the database");
        }
        return  new MyUser(user);
    }
}
