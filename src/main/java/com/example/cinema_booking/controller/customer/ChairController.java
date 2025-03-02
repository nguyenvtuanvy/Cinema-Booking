package com.example.cinema_booking.controller.customer;

import com.example.cinema_booking.dto.ChairDTO;
import com.example.cinema_booking.dto.RoomWithGroupedChairsDTO;
import com.example.cinema_booking.exception.ChairException;
import com.example.cinema_booking.service.chair.ChairService;
import com.example.cinema_booking.service.movie.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/customer")
@AllArgsConstructor
public class ChairController {
    private final ChairService chairService;

    @GetMapping("/chair/byMovieAndShowDateAndShowTime")
    public ResponseEntity<?> getChairsByMovieAndShowDateAndShowTime(
            @RequestParam Long movieId,
            @RequestParam Long showDateId,
            @RequestParam Long showTimeId) {
        try {
            RoomWithGroupedChairsDTO roomWithGroupedChairs = chairService.getChairInfoByMovieAndShowDateAndShowTime(movieId, showDateId, showTimeId);
            return ResponseEntity.ok(roomWithGroupedChairs);
        } catch (ChairException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Đã xảy ra lỗi: " + ex.getMessage());
        }
    }
}
