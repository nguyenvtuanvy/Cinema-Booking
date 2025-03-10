package com.example.cinema_booking.controller.admin;

import com.example.cinema_booking.dto.MovieDTO;
import com.example.cinema_booking.service.movie.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/manager")
@AllArgsConstructor
public class AdminMovieController {
    private final MovieService movieService;

    @PostMapping("/create-movie")
    public ResponseEntity<?> createMovie(@RequestBody MovieDTO movieDTO) {
        try {
            String message = movieService.createMovie(movieDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(message);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An internal server error occurred");
        }
    }
}
