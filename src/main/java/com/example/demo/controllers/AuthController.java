package com.example.demo.controllers;

import com.example.demo.models.ERole;
import com.example.demo.models.User;
import com.example.demo.services.JwtService;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user){
        Optional<User> optionalUser = userService.getByUserName(user.getUsername());

        if(optionalUser.isPresent()){
            User foundUser = optionalUser.get();

            if(userService.passwordIsValid(foundUser, user.getPassword())){
                List<ERole> roleName = foundUser.getRole().stream()
                        .map(role -> role.getName())
                        .collect(Collectors.toList());

                String token = jwtService.generateToken(foundUser.getUsername(), roleName.toString());

                return ResponseEntity.ok(token);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login incorrecto");
            }
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }



}
