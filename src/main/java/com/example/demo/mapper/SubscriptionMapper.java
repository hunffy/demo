package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.model.Subscribe;
import com.example.demo.model.SubscribeType;

@Mapper
public interface SubscriptionMapper {
    void insertSubscription(Subscribe subscribe);
    SubscribeType findByTypeName(String typeName);
}
