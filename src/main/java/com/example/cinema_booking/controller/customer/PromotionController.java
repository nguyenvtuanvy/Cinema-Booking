package com.example.cinema_booking.controller.customer;

import com.example.cinema_booking.dto.MovieDTO;
import com.example.cinema_booking.dto.PromotionDTO;
import com.example.cinema_booking.exception.MovieException;
import com.example.cinema_booking.exception.PromotionException;
import com.example.cinema_booking.service.promotion.PromotionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/customer")
@AllArgsConstructor
public class PromotionController {
    @Autowired
    private final PromotionService promotionService;

    @GetMapping("/promotion/all")
    public ResponseEntity<?> findAllPromotions() {
        try {
            Set<PromotionDTO> promotions = promotionService.findAllPromotion();
            return ResponseEntity.ok(promotions);
        } catch (PromotionException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An internal server error occurred");
        }
    }
}
