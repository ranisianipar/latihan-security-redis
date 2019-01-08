package com.demo.service.impl;

import com.demo.entity.User;
import com.demo.repository.UserRepository;
import com.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> findUsers(){
        List<User> userList = userRepository.findAll();
        return userList;
    }

    @Override
    public User createUser(User user){
        User newUser = new User();
        newUser.setId(user.getId());
        newUser.setUsername(user.getUsername());
        newUser.setPassword(user.getPassword()); // bagian ini harus di Password Encoder
        if (user.getRole().toString() == "ADMIN") newUser.setRole(User.Role.ADMIN);
        else if (user.getRole().toString() == "USER") newUser.setRole(User.Role.USER);
        return userRepository.save(newUser);
    }

    @Override
    public User readUser(String id){
        User existUser = userRepository.findOne(id);
        if (existUser != null) {
            return userRepository.findOne(id);
        }
        else throw new RuntimeException("User to be read not found!");
    }

    @Override
    public User updateUser(String id, User user){
        User existUser = userRepository.findOne(id);
        if (existUser != null) {
            existUser.setId(id);
            existUser.setUsername(user.getUsername());
            existUser.setPassword(user.getPassword());
            if (user.getRole().toString() == "ADMIN") existUser.setRole(User.Role.ADMIN);
            else if (user.getRole().toString() == "USER") existUser.setRole(User.Role.USER);
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
