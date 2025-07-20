package com.springbootjpa.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenHelper  jwtTokenHelper;

    public JwtAuthenticationFilter(UserDetailsService userDetailsService, JwtTokenHelper jwtTokenHelper) {
        this.userDetailsService = userDetailsService;
        this.jwtTokenHelper = jwtTokenHelper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 1. Get Token from request
        String requestToken = request.getHeader("Authorization");

        // Token will starts with Bearer
        String username = null;
        String token = null;
        if(requestToken != null && requestToken.startsWith("Bearer")){
          token = requestToken.substring(7);
          try {
              username = jwtTokenHelper.getUsernameFromToken(token);
          }catch(IllegalArgumentException ex){
              System.out.println("Unable to get JWT Token.");
              ex.printStackTrace();
          }catch(ExpiredJwtException ex){
              System.out.println("JWT Token has expired");
              ex.printStackTrace();
          }catch(MalformedJwtException ex){
              System.out.println("Invalid JWT");
              ex.printStackTrace();
          }
        }else{
            System.out.println("JWT Token doesn't begin with Bearer.");
        }

        // 2. Validate the token
        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
           UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if(jwtTokenHelper.validateToken(token, userDetails)){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }else{
                System.out.println("Invalid JWT Token.");
            }
        }else{
            System.out.println("Username is null or context is not null.");
        }

        filterChain.doFilter(request, response);
    }
}