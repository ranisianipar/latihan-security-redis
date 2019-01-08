package com.demo.service;

import com.demo.entity.User;

import java.util.List;

public interface UserService {
    List<User> findUsers();
    User createUser(User user);
    User readUser(String id);
    User updateUser(String id, User user);
    void deleteUser(String id);
}
