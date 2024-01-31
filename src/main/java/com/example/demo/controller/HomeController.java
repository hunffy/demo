package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class HomeController {

    @GetMapping("/")
    public String Home(Model model, HttpSession session) {
        //세션에서 로그인상태 확인하여 클라이언트에게 전달
        boolean isLogIn = session.getAttribute("user") !=null;
        model.addAttribute("isLogIn", isLogIn);
        if(isLogIn){
            return "home";
        }
        return "redirect:/login";
    }
    
    //로그아웃 처리
    @PostMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/login";
    }
}
