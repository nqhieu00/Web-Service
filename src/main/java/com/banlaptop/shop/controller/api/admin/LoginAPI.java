package com.banlaptop.shop.controller.api.admin;


import com.banlaptop.shop.config.JwtUtils;
import com.banlaptop.shop.dto.JwtResponse;
import com.banlaptop.shop.dto.LoginRequest;
import com.banlaptop.shop.dto.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:8080",maxAge = 3600)
@RestController
@RequestMapping("/api/login")
public class LoginAPI {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping
    public ResponseEntity<?> authenticationUser(@Validated @RequestBody LoginRequest loginRequest){
        Authentication authentication=authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt=jwtUtils.generateJwtToken(authentication);
        MyUser userDetails= (MyUser) authentication.getPrincipal();
        List<String> roles=userDetails.getAuthorities().stream()
                .map(item->item.getAuthority())
                .collect(Collectors.toList());
        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),userDetails.getUsername(),roles));
    }
}
