package com.example.cinema_booking.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class TicketDTO {
    private Long id;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate bookingDate;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate showDate;
    private String nameChair;
    private String nameCustomer;
    private String nameMovie;
    private String nameRoom;
    private Integer numberChair;
    private BigDecimal totalPrice;
    private Long customerId;
    private Long promotionId;
    private Long showtimeId;

    public TicketDTO(Long id, LocalDate showDate, String nameChair, String nameMovie, String nameRoom, BigDecimal totalPrice, Long showtimeId) {
        this.id = id;
        this.showDate = showDate;
        this.nameChair = nameChair;
        this.nameMovie = nameMovie;
        this.nameRoom = nameRoom;
        this.totalPrice = totalPrice;
        this.showtimeId = showtimeId;
    }
}
