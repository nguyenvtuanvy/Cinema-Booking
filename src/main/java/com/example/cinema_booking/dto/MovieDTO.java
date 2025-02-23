package com.example.cinema_booking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

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
    private Date releaseDate;
    private String title;
    private Long directorId;



    public MovieDTO(Long id, String image, String title) {
        this.id = id;
        this.image = image;
        this.title = title;
    }
}
