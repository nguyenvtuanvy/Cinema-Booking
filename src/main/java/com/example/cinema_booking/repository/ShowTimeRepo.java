package com.example.cinema_booking.repository;

import com.example.cinema_booking.entity.ShowTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowTimeRepo extends JpaRepository<ShowTime, Long> {
}
