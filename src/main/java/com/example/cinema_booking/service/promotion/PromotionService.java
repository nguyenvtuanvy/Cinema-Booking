package com.example.cinema_booking.service.promotion;

import com.example.cinema_booking.dto.PromotionDTO;
import com.example.cinema_booking.exception.PromotionException;

import java.util.Set;

public interface PromotionService {
    Set<PromotionDTO> findAllPromotion() throws PromotionException;

    void updateExpiredPromotions();
}
