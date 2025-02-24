package com.example.cinema_booking.repository;

import com.example.cinema_booking.dto.ShowTimeDTO;
import com.example.cinema_booking.entity.ShowTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface ShowTimeRepo extends JpaRepository<ShowTime, Long> {
    @Query("select new com.example.cinema_booking.dto.ShowTimeDTO(st.id, st.price, st.startTime) " +
            "from ShowDate sd " +
            "join sd.showTimes st " +
            "where sd.id = :showDateId")
    Set<ShowTimeDTO> findShowTimesByShowDateId(@Param("showDateId") Long showDateId);
}
