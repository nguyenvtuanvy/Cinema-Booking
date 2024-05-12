package com.example.cinema_booking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Cinema_Room")
public class Cinema_Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false, length = 50)
    private String name;
    @Column(name = "address", nullable = false)
    private String address;

    @OneToMany(mappedBy = "cinemaRoom", cascade = CascadeType.ALL)
    private Set<Chair> chairs = new HashSet<>();

    @ManyToMany(mappedBy = "cinemaRooms")
    @JsonIgnore
    private Set<ShowTime> showTimes = new HashSet<>();
}
