package com.example.cinema_booking.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingFilmRequest {
    private Long showtimeId;
    private Long promotionId;
    private String nameChair;
    private String nameMovie;
    private String nameRoom;
    private Integer numberChair;
    private BigDecimal totalPrice;
    private LocalDate showDate;
}
