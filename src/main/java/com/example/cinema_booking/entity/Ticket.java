package com.example.cinema_booking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "number_chair", nullable = false)
    private Integer numberChair;
    @Column(name = "name_chair", nullable = false, length = 20)
    private String nameChair;
    @Column(name = "name_room", nullable = false, length = 20)
    private String nameRoom;
    @Column(name = "name_movie", nullable = false, length = 50)
    private String nameMovie;
    @Column(name = "name_customer", nullable = false, length = 100)
    private String nameCustomer;
    @Column(name = "total_price", nullable = false)
    private BigDecimal totalPrice;
    @Column(name = "booking_date", nullable = false)
    private LocalDate bookingDate;
    @Column(name = "status", columnDefinition = "int default 1")
    @Builder.Default
    private Integer status = 1;
    @Column(name = "show_date", nullable = false)
    private LocalDate showDate;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JsonIgnore
    @JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinColumn(name = "showtime_id", referencedColumnName = "id", nullable = false)
    private ShowTime showTime;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinColumn(name = "promotion_id", referencedColumnName = "id")
    private Promotion promotion;
}
