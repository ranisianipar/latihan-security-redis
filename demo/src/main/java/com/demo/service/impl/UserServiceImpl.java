package com.demo.service.impl;

import com.demo.entity.User;
import com.demo.repository.UserRepository;
import com.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Override
    public List<User> findUsers(){
        List<User> userList = userRepository.findAll();
        return userList;
    }

    @Override
    public User createUser(User user){
        User newUser = new User();
        newUser.setId(UUID.randomUUID().toString());
        newUser.setUsername(user.getUsername());
        newUser.setPassword(encoder.encode(user.getPassword())); // bagian ini harus di Password Encoder
        if (user.getRole().toString().equals("ADMIN")) newUser.setRole(User.Role.ADMIN);
        else if (user.getRole().toString().equals("USER")) newUser.setRole(User.Role.USER);
        return userRepository.save(newUser);
    }

    @Override
    public User readUser(String id){
        User existUser = userRepository.findOne(id);
        if (existUser != null) {
            return existUser;
        }
        throw new RuntimeException("User to be read not found!");
    }

    @Override
    public User updateUser(String id, User user){
        User existUser = userRepository.findOne(id);
        if (existUser != null) {
            existUser.setId(id);
            existUser.setUsername(user.getUsername());
            existUser.setPassword(encoder.encode(user.getPassword()));
            if (user.getRole().toString().equals("ADMIN")) existUser.setRole(User.Role.ADMIN);
            else if (user.getRole().toString().equals("USER")) existUser.setRole(User.Role.USER);
            return userRepository.save(existUser);
        }
        else throw new RuntimeException("User to be updated Not Found");
    }

    @Override
    public void deleteUser(String id){
        User existUser = userRepository.findOne(id);
        if (existUser != null) {
            userRepository.delete(id);
        }
        else throw new RuntimeException("User to be deleted Not Found!");
    }
}
