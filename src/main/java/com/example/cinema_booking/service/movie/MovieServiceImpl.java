package com.example.cinema_booking.service.movie;

import com.example.cinema_booking.dto.MovieDTO;
import com.example.cinema_booking.dto.PerformerDTO;
import com.example.cinema_booking.dto.ShowDateWithTimeDTO;
import com.example.cinema_booking.exception.MovieException;
import com.example.cinema_booking.exception.ShowDateException;
import com.example.cinema_booking.repository.MovieRepo;
import com.example.cinema_booking.repository.PerformerRepo;
import com.example.cinema_booking.service.showdate.ShowDateService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class MovieServiceImpl implements MovieService{
    private final MovieRepo movieRepo;
    private final ShowDateService showDateService;
    private final PerformerRepo performerRepo;

    @Override
    public MovieDTO findMovieById(Long id) throws MovieException, ShowDateException {
        MovieDTO movieDTO = movieRepo.findMovieById(id)
                .orElseThrow(() -> new MovieException("Movie not found with id: " + id));

        Set<PerformerDTO> performers = performerRepo.findPerformerByMovieId(id);
        movieDTO.setPerformers(performers);

        Set<ShowDateWithTimeDTO> showDatesWithTimes = showDateService.findAllShowDateByMovieId(id);
        movieDTO.setShowDatesWithTimes(showDatesWithTimes);


        return movieDTO;
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
