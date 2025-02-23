package com.example.cinema_booking.service.movie;

import com.example.cinema_booking.dto.MovieDTO;
import com.example.cinema_booking.exception.MovieException;

import java.util.Set;

public interface MovieService {
    MovieDTO findMovieById(Long id) throws MovieException;

    Set<MovieDTO> findAllMovie() throws MovieException;
}
