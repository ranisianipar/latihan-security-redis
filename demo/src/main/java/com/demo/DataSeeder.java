package com.demo;

import com.demo.entity.User;
import com.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

// lihat Alfian!!
@Component
public class DataSeeder {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @EventListener
    public void initAdminAccount(ContextRefreshedEvent event){
        User user = userRepository.findByUsername("root");
        if (user == null){
            User newUser = User.builder().id(UUID.randomUUID().toString())
                    .username("root")
                    .password(encoder.encode("root"))
                    .role(User.Role.ADMIN)
                    .build();
            userRepository.save(newUser);
        }
    }
}
