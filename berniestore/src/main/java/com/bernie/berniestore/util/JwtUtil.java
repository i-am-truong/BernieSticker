package com.bernie.berniestore.util;

import com.bernie.berniestore.constants.ApplicationConstants;
import com.bernie.berniestore.entity.Customer;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtUtil {

    private final Environment environment;

    public String generateJwtToken (Authentication authentication) {
        String jwt = "";
        String secret = environment.getProperty(ApplicationConstants.JWT_SECRET_KEY,
                ApplicationConstants.JWT_SECRET_DEFAULT_VALUE);
        SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        Customer fetchedCustomer = (Customer) authentication.getPrincipal();
        jwt = Jwts.builder().issuer("Bernie Store")
                .subject("JWT Token")
                .claim("username", fetchedCustomer.getName())
                .claim("email", fetchedCustomer.getEmail())
                .claim("mobileNumber", fetchedCustomer.getMobileNumber())
                .claim("roles", authentication.getAuthorities().stream().map(
                        GrantedAuthority::getAuthority).collect(Collectors.joining(",")))
                .issuedAt(new Date())
                .expiration(new Date((new Date()).getTime() + 60 * 60 * 1000))
                .signWith(secretKey)
                .compact();
        return jwt;
    }
}
