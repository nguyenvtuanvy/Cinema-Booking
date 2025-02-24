package com.example.cinema_booking.controller.customer;

import com.example.cinema_booking.dto.MovieDTO;
import com.example.cinema_booking.dto.ShowDateDTO;
import com.example.cinema_booking.exception.LoginException;
import com.example.cinema_booking.exception.MovieException;
import com.example.cinema_booking.request.AuthenticationRequest;
import com.example.cinema_booking.response.AuthenticationResponse;
import com.example.cinema_booking.service.movie.MovieService;
import com.example.cinema_booking.service.showdate.ShowDateService;
import com.example.cinema_booking.service.showtime.ShowTimeService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/customer")
@AllArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @GetMapping("/movie/{id}")
    public ResponseEntity<?> findMovieById(@PathVariable("id") Long id) {
        try {
            MovieDTO movieDTO = movieService.findMovieById(id);
            return ResponseEntity.ok(movieDTO);
        } catch (MovieException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An internal server error occurred");
        }
    }

    @GetMapping("/movie/all")
    public ResponseEntity<?> findAllMovies() {
        try {
            Set<MovieDTO> movies = movieService.findAllMovie();
            return ResponseEntity.ok(movies);
        } catch (MovieException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An internal server error occurred");
        }
    }


}
