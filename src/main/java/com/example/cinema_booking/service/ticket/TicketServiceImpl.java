package com.example.cinema_booking.service.ticket;

import com.example.cinema_booking.dto.TicketDTO;
import com.example.cinema_booking.entity.Ticket;
import com.example.cinema_booking.exception.TicketException;
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
    public Set<TicketDTO> getTicketsByCustomerId(Long customerId) throws TicketException {
        Set<TicketDTO> tickets = ticketRepo.findTicketByCustomerId(customerId);

        if (tickets.isEmpty()){
            throw new TicketException("Không tìm thấy vé xem phim");
        }

        return tickets;
    }

    @Override
    public String cancelTicketByTicketId(Long ticketId) throws TicketException {
        Ticket ticket = ticketRepo.findById(ticketId)
                .orElseThrow(() -> new TicketException("Không tìm thấy ticket id: " + ticketId));

        ticketRepo.cancelTicket(ticketId);
        return "Huỷ vé thành công";
    }
}
