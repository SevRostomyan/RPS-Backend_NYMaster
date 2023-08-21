/*
package com.rps.app.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.Optional;

@Configuration
public class UserConfig {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserConfig(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

//Nedan kod hårdkodar två användare och ser till att de inte skapas igen vid omstart av applikationen
    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            String username1 = "User1";
            String username2 = "User2";
            Optional<UserEntity> existingUser1 = userRepository.findUserByUsername(username1);
            Optional<UserEntity> existingUser2 = userRepository.findUserByUsername(username2);

            if (existingUser1.isPresent()) {
                System.out.println("User with username " + username1 + " already exists, skipping creation.");
            } else {
                UserEntity userEntity1 = new UserEntity(
                        null,
                        username1,
                        passwordEncoder.encode("password1")
                );
                userRepository.save(userEntity1);
                System.out.println("Created user with username " + username1 + ".");
            }

            if (existingUser2.isPresent()) {
                System.out.println("User with username " + username2 + " already exists, skipping creation.");
            } else {
                UserEntity userEntity2 = new UserEntity(
                        null,
                        username2,
                        passwordEncoder.encode("password2")
                );
                userRepository.save(userEntity2);
                System.out.println("Created user with username " + username2 + ".");
            }
        };
    }

}



*/
