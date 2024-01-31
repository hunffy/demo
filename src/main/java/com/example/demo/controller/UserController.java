package com.example.demo.controller;

import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpSession;

import com.example.demo.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequiredArgsConstructor
public class UserController{
    @Autowired
    UserService userService;

    @GetMapping("/mypage")
    public String MyPage(Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        String user_name = user.getUser_name();
        String email = user.getEmail();
        String company_name = user.getCompany_name();
        String address = user.getAddress();

        model.addAttribute("user_name",user_name);
        model.addAttribute("email",email);
        model.addAttribute("company_name",company_name);
        model.addAttribute("address",address);
        return "mypage";
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
    public String signUp(@ModelAttribute @Validated User user, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        // 입력값 검증
    if (bindingResult.hasErrors()) {
        redirectAttributes.addFlashAttribute("errorMessage", "입력값을 확인해주세요.");
        return "redirect:/signup";
    }
    //회원가입 처리
    boolean success = userService.joinUser(user);
    if(success){
        return "redirect:/login";
    }else{
        redirectAttributes.addFlashAttribute("errorMessage","회원기입에 실패했습니다.");
        return "redirect:/signup";
    }
    }

   @PostMapping("/login")
    public String login(@ModelAttribute User loginUser, Model model , HttpSession session){

        User user = userService.loginUser(loginUser);
        if(user != null){
            model.addAttribute("message","로그인성공");
            model.addAttribute("user_id",user.getUser_id());
            session.setAttribute("user", user);
            return "home";
        }else{
            model.addAttribute("message","로그인실패");
            return "loginFaild";
        }
    }

}
    
