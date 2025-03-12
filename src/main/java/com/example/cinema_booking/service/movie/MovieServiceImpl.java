package com.example.cinema_booking.service.movie;

import com.example.cinema_booking.dto.MovieDTO;
import com.example.cinema_booking.dto.PerformerDTO;
import com.example.cinema_booking.dto.ShowDateWithTimeDTO;
import com.example.cinema_booking.entity.Director;
import com.example.cinema_booking.entity.Movie;
import com.example.cinema_booking.exception.MovieException;
import com.example.cinema_booking.exception.ShowDateException;
import com.example.cinema_booking.mapper.MovieMapper;
import com.example.cinema_booking.repository.DirectorRepo;
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
    private final DirectorRepo directorRepo;
    private final MovieMapper movieMapper;

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

    @Override
    public String createMovie(MovieDTO movieDTO) throws MovieException {
        if (movieDTO.getTitle() == null || movieDTO.getTitle().isEmpty()) {
            throw new MovieException("Movie title cannot be empty");
        }

        try {
            Movie movie = movieMapper.toEntity(movieDTO);

            if (movieDTO.getDirectorId() != null) {
                Director director = directorRepo.findById(movieDTO.getDirectorId())
                        .orElseThrow(() -> new MovieException("Director not found with id: " + movieDTO.getDirectorId()));
                movie.setDirector(director);
            } else {
                throw new MovieException("Director ID cannot be null");
            }

            Movie savedMovie = movieRepo.save(movie);

            if (savedMovie.getId() != null) {
                return "Lưu phim thành công";
            } else {
                return "Lưu phim thất bại";
            }
        } catch (Exception ex) {
            return "Lỗi khi lưu phim: " + ex.getMessage();
        }
    }
}
