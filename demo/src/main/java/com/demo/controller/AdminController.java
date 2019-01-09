package com.demo.controller;

import com.demo.entity.User;
import com.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("admin")
public class AdminController {
    @Autowired
    UserService userService;

    @GetMapping("/users")
    @ResponseBody
    public List<User> findUsers(){
        return userService.findUsers();
    }

    @PostMapping("/users")
    @ResponseBody
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @GetMapping("/user/{id}")
    @ResponseBody
    @Cacheable(key = "#id", value = "user")
    public User readUser(@PathVariable("id") String id){
        return userService.readUser(id);
    }

    @PutMapping("/user/{id}")
    @ResponseBody
    @CacheEvict(key = "#user.id", value = "user")
    public User updateUser(@PathVariable("id") String id, @RequestBody User user){
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/user/{id}")
    @ResponseBody
    @CacheEvict(key = "#user.id", value = "user")
    public String deleteUser(@PathVariable("id") String id){
        userService.deleteUser(id);
        return "Success to be deleted!";
    }

    /* TEMPLATE THYMELEAF */
    @GetMapping("/users/view")
    public ModelAndView viewUser(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("view");
        modelAndView.addObject("users", userService.findUsers());
        return modelAndView;
    }

    @GetMapping("/user/{id}/edit")
    public ModelAndView editUser(@PathVariable("id") String id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", userService.readUser(id));
        modelAndView.setViewName("edit");
        return modelAndView;
    }


}