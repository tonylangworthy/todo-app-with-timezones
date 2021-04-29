package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserController {

    private UserRepository userRepository;

    private UserSettingRepository userSettingRepository;

    public UserController(UserRepository userRepository, UserSettingRepository userSettingRepository) {
        this.userRepository = userRepository;
        this.userSettingRepository = userSettingRepository;
    }

    @PostMapping("/user")
    public ResponseEntity<?> createUser(@RequestBody User user ) {

        Optional<User> optionalUser = userRepository.findByUsername(user.getUsername());
        if(optionalUser.isEmpty()) {
            User newUser = userRepository.save(user);
            user.getUserSettings().forEach(setting -> userSettingRepository.save(setting).setUser(newUser));

            return ResponseEntity.ok(user);
        }
        return ResponseEntity.badRequest().build();
    }
}
