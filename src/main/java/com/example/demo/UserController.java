package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserController {

    private UserRepository userRepository;

    private UserSettingRepository userSettingRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserRepository userRepository, UserSettingRepository userSettingRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userSettingRepository = userSettingRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody User user ) {

        Optional<User> optionalUser = userRepository.findByUsername(user.getUsername());
        if(optionalUser.isEmpty()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            User newUser = userRepository.save(user);
            user.getUserSettings().forEach(setting -> userSettingRepository.save(setting).setUser(newUser));

            return ResponseEntity.ok(user);
        }
        return ResponseEntity.badRequest().build();
    }
}
