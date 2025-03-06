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
            "CASE WHEN EXISTS (SELECT 1 FROM Ticket t WHERE t.showTime.id = st.id AND t.nameChair LIKE CONCAT('%', CONCAT(c.columnChair, c.rowChair), '%')) THEN 0 ELSE 1 END) " + // Trả về 0 nếu ghế đã được đặt, 1 nếu còn trống
            "FROM Chair c " +
            "JOIN c.cinemaRoom cr " +
            "JOIN cr.showTimes st " +
            "JOIN st.showDates sd " +
            "JOIN sd.movies m " +
            "WHERE m.id = :movieId AND sd.id = :showDateId AND st.id = :showTimeId")
    Set<ChairDTO> findByMovieAndShowDateAndShowTime(@Param("movieId") Long movieId,
                                                    @Param("showDateId") Long showDateId,
                                                    @Param("showTimeId") Long showTimeId);
}