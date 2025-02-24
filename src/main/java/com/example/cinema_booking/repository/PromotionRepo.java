package com.example.cinema_booking.repository;

import com.example.cinema_booking.dto.PromotionDTO;
import com.example.cinema_booking.entity.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface PromotionRepo extends JpaRepository<Promotion, Long> {
    @Query("select new com.example.cinema_booking.dto.PromotionDTO(p.id, p.title, p.title) from Promotion p")
    Set<PromotionDTO> findAllPromotion();

    List<Promotion> findByExpirationDateBeforeAndIsUsedFalse(Date now);
}
