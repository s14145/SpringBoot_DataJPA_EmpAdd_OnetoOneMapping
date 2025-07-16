package com.springbootjpa.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import java.util.function.Function;

@Component
public class JwtTokenHelper {

    @Value("${jwt.token.validity}")
    private String jwtTokenValidity;

    private String secret = "JwtTokenKey";

    // Retrieve username from jwt token
    public String getUsernameFromToken(String token){
        return getClaimFromToken(token, Claims::getSubject);
    }

    // Retrieve expiration date from jwt token
    public Date getExpirationDateFromToken(String token){
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver){
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    // Retrieve any other information from token we will need the secret key
    public Claims getAllClaimsFromToken(String token){
        return  Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    // Check if token has expired
    public Boolean isTokenExpired(String token){
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    // Generate token from user
    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername());
    }

    /**
     * While creating token:
     * 1. Define claims of the token like Issuer, Expiration, Subject, and ID.
     * 2. Sign the JWT using HS512 algorithm and secret key
     * 3. According to the JWS Compact Serialization compacting of the JWT to a URL-safe string.
     *
     * @param claims
     * @param subject
     * @return
     */
    private String doGenerateToken(Map<String, Object> claims, String subject){
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(jwtTokenValidity) * 100))
                .signWith(SignatureAlgorithm.ES512, secret).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails){
        final String username  = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}