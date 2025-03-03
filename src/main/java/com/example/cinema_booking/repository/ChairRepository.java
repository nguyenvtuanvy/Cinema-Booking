package com.example.cinema_booking.repository;

import com.example.cinema_booking.dto.ChairDTO;
import com.example.cinema_booking.entity.Chair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface ChairRepository extends JpaRepository<Chair, Long> {
    @Query("SELECT NEW com.example.cinema_booking.dto.ChairDTO(" +
            "c.rowChair, c.columnChair, cr.name, st.price, st.id, " +
            "CASE WHEN t.id IS NULL THEN 1 ELSE 0 END) " + // Trả về 1 nếu còn trống, 0 nếu đã đặt
            "FROM Chair c " +
            "JOIN c.cinemaRoom cr " +
            "JOIN cr.showTimes st " +
            "JOIN st.showDates sd " +
            "JOIN sd.movies m " +
            "LEFT JOIN Ticket t ON t.nameChair = CONCAT(c.columnChair, c.rowChair) AND t.showTime.id = st.id " +
            "WHERE m.id = :movieId AND sd.id = :showDateId AND st.id = :showTimeId")
    Set<ChairDTO> findByMovieAndShowDateAndShowTime(@Param("movieId") Long movieId,
                                                    @Param("showDateId") Long showDateId,
                                                    @Param("showTimeId") Long showTimeId);
}