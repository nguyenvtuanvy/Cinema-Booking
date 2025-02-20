package com.example.cinema_booking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieDTO {
    private Long id;
    private String description;
    private Integer duration;
    private String image;
    private String nation;
    private Float rating;
    private LocalDateTime releaseDate;
    private String title;
    private Long directorId;
}
