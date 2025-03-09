package com.example.cinema_booking.controller.customer;

import com.example.cinema_booking.dto.TicketDTO;
import com.example.cinema_booking.exception.TicketException;
import com.example.cinema_booking.service.ticket.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/customer")
@AllArgsConstructor
public class TicketController {
    private final TicketService ticketService;

    @GetMapping("/all-ticket/{customerId}")
    public ResponseEntity<?> getTicketsByCustomerId(@PathVariable Long customerId) {
        try{
            Set<TicketDTO> tickets = ticketService.getTicketsByCustomerId(customerId);
            return ResponseEntity.ok(tickets);
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Đã xảy ra lỗi: " + ex.getMessage());
        }
    }

    @PutMapping("/cancel-ticket/{ticketId}")
    public ResponseEntity<?> cancelTicket(@PathVariable Long ticketId) {
        try{
            String message = ticketService.cancelTicketByTicketId(ticketId);
            return ResponseEntity.ok(message);
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Đã xảy ra lỗi: " + ex.getMessage());
        }
    }
}
