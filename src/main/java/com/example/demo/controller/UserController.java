package com.example.demo.controller;

import com.example.demo.service.UserService;
import com.example.demo.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
public class UserController{
    @Autowired
    UserService userService;

    @GetMapping("/")
    public String HomePage() {
        return "home";
    }
    

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/signup")
    public String signUpPage() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signUp(@RequestBody User user){
        System.out.println(user);
        userService.joinUser(user);
        return "redirect:/login";
    }

   @PostMapping("/login")
    public String login(@ModelAttribute User loginUser, Model model){
        System.out.println(loginUser);
        User user = userService.loginUser(loginUser);
        if(user != null){
            model.addAttribute("message","로그인성공");
            model.addAttribute("user_id",user.getUser_id());
            return "home";
        }else{
            model.addAttribute("message","로그인실패");
            return "loginFaild";
        }
    }

}
    
