package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.mapper.SubscriptionTypeMapper;
import com.example.demo.model.SubscribeType;

@Service
public class SubscriptionTypeService {
  @Autowired
  private SubscriptionTypeMapper subscriptionTypeMapper;

  public SubscribeType selectSubscriptionType(SubscribeType subscribeType) {
    subscriptionTypeMapper.selectSubscriptionType(subscribeType);
    return subscribeType;
  }
}
