package com.example.cinema_booking.repository;

import com.example.cinema_booking.dto.ShowDateDTO;
import com.example.cinema_booking.entity.ShowDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface ShowDateRepo extends JpaRepository<ShowDate, Long> {
    @Query("SELECT new com.example.cinema_booking.dto.ShowDateDTO(sd.id, sd.dateShow) " +
            "FROM Movie m " +
            "JOIN m.showDates sd " +
            "WHERE m.id = :movieId")
    Set<ShowDateDTO> findShowDateByMovieId(@Param("movieId") Long movieId);
}
