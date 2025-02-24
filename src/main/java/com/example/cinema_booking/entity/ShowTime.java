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
    @Column(name = "start_time", nullable = false)
    private Time startTime;
    @Column(name = "price", nullable = false, precision = 10, scale = 0)
    private BigDecimal price;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "ShowDate_ShowTime",
            joinColumns = @JoinColumn(name = "showtime_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "showdate_id", referencedColumnName = "id")
    )
    private Set<ShowDate> showDates = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "Room_ShowTime",
            joinColumns = @JoinColumn(name = "showtime_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "room_id", referencedColumnName = "id")
    )
    private Set<Cinema_Room> cinemaRooms = new HashSet<>();

    @OneToMany(mappedBy = "showTime", cascade = CascadeType.ALL)
    private Set<Ticket> tickets = new HashSet<>();
}
