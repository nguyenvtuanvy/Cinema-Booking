package com.example.cinema_booking.service.movie;

import com.example.cinema_booking.dto.MovieDTO;
import com.example.cinema_booking.exception.MovieException;
import com.example.cinema_booking.exception.ShowDateException;

import java.util.Set;

public interface MovieService {
    MovieDTO findMovieById(Long id) throws MovieException, ShowDateException;

    Set<MovieDTO> findAllMovie() throws MovieException;
}
