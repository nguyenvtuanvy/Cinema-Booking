package com.example.cinema_booking.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Promotion")
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "code", nullable = false, unique = true,length = 10)
    private String code;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "image", nullable = false)
    private String image;
    @Column(name = "discount_amount", nullable = false)
    private BigDecimal discountAmount;
    @Column(name = "expiration_date", nullable = false)
    private Date expirationDate;
    @Column(name = "is_used", columnDefinition = "boolean DEFAULT false")
    private Boolean isUsed;
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "promotion", cascade = CascadeType.ALL)
    private Set<Ticket> tickets = new HashSet<>();

    @OneToMany(mappedBy = "promotion", cascade = CascadeType.ALL)
    private Set<Member> members = new HashSet<>();
}
