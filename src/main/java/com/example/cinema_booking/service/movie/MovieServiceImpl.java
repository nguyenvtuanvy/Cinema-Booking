package com.example.cinema_booking.service.movie;

import com.example.cinema_booking.dto.MovieDTO;
import com.example.cinema_booking.exception.MovieException;
import com.example.cinema_booking.repository.MovieRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class MovieServiceImpl implements MovieService{
    private final MovieRepo movieRepo;

    @Override
    public MovieDTO findMovieById(Long id) throws MovieException {
        return movieRepo.findMovieById(id).orElseThrow(() -> new MovieException("Movie not found with id: " + id));
    }

    @Override
    public Set<MovieDTO> findAllMovie() throws MovieException {
        Set<MovieDTO> movies = movieRepo.findAllMovie();
        if (movies.isEmpty()) {
            throw new MovieException("No movies found");
        }
        return movies;
    }
}
