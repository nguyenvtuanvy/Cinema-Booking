package com.example.cinema_booking.entity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "ShowTime")
public class ShowTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "date_show", nullable = false)
    private Date dateShow;
    @Column(name = "start-time", nullable = false)
    private Time startTime;
    @Column(name = "price", nullable = false, precision = 10, scale = 0)
    private BigDecimal price;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "Movie_Showtime",
            joinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "showtime_id", referencedColumnName = "id", nullable = false)
    )
    private Set<Movie> movies = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "Room_ShowTime",
            joinColumns = @JoinColumn(name = "room_id", referencedColumnName = "id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "showtime_id", referencedColumnName = "id", nullable = false)
    )
    private Set<Cinema_Room> cinemaRooms = new HashSet<>();

    @OneToMany(mappedBy = "showTime", cascade = CascadeType.ALL)
    private Set<Ticket> tickets = new HashSet<>();
}
