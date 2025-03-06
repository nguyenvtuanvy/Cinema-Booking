package com.example.cinema_booking.service.ticket;

import com.example.cinema_booking.dto.TicketDTO;

import java.util.Set;

public interface TicketService {
    Set<TicketDTO> getTicketsByCustomerId(Long customerId);
}
