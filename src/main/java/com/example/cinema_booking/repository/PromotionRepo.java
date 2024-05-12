package com.example.cinema_booking.repository;

import com.example.cinema_booking.entity.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromotionRepo extends JpaRepository<Promotion, Long> {
}
