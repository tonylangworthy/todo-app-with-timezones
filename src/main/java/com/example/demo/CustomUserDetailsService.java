package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.Optional;

@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        User user = optionalUser.orElseThrow(() -> new EntityNotFoundException("User ["+username+"] not found!"));
        System.out.println(user.getUsername());

        CustomUserDetails customUserDetails = new CustomUserDetails(
                user.getUsername(),
                user.getPassword(),
                true,
                true,
                true,
                true,
                Collections.emptyList());

        System.out.println(customUserDetails.toString());
        return customUserDetails;

    }
}
