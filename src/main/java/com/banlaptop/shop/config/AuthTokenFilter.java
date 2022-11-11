package com.banlaptop.shop.config;


import com.banlaptop.shop.service.imp.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthTokenFilter extends OncePerRequestFilter {


    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt=parseJwt(request);
            if(jwt!=null && jwtUtils.validateJwtToken(jwt)){
                String username=jwtUtils.getUserNameFromJwtToken(jwt);

                UserDetails userDetails=userDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken auth=new UsernamePasswordAuthenticationToken(
                        userDetails,null,userDetails.getAuthorities()
                );
                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        catch (Exception e){

        }
        filterChain.doFilter(request,response);
    }

    private String parseJwt(HttpServletRequest request) {

        String headerAuth=request.getHeader("Authorization");
        if(StringUtils.hasText(headerAuth)&&headerAuth.startsWith("Bearer")){
            return headerAuth.substring(7,headerAuth.length());
        }
        return null;
    }
}
