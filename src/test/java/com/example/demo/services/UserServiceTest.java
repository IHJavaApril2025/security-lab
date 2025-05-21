package com.example.demo.services;


import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class UserServiceTest {

    private User user;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setUsername("Testing_user");
        user.setPassword("1234");
        System.out.println("El usuario inicial es: " + user);

        userService.saveUser(user);
    }


    @AfterEach
    public void tearDown() {
        userRepository.delete(user);
    }

    @Test
    @DisplayName("La encriptación de passwords es correcta")
    public void passwordEncryption() {
        assertTrue(user.getPassword().startsWith("$2a$")); // todas bcrypt usando el algoritmo que estamos usando deberían empezar así
        System.out.println("este es el user final: " + user);
    }

    @Test
    @DisplayName("Password es correcta")
    public void passwordIsCorrect() {
        boolean isCorrect = userService.checkPassword(user, "1234");
        assertTrue(isCorrect);
        System.out.println("Es la password correcta? " + isCorrect);
    }

}
