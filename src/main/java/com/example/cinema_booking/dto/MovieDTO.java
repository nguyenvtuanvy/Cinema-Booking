package com.example.cinema_booking.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

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
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date releaseDate;
    private String title;
    private String director;
    private Set<PerformerDTO> performers;
    private Set<ShowDateWithTimeDTO> showDatesWithTimes;
    private Long directorId;


    public MovieDTO(Long id, String image, String title) {
        this.id = id;
        this.image = image;
        this.title = title;
    }

    public MovieDTO(Long id, String description, Integer duration, String image, String nation, Float rating, Date releaseDate, String title, String director) {
        this.id = id;
        this.description = description;
        this.duration = duration;
        this.image = image;
        this.nation = nation;
        this.rating = rating;
        this.releaseDate = releaseDate;
        this.title = title;
        this.director = director;
    }

    public MovieDTO(String description, Integer duration, String image, String nation, Date releaseDate, String title, Long directorId) {
        this.description = description;
        this.duration = duration;
        this.image = image;
        this.nation = nation;
        this.releaseDate = releaseDate;
        this.title = title;
        this.directorId = directorId;
    }
}
