package com.example.demo.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.SubscriptionMapper;
import com.example.demo.model.Subscribe;
import com.example.demo.model.User;
import com.example.demo.repository.SubscriptionRepository;;
@Service
public class SubscriptionService {
  private final SubscriptionRepository subscriptionRepository;

  public SubscriptionService(SubscriptionRepository subscriptionRepository){
    this.subscriptionRepository = subscriptionRepository;
  }

  @Autowired
  private SubscriptionMapper subscriptionMapper;

  public Subscribe subscribe(Subscribe subscribe) {
    subscriptionMapper.insertSubscription(subscribe);
    return subscribe;
  }

  // 구독 검사
  public String checkSubscription(User user){
    Subscribe subscribe = subscriptionRepository.findByuser_U_Id(user.getU_id()).orElse(null);
    if(subscribe != null){
      LocalDateTime now = LocalDateTime.now();
      if(subscribe.getSubscription_end_date().isAfter(now)) {
        return "현재 구독 중이며, 구독 기간이 아직 남아 있습니다. 구독 유형: " + subscribe.getSubscribeType().getType_name();
      } else {
        return "구독 가능합니다. 이전 구독 기간이 만료되었습니다.";
      }
    } else {
      return "구독 가능합니다.";
    }
}

}

