package com.demo.controller;

import com.demo.entity.User;
import com.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin")
public class AdminController {
    @Autowired
    UserService userService;

    @GetMapping("/users")
    public List<User> findUsers(){
        return userService.findUsers();
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @GetMapping("/user/{id}")
    @Cacheable(key = "#id", value = "user")
    public User readUser(@PathVariable("id") String id){
        return userService.readUser(id);
    }

    @PutMapping("/user/{id}")
    @CacheEvict(key = "#user.id", value = "user")
    public User updateUser(@PathVariable("id") String id, @RequestBody User user){
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/user/{id}")
    @CacheEvict(key = "#user.id", value = "user")
    public String deleteUser(@PathVariable("id") String id){
        userService.deleteUser(id);
        return "Success to be deleted!";
    }

}
