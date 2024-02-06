package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.Subscribe;
import com.example.demo.model.SubscribeType;
import com.example.demo.model.User;
import com.example.demo.repository.SubscribeTypeRepository;
import com.example.demo.service.SubscriptionService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SubscriptionController {
  @Autowired
  private SubscriptionService subscriptionService;
  private final SubscribeTypeRepository subscribeTypeRepository;

@PostMapping("/subscribe")
public String subscribe(@ModelAttribute Subscribe subscribe, HttpServletRequest request) {

    // 세션에서 로그인한 사용자의 정보를 가져옵니다.
    HttpSession session = request.getSession();
    User loginUser = (User) session.getAttribute("user");

    // 로그인한 사용자의 정보(User 객체)를 Subscribe 객체의 user에 설정합니다.
    if (loginUser != null) {
        subscribe.setUser(loginUser);
    } else {
        // 로그인한 사용자의 정보가 없는 경우에는 오류를 처리합니다.
        // 이 부분은 실제 상황에 맞게 구현해야 합니다.
        throw new RuntimeException("로그인한 사용자의 정보가 없습니다.");
    }

    // 폼 데이터에 포함된 subscription_type_id로 SubscribeType 객체를 찾습니다.
    String subscriptionTypeIdStr = request.getParameter("serviceType");
    if (subscriptionTypeIdStr != null) {
        int subscriptionTypeId = Integer.parseInt(subscriptionTypeIdStr);
        Optional<SubscribeType> subscribeTypeOpt = subscribeTypeRepository.findById(subscriptionTypeId);
        if (subscribeTypeOpt.isPresent()) {
            subscribe.setSubscribeType(subscribeTypeOpt.get());
        } else {
            // 폼 데이터에 subscription_type_id가 없는 경우에는 오류를 처리합니다.
            // 이 부분은 실제 상황에 맞게 구현해야 합니다.
            throw new RuntimeException("해당 구독 유형이 존재하지 않습니다.");
        }
    } else {
        // 폼 데이터에 subscription_type_id가 없는 경우에는 오류를 처리합니다.
        // 이 부분은 실제 상황에 맞게 구현해야 합니다.
        throw new RuntimeException("구독 유형이 선택되지 않았습니다.");
    }

    subscriptionService.subscribe(subscribe);

    return "redirect:/"; 
}

}
