package com.example.cinema_booking.service.ticket;

import com.example.cinema_booking.dto.TicketDTO;
import com.example.cinema_booking.repository.TicketRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class TicketServiceImpl implements TicketService{
    private final TicketRepo ticketRepo;

    @Override
    public Set<TicketDTO> getTicketsByCustomerId(Long customerId) {
        return null;
    }
}
