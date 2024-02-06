package com.example.demo.model;

import lombok.Data;
import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@Data
public class Subscribe {
    @Id
    private int subscription_id;
    private int storage_capacity;
    private LocalDateTime subscription_start_date;
    private LocalDateTime subscription_end_date;

    @ManyToOne
    @JoinColumn(name = "u_id", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "subscription_type_id", insertable = false, updatable = false)
    private SubscribeType subscribeType;
}
