package com.example.cinema_booking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PromotionDTO {
    private Long id;
    private String code;
    private LocalDateTime createdAt;
    private BigDecimal discountAmount;
    private LocalDateTime expirationDate;
    private String image;
    private String title;

    public PromotionDTO(Long id, String image, String title) {
        this.id = id;
        this.image = image;
        this.title = title;
    }
}
