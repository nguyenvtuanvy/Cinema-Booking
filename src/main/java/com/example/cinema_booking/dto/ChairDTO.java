package com.example.cinema_booking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChairDTO {
    private String rowChair;
    private String columnChair;
    private String roomName;
    private BigDecimal price;
    private Long showTimeId;
    private int status;
}
