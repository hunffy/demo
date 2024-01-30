package com.example.demo.controller;

import com.example.demo.service.UserService;
import com.example.demo.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class UserController{
    @Autowired
    UserService userService;

    @PostMapping("/signup")
    public String signUp(@RequestBody User user){
        System.out.println(user);
        userService.joinUser(user);
        return "redirect:/login";
    }
}
    
