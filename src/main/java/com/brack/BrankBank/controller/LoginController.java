package com.brack.BrankBank.controller;

import com.brack.BrankBank.model.Users;
import com.brack.BrankBank.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Users users){
        Users savedUser = null;
        ResponseEntity response = null;
        try {
            String hashPassword = passwordEncoder.encode(users.getPassword());
            users.setPassword(hashPassword);
            savedUser = userRepository.save(users);
            if(savedUser.getId() > 0){
                response = ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body("User details registered");
            }
        } catch (Exception e){
            response = ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Exception occurred: " + e.getMessage());
        }

        return response;
    }


}
