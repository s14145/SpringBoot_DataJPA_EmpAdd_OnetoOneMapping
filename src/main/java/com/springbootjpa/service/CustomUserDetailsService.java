package com.springbootjpa.service;

import com.springbootjpa.entity.Users;
import com.springbootjpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service("UserService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Optional<Users> userOptional = userRepository.findByUsername(username);
       if(userOptional.isPresent()){
           throw new UsernameNotFoundException("Provided username doesn't exist.");
       }
       Users user = userOptional.get();
       return new CustomUserDetails(user);
    }
}