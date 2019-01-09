package com.demo.controller;

import com.demo.entity.User;
import com.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/")
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @GetMapping("/signup")
    public ModelAndView signup(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("signUp");
        return modelAndView;
    }

    @PostMapping("/signup")
    @ResponseBody
    public User signupSuccess(@RequestBody User user){
        return userService.createUser(user);
    }

    @GetMapping("/profile")
    public ModelAndView profile(){
        ModelAndView modelAndView = new ModelAndView();
        UserDetails myUserDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        modelAndView.addObject("user", myUserDetails);
        return modelAndView;
    }



}