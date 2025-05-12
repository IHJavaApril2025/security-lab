package com.example.labsecurity.services;

import com.example.labsecurity.models.User;
import com.example.labsecurity.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Guardar usuario encripando la contraseña
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // Guardar el usuario en la base de datos
        return userRepository.save(user);
    }


    //Comprobar si la contraseña del usuario coincide con la contraseña encriptada usando el propio password encoder que lo
    //gestiona
    public boolean checkPassword(User user, String password) {
        return passwordEncoder.matches(password, user.getPassword());
    }

    //buscar usuario
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}