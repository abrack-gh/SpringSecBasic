package com.brack.BrankBank.controller;

import com.brack.BrankBank.model.Users;
import com.brack.BrankBank.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
public class LoginController {

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @PostMapping("/register")
//    public ResponseEntity<String> registerUser(@RequestBody Users users){
//        Users savedUser = null;
//        ResponseEntity response = null;
//        try {
//            String hashPassword = passwordEncoder.encode(users.getPassword());
//            users.setPassword(hashPassword);
//            savedUser = userRepository.save(users);
//            if(savedUser.getId() > 0){
//                response = ResponseEntity
//                        .status(HttpStatus.CREATED)
//                        .body("User details registered");
//            }
//        } catch (Exception e){
//            response = ResponseEntity
//                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Exception occurred: " + e.getMessage());
//        }
//
//        return response;
//    }

    @RequestMapping("/user")
    public Users getUserDetailsAfterLogin(Principal user) {
        List<Users> users = userRepository.findByEmail(user.getName());
        if (users.size() > 0) {
            return users.get(0);
        }else {
            return null;
        }

    }


}
