package com.example.demo.TestService;

import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {

    private User user;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setUP(){
        user = new User();
        user.setUsername("Marta");
        user.setPassword("1234");

        System.out.println("El usuario es: " + user);
        userService.saveUser(user);
    }

   /* @AfterEach
    public void delete(){
        userRepository.delete(user);
    }*/

    @Test
    @DisplayName("Encryption")
    public void encryptionCorrect(){
        assertTrue(user.getPassword().startsWith("$2a$"));
        System.out.println("Encriptado: " + user);
    }
}
