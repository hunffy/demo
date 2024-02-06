package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.demo.model.SubscribeType;
import com.example.demo.service.SubscriptionTypeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SubscriptionTypeController {
  @Autowired
  private SubscriptionTypeService subscriptionTypeService;

  @GetMapping("/selectSubscriptionType")
  public SubscribeType selectSubscriptionType(@ModelAttribute SubscribeType subscribeType) {
    return subscriptionTypeService.selectSubscriptionType(subscribeType);
  }
}
