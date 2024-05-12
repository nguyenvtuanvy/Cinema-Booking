package com.example.cinema_booking.repository;

import com.example.cinema_booking.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepo extends JpaRepository<Ticket, Long> {
}
