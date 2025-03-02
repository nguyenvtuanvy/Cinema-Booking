package com.example.cinema_booking.service.chair;

import com.example.cinema_booking.dto.ChairDTO;
import com.example.cinema_booking.dto.RoomWithGroupedChairsDTO;
import com.example.cinema_booking.exception.ChairException;

import java.util.Set;

public interface ChairService {
    RoomWithGroupedChairsDTO getChairInfoByMovieAndShowDateAndShowTime(Long movieId, Long showDateId, Long showTimeId) throws ChairException;
}
