package com.example.demo.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class SubscribeType {
    @Id
    private int subscriptionTypeId;
    private String type_name;
    private int price;
    private String description;    
}

