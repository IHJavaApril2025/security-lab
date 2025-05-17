package com.example.demo.TestService;

import com.example.demo.repositories.UserRepository;
import com.example.demo.services.JwtService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JwtServiceTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtService jwtService;

    @Test
    @DisplayName("Token")
    void generateToken(){
        String token = jwtService.generateToken("Pedro", "[ROLE_ADMIN]");

        System.out.println("El token: " + token);
    }


}
