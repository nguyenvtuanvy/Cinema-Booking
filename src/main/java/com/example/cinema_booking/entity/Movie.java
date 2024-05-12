package com.example.cinema_booking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title", nullable = false, unique = true, length = 100)
    private String title;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "image", nullable = false)
    private String image;
    @Column(name = "nation", nullable = false, length = 50)
    private String nation;
    @Column(name = "release_date", nullable = false)
    private Date releaseDate;
    @Column(name = "duration", nullable = false)
    private Integer duration;
    @Column(name = "rating", nullable = false)
    private Float rating;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinColumn(name = "director_id", nullable = false, referencedColumnName = "id")
    private Director director;

    @ManyToMany(mappedBy = "movies")
    @JsonIgnore
    private Set<Performer> performers = new HashSet<>();

    @ManyToMany(mappedBy = "movies")
    @JsonIgnore
    private Set<Category> categories = new HashSet<>();

    @ManyToMany(mappedBy = "movies")
    @JsonIgnore
    private Set<ShowTime> showTimes = new HashSet<>();
}
