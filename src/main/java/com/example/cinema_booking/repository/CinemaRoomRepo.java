package com.example.cinema_booking.repository;

import com.example.cinema_booking.entity.Cinema_Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CinemaRoomRepo extends JpaRepository<Cinema_Room, Long> {
}
