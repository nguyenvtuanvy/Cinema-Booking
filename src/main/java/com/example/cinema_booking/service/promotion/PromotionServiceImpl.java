package com.example.cinema_booking.service.promotion;

import com.example.cinema_booking.dto.PromotionDTO;
import com.example.cinema_booking.entity.Promotion;
import com.example.cinema_booking.exception.PromotionException;
import com.example.cinema_booking.repository.PromotionRepo;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class PromotionServiceImpl implements PromotionService{
    private PromotionRepo promotionRepo;
    @Override
    public Set<PromotionDTO> findAllPromotion() throws PromotionException {
        Set<PromotionDTO> promotions = promotionRepo.findAllPromotion();
        if (promotions.isEmpty()) {
            throw new PromotionException("No promotions found");
        }
        return promotions;
    }

    @Override
    @Scheduled(cron = "0 0 0 * * ?") // 0 giờ
    public void updateExpiredPromotions() {
        Date now = new Date();
        List<Promotion> expiredPromotions = promotionRepo.findByExpirationDateBeforeAndIsUsedFalse(now);
        for (Promotion promotion : expiredPromotions) {
            promotion.setIsUsed(true);
            promotionRepo.save(promotion);
        }
    }
}
