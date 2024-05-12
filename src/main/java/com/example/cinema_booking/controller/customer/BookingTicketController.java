package com.example.cinema_booking.controller.customer;

import com.example.cinema_booking.request.BookingFilmRequest;
import com.example.cinema_booking.service.bookingfilm.BookingFilmService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
@AllArgsConstructor
public class BookingTicketController {
    private final BookingFilmService bookingFilmService;
    @GetMapping("/demo")
    public ResponseEntity<String> getText(){
        return ResponseEntity.ok("Hello Customer!!!");
    }

    @PostMapping("/booking-movie")
    public ResponseEntity<?> bookingMovie(@RequestBody BookingFilmRequest request){
        try{
            String message = bookingFilmService.BookingFilm(request);
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
