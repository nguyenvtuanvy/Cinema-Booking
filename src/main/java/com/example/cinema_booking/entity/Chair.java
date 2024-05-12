package com.example.cinema_booking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Chair")
public class Chair {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "row_chair", nullable = false)
    private Integer rowChair;
    @Column(name = "column_chair", nullable = false)
    private Integer columnChair;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "room_id", referencedColumnName = "id", nullable = false)
    private Cinema_Room cinemaRoom;
}
