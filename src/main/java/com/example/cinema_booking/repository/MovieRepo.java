package com.example.cinema_booking.repository;

import com.example.cinema_booking.dto.MovieDTO;
import com.example.cinema_booking.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;

public interface MovieRepo extends JpaRepository<Movie, Long> {

    @Query("select new com.example.cinema_booking.dto.MovieDTO(m.id, m.image, m.title) from Movie m")
    Set<MovieDTO> findAllMovie();

    @Query("SELECT new com.example.cinema_booking.dto.MovieDTO(m.id, m.description, m.duration, m.image, m.nation, m.rating, m.releaseDate, m.title, d.name) " +
            "FROM Movie m " +
            "JOIN m.director d " +
            "WHERE m.id = :id")
    Optional<MovieDTO> findMovieById(@Param("id") Long id);
}
