package com.example.cinema_booking.repository;

import com.example.cinema_booking.entity.Director;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectorRepo extends JpaRepository<Director, Long> {
}
