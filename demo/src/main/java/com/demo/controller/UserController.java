package com.demo.controller;

import com.demo.entity.User;
import com.demo.repository.UserRepository;
import com.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String index(){
        return "Hello World!";
    }

    @GetMapping("/login")
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @GetMapping("/signup")
    public ModelAndView signup() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("signup");
        return modelAndView;
    }

    @PostMapping("/signup")
    public String signupSuccess(@RequestBody User user){
        userService.createUser(user);
        return "redirect:/";
    }

}
