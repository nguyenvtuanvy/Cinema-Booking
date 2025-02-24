package com.example.cinema_booking.entity;


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
@Table(name = "ShowDate")
public class ShowDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "date_show", nullable = false)
    private Date dateShow;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "Movie_ShowDate",
            joinColumns = @JoinColumn(name = "showdate_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id")
    )
    private Set<Movie> movies = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "ShowDate_ShowTime",
            joinColumns = @JoinColumn(name = "showdate_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "showtime_id", referencedColumnName = "id")
    )
    private Set<ShowTime> showTimes = new HashSet<>();
}
