package com.example.cinema_booking.service.ticket;

import com.example.cinema_booking.dto.TicketDTO;
import com.example.cinema_booking.exception.TicketException;

import java.util.Set;

public interface TicketService {
    Set<TicketDTO> getTicketsByCustomerId(Long customerId) throws TicketException;

    String cancelTicketByTicketId(Long ticketId) throws TicketException;
}
