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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Controller
@RequiredArgsConstructor
public class UserController{
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    UserService userService;

    @GetMapping("/mypage")
    public String MyPage(Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        String user_name = user.getUser_name();
        String email = user.getEmail();
        String company_name = user.getCompany_name();
        String address = user.getAddress();
        String phone = user.getPhone();

        model.addAttribute("user_name",user_name);
        model.addAttribute("email",email);
        model.addAttribute("company_name",company_name);
        model.addAttribute("address",address);
        model.addAttribute("phone",phone);

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
        if(loginUser.getUser_id() == null || loginUser.getUser_id().isEmpty()){
            model.addAttribute("message","아이디를 입력해주세요.");
            return "loginFaild";
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user = userService.getUserById(loginUser.getUser_id());

        if(user != null && passwordEncoder.matches(loginUser.getPassword(), user.getPassword())){
            model.addAttribute("message","로그인성공");
            model.addAttribute("user_id",user.getUser_id());
            session.setAttribute("user", user);
            return "home";
        }else{
            logger.error("로그인 실패: 아이디={}, 비밀번호={}", loginUser.getUser_id(), loginUser.getPassword());
            model.addAttribute("message","로그인실패");
            return "loginFaild";
        }
}

}
    
