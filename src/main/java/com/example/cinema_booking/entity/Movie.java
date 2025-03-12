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
    @Column(name = "rating")
    private Float rating;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JsonIgnore
    @JoinColumn(name = "director_id", nullable = false, referencedColumnName = "id")
    private Director director;

    @ManyToMany
    @JoinTable(
            name = "Movie_Performer",
            joinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "performer_id", referencedColumnName = "id")
    )
    private Set<Performer> performers = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "Movie_Category",
            joinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id")
    )
    private Set<Category> categories = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "Movie_ShowDate",
            joinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "showdate_id", referencedColumnName = "id")
    )
    private Set<ShowDate> showDates = new HashSet<>();
}
