package com.example.demo.repository;

import com.example.demo.model.Subscribe;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface SubscriptionRepository extends JpaRepository<Subscribe, Integer> {
    Optional<Subscribe> findByUserUid(Integer uid);

}




