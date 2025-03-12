package com.example.cinema_booking.repository;

import com.example.cinema_booking.dto.PerformerDTO;
import com.example.cinema_booking.entity.Performer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface PerformerRepo extends JpaRepository<Performer, Long> {

    @Query("select new com.example.cinema_booking.dto.PerformerDTO(p.name) from Movie m " +
            "JOIN m.performers p " +
            "where m.id = :movieId")
    Set<PerformerDTO> findPerformerByMovieId(@Param("movieId") Long movieId);
}
