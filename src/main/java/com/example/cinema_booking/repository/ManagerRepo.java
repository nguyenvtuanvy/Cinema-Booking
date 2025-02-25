package com.example.cinema_booking.repository;

import com.example.cinema_booking.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ManagerRepo extends JpaRepository<Manager, Long> {
    Optional<Manager> findManagerByEmail(String email);
}
