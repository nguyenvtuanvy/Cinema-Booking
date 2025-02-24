package com.example.cinema_booking.service.showdate;

import com.example.cinema_booking.dto.ShowDateDTO;
import com.example.cinema_booking.dto.ShowDateWithTimeDTO;
import com.example.cinema_booking.exception.ShowDateException;
import com.example.cinema_booking.exception.ShowTimeException;

import java.util.Set;

public interface ShowDateService {
    Set<ShowDateWithTimeDTO> findAllShowDateByMovieId(Long movieId) throws ShowDateException;
}
