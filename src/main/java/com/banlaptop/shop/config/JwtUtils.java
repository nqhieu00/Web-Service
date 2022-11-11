package com.banlaptop.shop.config;

import com.banlaptop.shop.dto.MyUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {

    @Value("${bezkoder.app.jwtSecret}")
    private String jwtSecret;

    @Value("${bezkoder.app.jwtExpirationMs}")
    private String jwtExpirationMs;

    public String generateJwtToken(Authentication authentication){
        MyUser userDetails = (MyUser) authentication.getPrincipal();
        long time=System.currentTimeMillis()+Long.valueOf(jwtExpirationMs);
        Date date=new Date();
        Date dateExpiration=new Date(time);
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(date)
                .setExpiration(dateExpiration)
                .signWith(SignatureAlgorithm.HS512,jwtSecret)
                .compact();
    }
    public String getUserNameFromJwtToken(String token){
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken){
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        }
        catch (SignatureException e){

        }
        return false;
    }
}
