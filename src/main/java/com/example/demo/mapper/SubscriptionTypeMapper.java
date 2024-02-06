package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.model.SubscribeType;


@Mapper
public interface SubscriptionTypeMapper {
    void selectSubscriptionType(SubscribeType subscribeType);
   
}
