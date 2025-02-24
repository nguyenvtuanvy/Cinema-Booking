package com.example.cinema_booking.service.showtime;

import com.example.cinema_booking.dto.ShowDateDTO;
import com.example.cinema_booking.dto.ShowTimeDTO;
import com.example.cinema_booking.entity.ShowDate;
import com.example.cinema_booking.exception.ShowTimeException;

import java.util.Set;

public interface ShowTimeService {
    Set<ShowTimeDTO> findShowTimesByShowDateId(Long showDateId) throws ShowTimeException;
}
